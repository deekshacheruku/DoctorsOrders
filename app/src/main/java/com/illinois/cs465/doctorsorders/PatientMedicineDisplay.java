package com.illinois.cs465.doctorsorders;

import android.content.Intent;
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

import java.util.concurrent.TimeUnit;

public class PatientMedicineDisplay extends AppCompatActivity implements View.OnClickListener {
    int[] images = {R.drawable.atorvastatin, R.drawable.metformin, R.drawable.simvastatin,
            R.drawable.omeprazole, R.drawable.amlodipine};
    final String FORMAT = "%02d : %02d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String medicine = intent.getStringExtra("medicine");
        String dosage = intent.getStringExtra("dosage");
        int index = intent.getIntExtra("index", 0);

        setContentView(R.layout.activity_patient_medicine_display_layout);

        Button yes_button = findViewById(R.id.yes_button);
        Button no_button = findViewById(R.id.no_button);

        TextView text = findViewById(R.id.display_text);
        text.setText(R.string.displayInitialText);

        TextView dosageView = findViewById(R.id.patient_dosage);
        dosageView.setText(getString(R.string.patient_dosage, dosage));

        yes_button.setVisibility(View.INVISIBLE);
        no_button.setVisibility(View.INVISIBLE);

        TextView tv = findViewById(R.id.display_medicine);
        tv.setText(getString(R.string.patient_medicine, medicine));

        ImageView image = findViewById(R.id.medicine_image);
        image.setImageResource(images[index]);

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
        String display = "We informed your family!";
        if (view.getId() == R.id.yes_button) {
            String msg = "Your GrandParent has taken the medicine!";
            sendSMS(msg, display);
            startActivity(new Intent(this, PatientDashboardActivity.class));
        } else {
            new AlertDialog.Builder(this)
                    .setMessage("Have you not taken the medicine?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(R.string.yes, (dialog, whichButton) -> {
                        String msg = "Your GrandParent has NOT taken the medicine!";
                        sendSMS(msg, display);
                        startActivity(new Intent(this, PatientDashboardActivity.class));
                    })
                    .setNegativeButton(R.string.no, null).show();
        }
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
