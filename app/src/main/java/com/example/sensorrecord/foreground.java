package com.example.sensorrecord;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.time.Instant;

public class foreground extends Service
{
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotificationChannel();


        Intent intent1=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivities(this,0, new Intent[]{intent1},0);

        Notification notification=new NotificationCompat.Builder(this,"ChannelId1")
                .setContentTitle("Accelerometer /Gyroscope /Light /Proximity")
                .setContentText("Sensor App is Runing")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent).build();


        startForeground(1,notification);

        return START_STICKY;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O);
            NotificationChannel notificationChannel=new NotificationChannel(
              "ChannelId1","Foreground notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
    return null;
    }

    @Override
    public void onDestroy() {
            stopForeground(true);
            stopSelf();
            super.onDestroy();
        }
    }

