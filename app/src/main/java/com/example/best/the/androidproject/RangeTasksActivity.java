package com.example.best.the.androidproject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.best.the.androidproject.data.DataManager;
import com.example.best.the.androidproject.data.DataManagerImpl;
import com.example.best.the.androidproject.model.Task;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RangeTasksActivity extends AppCompatActivity implements TaskDescription.OnFragmentInteractionListener {
    Button startDateButton;
    Button endDateButton;
    DataManagerImpl dataManager;
    List<Task> taskList;
    TaskAdapter adapter;
    Task taskOne = new Task(1,"first task","description of first task",Calendar.getInstance());
    Task taskTwo = new Task(2,"Second task","description of second task",Calendar.getInstance());
    Task taskThree = new Task(3,"Third task","description of third task",Calendar.getInstance());

    int yearS, monthS, dayS, yearE, monthE, dayE;

    static final int STARTDATEDIALOGID = 0;
    static final int ENDDATEDIALOGID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range_tasks);

        final FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        TaskDescription fragment1 = new TaskDescription();
        ft1.replace(R.id.currentTaskDescription2, fragment1);
        ft1.addToBackStack(null);
        ft1.commit();


        final Calendar currentDate = Calendar.getInstance();
        yearS = currentDate.get(Calendar.YEAR);
        yearE = currentDate.get(Calendar.YEAR);
        monthS = currentDate.get(Calendar.MONTH);
        monthE = currentDate.get(Calendar.MONTH);
        dayS = currentDate.get(Calendar.DAY_OF_MONTH);
        dayE = currentDate.get(Calendar.DAY_OF_MONTH);

        showDateDialogOnButtonsClick();

       // dataManager = new DataManagerImpl(this);
        taskList = new ArrayList<>();
        taskList.clear();
        taskList.add(taskOne);
        taskList.add(taskTwo);
        taskList.add(taskThree);
        //taskList.addAll(dataManager.getAllTasks());
        adapter = new TaskAdapter(this, R.layout.task_item, taskList);
        ListView listView = (ListView) findViewById(R.id.rangeTasksListView);
        listView.setAdapter(adapter);
    }

    private void showDateDialogOnButtonsClick() {
        startDateButton = (Button) findViewById(R.id.startDateButton);
        endDateButton = (Button) findViewById(R.id.endDateButton);

        startDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(STARTDATEDIALOGID);
            }
        });

        endDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(ENDDATEDIALOGID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == STARTDATEDIALOGID) {
            return new DatePickerDialog(this, dpickerListener1, yearS, monthS, dayS);
        }
        if (id == ENDDATEDIALOGID) {
            return new DatePickerDialog(this,dpickerListener2, yearE, monthE, dayE);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener1 = new
            DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfTheYear, int dayOfMonth) {
                    yearS = year;
                    monthS = monthOfTheYear + 1;
                    dayS = dayOfMonth;
                    Toast.makeText(RangeTasksActivity.this,"Start Date selected: " + dayS + "/" + monthS + "/" + yearS,Toast.LENGTH_LONG).show();
                }
            };

    private void actualizeData() {
        Calendar startDate= Calendar.getInstance();
        startDate.set(yearS, monthS - 1,dayS);
        Calendar endDate= Calendar.getInstance();
        endDate.set(yearE, monthE - 1,dayE);

        //taskList.addAll(dataManager.getAllTasks());
        taskList.clear();
        taskList.add(taskOne);
        taskList.add(taskTwo);
        taskList.add(taskThree);
        List<Task> tmpList = getTasksFromRange(startDate,endDate, taskList);
        taskList = tmpList;
        adapter.updateTaskList(taskList);
    }

    private DatePickerDialog.OnDateSetListener dpickerListener2 = new
            DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfTheYear, int dayOfMonth) {
                    yearE = year;
                    monthE = monthOfTheYear + 1;
                    dayE = dayOfMonth;
                    Toast.makeText(RangeTasksActivity.this,"End Date selected: " + dayE + "/" + monthE + "/" + yearE,Toast.LENGTH_LONG).show();
                    actualizeData();
                }
            };

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private class TaskAdapter extends ArrayAdapter<Task> {

        private ArrayList<Task> taskList;

        public TaskAdapter(Context context, int textViewResourceId, List<Task> taskList) {
            super(context, textViewResourceId, taskList);
            this.taskList = new ArrayList<>();
            this.taskList.addAll(taskList);
        }

        private class ViewHolder{
            TextView taskName;
            Button buttonDelete;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            View v = convertView;

            if (v == null) {
                 LayoutInflater vi = LayoutInflater.from(getContext());
                 v = vi.inflate(R.layout.task_item, null);

                 holder = new ViewHolder();
                 holder.taskName = (TextView) v.findViewById(R.id.TaskNameTextViewUnique);
                 holder.buttonDelete = (Button) v.findViewById(R.id.buttonDeleteTask);
                 v.setTag(holder);

                 final Task task = taskList.get(position);

                 holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         dataManager.deleteTask(dataManager.getTask(task.getId()));
                         finish();
                         startActivity(getIntent());
                     }
                 });

                 holder.taskName.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        TextView descriptionOfTask = (TextView) findViewById(R.id.taskDescriptionInFragment);
                        descriptionOfTask.setText(task.getDate().get(Calendar.DAY_OF_MONTH) +
                                "/" + task.getDate().get(Calendar.MONTH) + "/" +
                               task.getDate().get(Calendar.YEAR) +
                                "\n" + task.getDescription());
                    }
                });

            } else {
                holder = (ViewHolder) v.getTag();
            }
                Task task = taskList.get(position);
                holder.taskName.setText(task.getName());

            return v;

        }

        public void updateTaskList(List<Task> newlist) {
            taskList.clear();
            taskList.addAll(newlist);
            this.notifyDataSetChanged();
        }
    }

    public List<Task> getTasksFromRange(Calendar startDate, Calendar endDate,List<Task> taskList) {
        List<Task> rangeTasks = new ArrayList<>();
        System.out.println("before select: ");
        for(Task t: taskList) {
            System.out.println(t.getDate().get(Calendar.DAY_OF_MONTH) + "/" +
                    t.getDate().get(Calendar.MONTH) + "/" +
                    t.getDate().get(Calendar.YEAR) + " Milis: " + t.getDate().getTimeInMillis());
        }

        for (Task task : taskList) {
            if (isTaskFromRange(startDate, endDate, task)) {
                rangeTasks.add(task);
            }
        }
        System.out.println("after select: ");
        for(Task t: rangeTasks) {
            System.out.println(t.getDate().get(Calendar.DAY_OF_MONTH) + "/" +
            t.getDate().get(Calendar.MONTH) + "/" +
            t.getDate().get(Calendar.YEAR)+ " Milis: " + t.getDate().getTimeInMillis());
        }
        return rangeTasks;
    }

    private boolean isTaskFromRange(Calendar startDate, Calendar endDate, Task task) {
        if((startDate.getTimeInMillis() <= task.getDate().getTimeInMillis()) && (task.getDate().getTimeInMillis() <= endDate.getTimeInMillis()))
            return true;
        return false;
    }
}
