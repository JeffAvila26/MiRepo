package com.example.ioon017.swipe2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.wafel.skald.api.LogcatAppender;

public class MyFirebaseMessagingService extends FirebaseMessagingService {



    public MyFirebaseMessagingService() {
    }




    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int NOTIFICATION_ID = 234;

            NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

            String CHANNEL_ID = "my_channel_01";


            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

                CharSequence name = "my_channel";
                String Description = "Channel destinado a AMIR";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                mChannel.setDescription(Description);
                mChannel.enableLights(true);
                mChannel.setLightColor(Color.RED);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                mChannel.setShowBadge(false);

                if (notificationManager != null) {
                    notificationManager.createNotificationChannel(mChannel);
                }
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(remoteMessage.getNotification().getTitle())
                    .setContentText(remoteMessage.getNotification().getBody());

            Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setContentIntent(resultPendingIntent);

            notificationManager.notify(NOTIFICATION_ID, builder.build());


        }else{

            Notification notification  = new Notification.Builder(getApplication())
                    .setCategory(Notification.CATEGORY_MESSAGE)
                    .setContentTitle(remoteMessage.getNotification().getTitle())
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(false)
                    .setVisibility(Notification.VISIBILITY_PUBLIC)
                    .build();

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(123, notification );


        }



        ConexionSQLiteHelper db = new ConexionSQLiteHelper(this);
        db.guardarNoti(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());



       Intent i = new Intent(getBaseContext(),BaseDatosService.class);
        i.putExtra("titulo",remoteMessage.getNotification().getTitle());
        i.putExtra("descripcion", remoteMessage.getNotification().getBody());
        getBaseContext().startService(i);
        BaseDatosService baseDatosService = new BaseDatosService(getBaseContext(), remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());


    }



    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }
        }
