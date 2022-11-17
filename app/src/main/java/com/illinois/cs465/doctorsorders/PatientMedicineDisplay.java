package com.illinois.cs465.doctorsorders;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class PatientMedicineDisplay extends AppCompatActivity {
    int[] images = {R.drawable.atorvastatin, R.drawable.metformin, R.drawable.simvastatin,
            R.drawable.omeprazole, R.drawable.amlodipine};
    final String FORMAT = "%02d : %02d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String medicine = intent.getStringExtra("medicine");
        int index = intent.getIntExtra("index", 0);

        setContentView(R.layout.activity_patient_medicine_display_layout);

        Button yes_button = findViewById(R.id.yes_button);
        Button no_button = findViewById(R.id.no_button);

        TextView text = findViewById(R.id.display_text);
        text.setText(R.string.displayInitialText);

        yes_button.setVisibility(View.INVISIBLE);
        no_button.setVisibility(View.INVISIBLE);

        TextView tv = findViewById(R.id.display_medicine);
        tv.setText(getString(R.string.patient_medicine, medicine));

        ImageView image = findViewById(R.id.medicine_image);
        image.setImageResource(images[index]);

        TextView time = findViewById(R.id.time);
        setCountDownOneMinute(time, yes_button, no_button, text);
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
}
