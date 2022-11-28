package com.illinois.cs465.doctorsorders;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

public class PatientDashboardActivity extends AppCompatActivity {
    String[] medicines = {"Atorvastatin", "Metformin", "Simvastatin", "Omeprazole", "Amlodipine"};
    int[] images = {R.drawable.atorvastatin, R.drawable.metformin, R.drawable.simvastatin,
            R.drawable.omeprazole, R.drawable.amlodipine};
    String CHANNEL_ID = "123";

    DatabaseHelper databaseHelper;

    String timeForMedicine = "";
    String pills = "";
    String medName = "";
    String instructions = "";
    String frequency = "";
    String patient = "Jim Frost";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dashboard_layout);
        databaseHelper = new DatabaseHelper(this);

        //Should get the patient name from Login Screen in the Format of Full Name "David Woods" or "Jim Frost"
        Log.d("patientName", patient);

        Cursor schedule = databaseHelper.getSchedule(patient);
        while (schedule.moveToNext()) {
            medName = schedule.getString(2);
            pills = schedule.getString(3);
            instructions = schedule.getString(4);
            frequency = schedule.getString(5);
            timeForMedicine = schedule.getString(6);
        }

        int randomMedicine = new Random().nextInt(medicines.length);

        setTextViews(randomMedicine);

        createNotification(randomMedicine);
    }

    private void setTextViews(int randomMedicine) {
        TextView tv = findViewById(R.id.patient_medicine);
        tv.setText(getString(R.string.patient_medicine, medName));

        TextView time = findViewById(R.id.patient_medicine_time);
        time.setText(getString(R.string.patient_medicine_time, timeForMedicine));

        TextView pill = findViewById(R.id.patient_pills);
        pill.setText(getString(R.string.patient_pills, pills));

        TextView instruction = findViewById(R.id.instructions);
        instruction.setText(getString(R.string.patient_medicine_instructions, instructions));

        ImageView image = findViewById(R.id.patient_medicine_image);
        image.setImageResource(images[randomMedicine]);
    }

    private void createNotification(int randomMedicine) {

        Intent intent = new Intent(this, PatientMedicineDisplay.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("medicine", medName);
        intent.putExtra("index", randomMedicine);
        intent.putExtra("dosage", pills);
        intent.setAction(medicines[randomMedicine]);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);


        CharSequence name = getString(R.string.channel_name);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Time to take Medicine!")
                .setContentText(medicines[randomMedicine])
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(medicines[randomMedicine]))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSmallIcon(R.drawable.amlodipine)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        int notificationId = new Random().nextInt();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, builder.build());
    }
}
