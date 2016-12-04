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
import java.util.List;

public class CurrentDayActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_day);
        listView = (ListView) findViewById(R.id.currentDayTasksListView);
        List<TaskTmp> arrayList = new ArrayList<TaskTmp>();
        arrayList.add(new TaskTmp("04-12-2017", "First task", "First task description"));
        arrayList.add(new TaskTmp("04-12-2017", "second task", "second task description"));
        arrayList.add(new TaskTmp("04-12-2017", "third task", "third task description"));
        arrayList.add(new TaskTmp("04-12-2017", "fourth task", "fourth task description"));
        ArrayAdapter adapter = new ArrayAdapter<TaskTmp>(this, android.R.layout.simple_list_item_1,
                arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TaskTmp clickedTask = (TaskTmp) listView.getItemAtPosition(i);
                TextView txt = (TextView) findViewById(R.id.tmpTextView);
                txt.setText(clickedTask.getTaskDescription());
            }
        });

    }
}
