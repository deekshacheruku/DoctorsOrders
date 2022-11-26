package com.illinois.cs465.doctorsorders;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class PatientMedicineDisplay extends AppCompatActivity implements View.OnClickListener {
    DatabaseHelper databaseHelper;
    String patientName;
    String medicine;
    final String FORMAT = "%02d : %02d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_medicine_display_layout);
        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        patientName = intent.getStringExtra("patientName");
        medicine = intent.getStringExtra("medicine");
        String pills = intent.getStringExtra("pills");
        String instructions = intent.getStringExtra("instructions");

        setTextViews(pills, instructions);
        setYesOrNoButtons();
    }

    private void setTextViews(String pills, String instructions) {
        TextView pillsView = findViewById(R.id.patient_pills);
        pillsView.setText(getString(R.string.patient_pills, pills));

        TextView tv = findViewById(R.id.display_medicine);
        tv.setText(getString(R.string.patient_medicine, medicine));

        ImageView image = findViewById(R.id.medicine_image);
        Cursor img = databaseHelper.getMedicineImage(medicine.toLowerCase());
        while (img.moveToNext()) {
            byte[] imgBlob = img.getBlob(2);
            Bitmap imgBitmap = BitmapFactory.decodeByteArray(imgBlob, 0, imgBlob.length);
            image.setImageBitmap(imgBitmap);
        }

        TextView instruction = findViewById(R.id.instructions);
        instruction.setText(instructions);
    }

    private void setYesOrNoButtons() {
        Button yes_button = findViewById(R.id.yes_button);
        Button no_button = findViewById(R.id.no_button);
        TextView text = findViewById(R.id.display_text);
        text.setText(R.string.displayInitialText);

        yes_button.setVisibility(View.INVISIBLE);
        no_button.setVisibility(View.INVISIBLE);

        TextView time = findViewById(R.id.time);
        setCountDownOneMinute(time, yes_button, no_button, text);

        yes_button.setOnClickListener(this);
        no_button.setOnClickListener(this);
    }

    private void setCountDownOneMinute(TextView time, Button yes_button, Button no_button, TextView text) {
        new CountDownTimer(61000, 1000) {

            public void onTick(long millisUntilFinished) {
                long min = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished));
                long sec = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished));
                time.setText(String.format(FORMAT, min, sec));
            }

            public void onFinish() {
                setCountDownOneHour(time, yes_button, no_button, text);
            }
        }.start();
    }

    private void setCountDownOneHour(TextView time, Button yes_button, Button no_button, TextView text) {
        yes_button.setVisibility(View.VISIBLE);
        no_button.setVisibility(View.VISIBLE);

        text.setText(R.string.displayFinalText);

        new CountDownTimer(3600000, 1000) {

            public void onTick(long millisUntilFinished) {
                long min = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished));
                long sec = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished));
                time.setText(String.format(FORMAT, min, sec));
            }

            public void onFinish() {
                time.setText("");
            }
        }.start();
    }

    @Override
    public void onClick(View view) {
        String timeNow = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            timeNow = String.valueOf(LocalTime.now().getHour());
            timeNow += ":" + LocalTime.now().getMinute();
        }

        if (view.getId() == R.id.yes_button) {
            String sms = patientName + " has taken the " + medicine + " at " + timeNow + "!";
            confirmResponse("Have you taken the medicine?", sms);
        } else {
            String sms = patientName + " has NOT taken the " + medicine + " at " + timeNow + "!";
            confirmResponse("Have you not taken the medicine?", sms);
        }
    }

    private void confirmResponse(String displayMessage, String smsMessage) {
        String display = "We informed your family!";

        new AlertDialog.Builder(this)
                .setMessage(displayMessage)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(R.string.yes, (dialog, whichButton) -> {
                    sendSMS(smsMessage, display);
                    startActivity(new Intent(this, PatientDashboardActivity.class));
                })
                .setNegativeButton(R.string.no, null).show();
    }

    private void sendSMS(String msg, String display) {
        String number = "4479021076";
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, msg, null, null);
            Toast.makeText(getApplicationContext(), display, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Some fields is Empty", Toast.LENGTH_LONG).show();
        }
    }
}
