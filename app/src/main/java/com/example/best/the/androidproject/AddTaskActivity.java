package com.example.best.the.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.best.the.androidproject.data.DataManager;
import com.example.best.the.androidproject.data.DataManagerImpl;
import com.example.best.the.androidproject.model.Task;
import com.example.best.the.androidproject.model.TaskPeriodicity;
import com.example.best.the.androidproject.model.TaskPriority;
import com.example.best.the.androidproject.model.TaskType;

import java.security.acl.Group;
import java.util.ArrayList;

public class AddTaskActivity extends AppCompatActivity {

    private EditText taskName;
    private EditText taskDescription;
    private EditText taskHour;
    private EditText taskMinute;
    private Spinner taskType;
    private Spinner taskPriority;
    private Spinner taskPeriodicity;
    private CalendarView taskDate;
    ArrayList<TaskType> typeList;
    ArrayList<TaskPriority> priorityList;
    ArrayList<TaskPeriodicity> periodicityList;

    private DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        setComponents();
    }

    private void setComponents(){
        taskName = (EditText) findViewById(R.id.taskName);
        taskDescription = (EditText) findViewById(R.id.taskDescription);
        taskMinute = (EditText) findViewById(R.id.taskMinutes);
        taskHour = (EditText) findViewById(R.id.taskHours);

        taskType = (Spinner) findViewById(R.id.taskType);
        taskPriority = (Spinner) findViewById(R.id.taskPriority);
        taskPeriodicity = (Spinner) findViewById(R.id.taskPeriodicity);

        taskDate = (CalendarView) findViewById(R.id.taskDate);

        dataManager = new DataManagerImpl(this);

        createLists();
    }

    private void createLists(){
        typeList = new ArrayList<>();
        typeList.clear();
        typeList.addAll(dataManager.getAllTaskType());

        priorityList = new ArrayList<>();
        priorityList.clear();
        priorityList.addAll(dataManager.getAllTaskPriority());

        periodicityList = new ArrayList<>();
        periodicityList.clear();
        periodicityList.addAll(dataManager.getAllTaskPeriodicty());

        ArrayAdapter adapterType = new ArrayAdapter<>(this, R.layout.spinner_item, typeList);
        ArrayAdapter adapterPriority = new ArrayAdapter<>(this, R.layout.spinner_item, priorityList);
        ArrayAdapter adapterPeriodicity = new ArrayAdapter<>(this, R.layout.spinner_item, periodicityList);

        taskType.setAdapter(adapterType);
        taskPriority.setAdapter(adapterPriority);
        taskPeriodicity.setAdapter(adapterPeriodicity);
    }

    public void addTask(View view){

        if(isAllFieldsFilled()){
            Task newTask = new Task();
            newTask.setName(taskName.getText().toString());
            newTask.setDescription(taskDescription.getText().toString());

            long taskTimeInMilis = taskDate.getDate();
            taskTimeInMilis += Long.parseLong(taskMinute.getText().toString()) * 60000;
            taskTimeInMilis += Long.parseLong(taskHour.getText().toString()) * 3600000;
            newTask.setDateFromMilis(taskTimeInMilis);

            newTask.setTaskType((TaskType) taskType.getSelectedItem());
            newTask.setTaskPriority((TaskPriority) taskPriority.getSelectedItem());
            newTask.setTaskPeriodicity((TaskPeriodicity) taskPeriodicity.getSelectedItem());

            dataManager.saveTask(newTask);
            Toast.makeText(getApplicationContext(), "Task has been added", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), AddTaskActivity.class);
            finish();
            startActivity(intent);
        } else{
            Toast.makeText(getApplicationContext(), "Fill all empty fields first", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isAllFieldsFilled(){
        return taskName.getText().toString().trim().length() > 0
                && taskDescription.getText().toString().trim().length() > 0
                && taskMinute.getText().toString().trim().length() > 0
                && taskHour.getText().toString().trim().length() > 0;
    }
}
