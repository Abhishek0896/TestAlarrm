package com.example.testalarm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class MyAlarm extends BroadcastReceiver {
    MediaPlayer mediaPlayer;
    Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MYTAG","CALLED RECIEVER");
        this.context =context;
         mediaPlayer = MediaPlayer.create(context,
                R.raw.jb);
        play();
        showNotification();
    }

    public void showNotification(){


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


            String NOTIFICATION_CHANNEL_ID = "com.example.simpleapp";
            String channelName = "My Background Service";
            NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
            chan.setLightColor(Color.BLUE);
            chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            assert manager != null;
            manager.createNotificationChannel(chan);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
            Notification notification = notificationBuilder.setOngoing(true)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("App is running in background")
//                    .addAction(new NotificationCompat.Action(android.R.drawable.ic_media_play, "play", playIntent))
//                    .addAction(new NotificationCompat.Action(android.R.drawable.ic_media_pause, "pause", pauseIntent))
//                    .addAction(new NotificationCompat.Action(android.R.drawable.ic_media_rew, "Stop", stopIntent))
                    .setPriority(NotificationManager.IMPORTANCE_MIN)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .build();

        }else{
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channelID");
            builder.setContentTitle("Abhishek Notification")
                    .setContentText("this is demo music player")
//                    .addAction(new NotificationCompat.Action(android.R.drawable.ic_media_play, "play", playIntent))
//                    .addAction(new NotificationCompat.Action(android.R.drawable.ic_media_pause, "pause", pauseIntent))
//                    .addAction(new NotificationCompat.Action(android.R.drawable.ic_media_rew, "Stop", stopIntent))
                    .setPriority(NotificationManager.IMPORTANCE_MIN)
                    .setPriority(NotificationManager.IMPORTANCE_MIN)
                    .setPriority(NotificationManager.IMPORTANCE_MIN)
                    .setSmallIcon(R.mipmap.ic_launcher);
        }
    }

    public void play(){
        Log.d("MYTAG","ON play:");
        mediaPlayer.start();
    }

    public void pause(){
        mediaPlayer.pause();
    }

    public void stop(){
        mediaPlayer.stop();
    }
}
