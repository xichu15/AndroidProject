package com.example.best.the.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
//SNIKI COMMIT
    Button baton;
    Button buttonToCurrentDayAcitivies;
    TaskList taskList = new TaskList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createTaskList();

        startService(new Intent(getApplicationContext(), NotificationService.class));

        //TU BYLAM ~Dudzilla
        baton = (Button) findViewById(R.id.settingsBtn);
        buttonToCurrentDayAcitivies = (Button) findViewById(R.id.buttonToCurrentDayActivites);

        baton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
            }
        });

        buttonToCurrentDayAcitivies.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CurrentDayActivity.class);
                startActivity(intent);
            }
        });

    private void createTaskList(){
        Date d1 = new Date();
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2016, 11, 4, 18, 40);

        /*Date d1 = new Date("12/4/2016 18:40:00");
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(d1);*/

        d1 = new Date("12/4/2016 19:25:00");
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(d1);

        d1 = new Date("12/4/2016 18:10:00");
        Calendar cal3 = Calendar.getInstance();
        cal3.setTime(d1);

        taskList.addTask(new TestTask("First", cal1));
        taskList.addTask(new TestTask("Second", cal2));
        taskList.addTask(new TestTask("Third", cal3));
    }



    }
}
