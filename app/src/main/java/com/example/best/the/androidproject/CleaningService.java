package com.example.best.the.androidproject;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import com.example.best.the.androidproject.data.DataManager;
import com.example.best.the.androidproject.data.DataManagerImpl;
import com.example.best.the.androidproject.model.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class CleaningService extends Service {
    ArrayList<Task> taskList;
    DataManager dataManager;

    public CleaningService() {
    }

    public static final long NOTIFY_INTERVAL = 60 * 1000; // 60 seconds

    private final IBinder mBinder = new LocalBinder();
    private Handler counterHandler = new Handler();
    private Handler timerhandler = new Handler();
    private Timer mTimer = null;
    private Integer removedTasks = 0;

    public class LocalBinder extends Binder {
        CleaningService getService(){
            return CleaningService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        if (mTimer != null) {
            mTimer.cancel();
        } else {
            mTimer = new Timer();
        }

        dataManager = new DataManagerImpl(this);
        taskList = new ArrayList<>();
        taskList.addAll(dataManager.getAllTasks());

        Toast.makeText(getApplicationContext(), "Bound service has been started",
                Toast.LENGTH_SHORT).show();

        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0, NOTIFY_INTERVAL);
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent){
        Toast.makeText(getApplicationContext(), "Bound service has been stopped", Toast.LENGTH_SHORT).show();
        mTimer.cancel();
        return true;
    }

    @Override
    public void onDestroy(){
        mTimer.cancel();
    }

    public void getCounter(){
        counterHandler.post(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), "Removed tasks " + removedTasks.toString() + " times", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class TimeDisplayTimerTask extends TimerTask {

        @Override
        public void run() {
            timerhandler.post(new Runnable() {

                @Override
                public void run() {
                    Date currentDate = new Date();
                    for(Task task : taskList){
                        if(currentDate.after(task.getDate().getTime())){
                            dataManager.deleteTask(task);
                            removedTasks++;
                        }
                    }
                    Toast.makeText(getApplicationContext(), "Bound service is still working",
                            Toast.LENGTH_SHORT).show();
                }

            });
        }
    }
}
