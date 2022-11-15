package com.illinois.cs465.doctorsorders;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.time.LocalTime;
import java.util.Random;

public class PatientDashboardActivity extends AppCompatActivity {
    String[] medicines = {"Atorvastatin", "Metformin", "Simvastatin", "Omeprazole", "Amlodipine"};
    int[] images = {R.drawable.atorvastatin, R.drawable.metformin, R.drawable.simvastatin,
            R.drawable.omeprazole, R.drawable.amlodipine};
    String CHANNEL_ID = "123";
    String timeForMedicine = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dashboard_layout);
        int randomMedicine = new Random().nextInt(medicines.length);

        TextView tv = (TextView) findViewById(R.id.patient_medicine);
        tv.setText(getString(R.string.patient_medicine, medicines[randomMedicine]));

        TextView time = findViewById(R.id.patient_medicine_time);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            timeForMedicine = String.valueOf(LocalTime.now().getHour());
            timeForMedicine += ":" + LocalTime.now().plusMinutes(1).getMinute();
            time.setText(getString(R.string.patient_medicine_time, timeForMedicine));
        }

        ImageView image = findViewById(R.id.patient_medicine_image);
        image.setImageResource(images[randomMedicine]);

        String timeNow = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            timeNow = String.valueOf(LocalTime.now().getHour());
            timeNow += ":" + LocalTime.now().plusMinutes(2).getMinute();
        }

        createNotification(randomMedicine);
        sendSMS();
    }

    private void sendSMS() {
        String number = "4479021076";
        String msg = "Your GrandParent has taken the medicine!";
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Some fields is Empty", Toast.LENGTH_LONG).show();
        }
    }

    private void createNotification(int randomMedicine) {

//        Intent intent = new Intent(this, AlertDetails.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

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
//                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        int notificationId = new Random().nextInt();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, builder.build());
    }
}
