package com.example.best.the.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button baton;
    Button buttonToCurrentDayAcitivies;
    TaskList taskList = new TaskList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createTaskList();

        startService(new Intent(getApplicationContext(), NotificationService.class));

        baton = (Button) findViewById(R.id.settingsBtn);
        buttonToCurrentDayAcitivies = (Button) findViewById(R.id.buttonToCurrentDayActivites);

        baton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
            }
        });

        buttonToCurrentDayAcitivies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CurrentDayActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createTaskList(){
        addTaskToList("First", new Date("12/4/2016 18:30:00"), "Desc no 1");
        addTaskToList("Second", new Date("12/5/2016 14:10:00"), "Desc no 2");
        addTaskToList("Third", new Date("12/6/2016 12:00:00"), "Desc no 3");
    }

    private void addTaskToList(String name, Date date, String description){
        Calendar taskDate = Calendar.getInstance();
        taskDate.setTime(date);
        taskList.addTask(new TestTask(name, taskDate, description));
    }
}

