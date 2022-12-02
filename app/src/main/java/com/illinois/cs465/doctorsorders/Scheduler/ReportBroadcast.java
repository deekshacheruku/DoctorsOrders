package com.illinois.cs465.doctorsorders.Scheduler;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.drawable.IconCompat;

import java.util.Random;

public class ReportBroadcast extends BroadcastReceiver {
    String CHANNEL_ID = "123";
    Bundle medications;
    Bundle lastTaken;
    String patientName;

    @Override
    public void onReceive(Context context, Intent intent) {

        medications = intent.getBundleExtra("medications");
        lastTaken = intent.getBundleExtra("lastTaken");
        patientName = intent.getStringExtra("patientName");

        String medList = "";
        for(int i = 0; i < medications.getInt("numOfMeds"); i++)
        {
            medList += medications.getString("medicine"+i);
            medList += "\n";
        }
    Log.d("Expected Med List:", medList);
        String lastUpdate = lastTaken.getString("mostRecent") + " taken at " + lastTaken.getString("date");

        NotificationCompat.Builder repBuilder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            repBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setContentTitle(patientName + "\'s Scheduled Medicine:\n")
                    .setContentText(medList + lastUpdate)
                    .setWhen(System.currentTimeMillis())
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(medList + lastUpdate))
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setTimeoutAfter(System.currentTimeMillis() + 61000)
                    .setAutoCancel(true);
        }

//        String medicine = intent.getStringExtra("medicine");
//        int resId = context.getResources().getIdentifier(medicine, "drawable", context.getPackageName());
//        Bitmap image = BitmapFactory.decodeResource(context.getResources(), resId);
//
//        Intent nextIntent = new Intent(context, PatientMedicineDisplay.class);
//        nextIntent.putExtra("medicine", medicine);
//        nextIntent.putExtra("pills", intent.getStringExtra("pills"));
//        nextIntent.putExtra("instructions", intent.getStringExtra("instructions"));
//        nextIntent.putExtra("patientName", intent.getStringExtra("patientName"));
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, nextIntent,
//                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);


//        NotificationCompat.Builder builder = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            builder = new NotificationCompat.Builder(context, CHANNEL_ID)
//                    .setContentTitle("Time to take Medicine!")
//                    .setContentText(medicine.toUpperCase())
//                    .setWhen(System.currentTimeMillis() + 61000)
//                    .setUsesChronometer(true)
//                    .setChronometerCountDown(true)
//                    .setSmallIcon(IconCompat.createWithBitmap(image))
//                    .setStyle(new NotificationCompat.BigTextStyle().bigText(medicine.toUpperCase()))
//                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(image))
//                    .setPriority(NotificationCompat.PRIORITY_MAX)
//                    .setTimeoutAfter(System.currentTimeMillis() + 61000)
//                    .setContentIntent(pendingIntent)
//                    .setAutoCancel(true);
//        }

        int notificationId = new Random().nextInt();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(notificationId, repBuilder.build());
    }
}
