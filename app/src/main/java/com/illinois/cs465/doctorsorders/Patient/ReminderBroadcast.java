package com.illinois.cs465.doctorsorders.Patient;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.illinois.cs465.doctorsorders.R;

import java.util.Random;

public class ReminderBroadcast extends BroadcastReceiver {
    String CHANNEL_ID = "123";

    @Override
    public void onReceive(Context context, Intent intent) {
        String medicine = intent.getStringExtra("medicine");

        Intent nextIntent = new Intent(context, PatientMedicineDisplay.class);
        nextIntent.putExtra("medicine", medicine);
        nextIntent.putExtra("pills", intent.getStringExtra("pills"));
        nextIntent.putExtra("instructions", intent.getStringExtra("instructions"));
        nextIntent.putExtra("patientName", intent.getStringExtra("patientName"));

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, nextIntent,
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Time to take Medicine!")
                .setContentText(medicine)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(medicine))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSmallIcon(R.drawable.amlodipine)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        int notificationId = new Random().nextInt();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(notificationId, builder.build());
    }
}
