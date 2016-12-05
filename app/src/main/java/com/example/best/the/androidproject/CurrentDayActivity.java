package com.example.best.the.androidproject;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CurrentDayActivity extends AppCompatActivity {

    ListView listView;
    TaskList arrayList = new TaskList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_day);
        listView = (ListView) findViewById(R.id.currentDayTasksListView);

        ArrayAdapter adapter = new ArrayAdapter<TestTask>(this, android.R.layout.simple_list_item_1,
                arrayList.getCurrentTasks());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TestTask clickedTask = (TestTask) listView.getItemAtPosition(i);
                TextView txt = (TextView) findViewById(R.id.tmpTextView);
                txt.setText(clickedTask.getDateString() + " - " + clickedTask.getTaskDescription());
            }
        });

    }
}
