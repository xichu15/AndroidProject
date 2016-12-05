package com.example.best.the.androidproject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by NP550P5C-SO4PL on 2016-12-04.
 */

public class TaskList{

    private static List<TestTask> taskList = new ArrayList<>();

    public TaskList(){}

    public void addTask(TestTask newTask){
        taskList.add(newTask);
    }

    public List<TestTask> getTaskList(){
        return taskList;
    }

    public List<TestTask> getCurrentTasks(){
        List<TestTask> currentTasks = new ArrayList<>();
        for(TestTask task : taskList){
            if(isCurrentTask(task)){
                currentTasks.add(task);
            }
        }
        return currentTasks;
    }

    private boolean isCurrentTask(TestTask task){
        Calendar today = Calendar.getInstance(); today.setTime(new Date());
        if(today.get(Calendar.YEAR) == task.getTaskStartDate().get(Calendar.YEAR)
                && today.get(Calendar.MONTH) == (task.getTaskStartDate().get(Calendar.MONTH))
                && today.get(Calendar.DAY_OF_MONTH) == task.getTaskStartDate().get(Calendar.DAY_OF_MONTH)){
            return true;
        } else{
            return false;
        }

    }
}
