package com.example.best.the.androidproject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.example.best.the.androidproject.data.DataManager;
import com.example.best.the.androidproject.data.DataManagerImpl;
import com.example.best.the.androidproject.model.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class NotificationService extends Service {

    private static final int HOUR_IN_MILIS = 3600000;
    private static final long MINUTE_IN_MILIS = 60000;
    private ArrayList<Long> notified = new ArrayList<>();

    NotificationCompat.Builder mBuilder;
    ArrayList<Task> taskList;
    DataManager dataManager;


    public NotificationService() {
    }

    @Override
    public void onCreate(){
        mBuilder = new NotificationCompat.Builder(this);
        dataManager = new DataManagerImpl(this);
        taskList = new ArrayList<>();
        taskList.addAll(dataManager.getAllTasks());
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
                    taskList.clear();
                    taskList.addAll(dataManager.getAllTasks());
                    try {
                        for(Task task : taskList){
                            if(isHourToGo(task)){
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

    private boolean isHourToGo(Task task){
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());

        long diff = task.getDate().getTimeInMillis() - today.getTimeInMillis();

        return diff > 0 && diff < HOUR_IN_MILIS;
    }

    private void sendNotify(Task task){
        if(!notified.contains(task.getId())){
            notified.add(task.getId());

            mBuilder.setSmallIcon(R.drawable.noti_logo)
                    .setContentTitle("Wydarzenie o nazwie " + task.getName())
                    .setContentText("W ciągu godziny odbędzie się zaplanowane wydarzenie!");

            Intent notificationIntent = new Intent(this, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(contentIntent);

            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            Random random = new Random();
            int notifyId = random.nextInt(9999 - 1000) + 1000;
            manager.notify(notifyId, mBuilder.build());
        }
    }

    @Override
    public void onDestroy(){
        stopSelf();
    }

}
