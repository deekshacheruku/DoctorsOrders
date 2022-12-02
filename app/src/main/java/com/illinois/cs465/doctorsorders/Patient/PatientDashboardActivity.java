package com.illinois.cs465.doctorsorders.Patient;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.illinois.cs465.doctorsorders.DatabaseHelper;
import com.illinois.cs465.doctorsorders.R;

import java.util.ArrayList;

public class PatientDashboardActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    String CHANNEL_ID = "123";
    String timeForMedicine = "";
    String pills = "";
    String medName = "";
    String instructions = "";
    String frequency = "";
    String patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle patInfo = getIntent().getBundleExtra("fromStep0");
        patient = patInfo.getString("name");
        setContentView(R.layout.activity_patient_dashboard_layout);
        databaseHelper = new DatabaseHelper(this);

        //Should get the patient name from Login Screen in the Format of Full Name "David Woods" or "Jim Frost"
        Log.d("patientName", patient);

        Cursor schedule = databaseHelper.getSchedule(patient);
        if (schedule.getCount() == 0) {
            startActivity(new Intent(this, DefaultPatientScreen.class));
        } else {
            while (schedule.moveToNext()) {
                medName = schedule.getString(2);
                pills = schedule.getString(3);
                instructions = schedule.getString(4);
                frequency = schedule.getString(5);
                timeForMedicine = schedule.getString(6);
            }

            setTextViews();
            createNotificationChannel();
            createNotificationReminder();
        }
    }

    private void setTextViews() {
        TextView tv = findViewById(R.id.patient_medicine);
        tv.setText(getString(R.string.patient_medicine, medName));

        TextView time = findViewById(R.id.patient_medicine_time);
        time.setText(getString(R.string.patient_medicine_time, timeForMedicine));

        TextView pill = findViewById(R.id.patient_pills);
        pill.setText(getString(R.string.patient_pills, pills));

        TextView instruction = findViewById(R.id.instructions);
        instruction.setText(getString(R.string.patient_medicine_instructions, instructions));

        ImageView image = findViewById(R.id.patient_medicine_image);
        Cursor img = databaseHelper.getMedicineImage(medName.toLowerCase());
        while (img.moveToNext()) {
            byte[] imgBlob = img.getBlob(2);
            Bitmap imgBitmap = BitmapFactory.decodeByteArray(imgBlob, 0, imgBlob.length);
            image.setImageBitmap(imgBitmap);
        }
    }

    private void createNotificationChannel() {
        CharSequence name = getString(R.string.channel_name);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void createNotificationReminder() {
        Intent intent = new Intent(this, ReminderBroadcast.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("medicine", medName);
        intent.putExtra("pills", pills);
        intent.putExtra("instructions", instructions);
        intent.putExtra("patientName", patient);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent,
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmService = (AlarmManager) getSystemService(ALARM_SERVICE);

        ArrayList<Long> time = getTime();
        for (Long t : time) {
            alarmService.set(AlarmManager.RTC_WAKEUP, t, pendingIntent);
        }
    }

    private ArrayList<Long> getTime() {
        String[] time = timeForMedicine.split(", ");
        ArrayList<Long> times = new ArrayList<>();
        for (String t : time) {
            switch (t) {
                case "breakfast":
                    times.add(System.currentTimeMillis() + (1000));
                    break;
                case "lunch":
                    times.add(System.currentTimeMillis() + (1000 * 5));
                    break;
                case "dinner":
                    times.add(System.currentTimeMillis() + (1000 * 10));
                    break;
            }
        }
        times.add(System.currentTimeMillis() + (1000 * 15));
        return times;
    }
}
