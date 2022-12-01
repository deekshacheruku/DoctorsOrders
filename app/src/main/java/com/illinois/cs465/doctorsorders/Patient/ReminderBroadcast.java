package com.illinois.cs465.doctorsorders.Patient;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.drawable.IconCompat;

import java.util.Random;

public class ReminderBroadcast extends BroadcastReceiver {
    String CHANNEL_ID = "123";

    @Override
    public void onReceive(Context context, Intent intent) {
        String medicine = intent.getStringExtra("medicine");
        int resId = context.getResources().getIdentifier(medicine, "drawable", context.getPackageName());
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), resId);

        Intent nextIntent = new Intent(context, PatientMedicineDisplay.class);
        nextIntent.putExtra("medicine", medicine);
        nextIntent.putExtra("pills", intent.getStringExtra("pills"));
        nextIntent.putExtra("instructions", intent.getStringExtra("instructions"));
        nextIntent.putExtra("patientName", intent.getStringExtra("patientName"));

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, nextIntent,
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setContentTitle("Time to take Medicine!")
                    .setContentText(medicine.toUpperCase())
                    .setWhen(System.currentTimeMillis() + 61000)
                    .setUsesChronometer(true)
                    .setChronometerCountDown(true)
                    .setSmallIcon(IconCompat.createWithBitmap(image))
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(medicine.toUpperCase()))
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(image))
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setTimeoutAfter(System.currentTimeMillis() + 61000)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
        }

        int notificationId = new Random().nextInt();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(notificationId, builder.build());
    }
}
