package com.example.best.the.androidproject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class NotificationService extends Service {

    private static final int HOUR_IN_MILIS = 3600000;
    private static final long MINUTE_IN_MILIS = 60000;

    NotificationCompat.Builder mBuilder;
    TaskList taskList;


    public NotificationService() {
    }

    @Override
    public void onCreate(){
        mBuilder = new NotificationCompat.Builder(this);
        taskList = new TaskList();
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        for(TestTask task : taskList.getTaskList()){
                            if(isHourToGo(task) && !task.isNotified()){
                                sendNotify(task);
                            }
                        }
                        Thread.sleep(MINUTE_IN_MILIS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        return START_STICKY;
    }

    private boolean isHourToGo(TestTask task){
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());

        long diff = task.getTaskStartDate().getTimeInMillis() - today.getTimeInMillis();

        return diff > 0 && diff < HOUR_IN_MILIS;
    }

    private void sendNotify(TestTask task){
        task.setNotified(true);

        mBuilder.setSmallIcon(R.drawable.noti_logo)
                .setContentTitle("Wydarzenie o nazwie " + task.getTaskName())
                .setContentText("W ciągu godziny odbędzie się zaplanowane wydarzenie!");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Get unique ID for notification
        Random random = new Random();
        int notifyId = random.nextInt(9999 - 1000) + 1000;
        manager.notify(notifyId, mBuilder.build());
    }

    @Override
    public void onDestroy(){
        stopSelf();
    }

}
