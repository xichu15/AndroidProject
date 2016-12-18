package com.example.best.the.androidproject;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.best.the.androidproject.data.DataManager;
import com.example.best.the.androidproject.model.Task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CurrentDayActivity extends AppCompatActivity implements TaskDescription.OnFragmentInteractionListener {

    ListView listView;
    List<Task> taskList;
    private DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_day);
        listView = (ListView) findViewById(R.id.currentDayTasksListView);

        final FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        TaskDescription fragment1 = new TaskDescription();
        ft1.replace(R.id.currentTaskDescription, fragment1);
        ft1.addToBackStack(null);
        ft1.commit();

        Task taskOne = new Task(1,"first task","description of first task",Calendar.getInstance());
        Task taskTwo = new Task(2,"Second task","description of second task",Calendar.getInstance());
        Task taskThree = new Task(3,"Third task","description of third task",Calendar.getInstance());

        taskList = new ArrayList<>();

        taskList.add(taskOne);
        taskList.add(taskTwo);
        taskList.add(taskThree);

        ArrayAdapter<Task> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                taskList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Task clickedTask = (Task) listView.getItemAtPosition(i);
                TextView txt = (TextView) findViewById(R.id.taskDescriptionInFragment);
                txt.setText(clickedTask.getDate().get(Calendar.DAY_OF_MONTH) +
                        "/" + clickedTask.getDate().get(Calendar.MONTH) + "/" +
                                clickedTask.getDate().get(Calendar.YEAR) +
                        "\n" + clickedTask.getDescription());
            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
