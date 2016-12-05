package com.example.best.the.androidproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NP550P5C-SO4PL on 2016-12-04.
 */

public class TaskList {

    private static List<TestTask> taskList = new ArrayList<>();

    public TaskList(){}

    public void addTask(TestTask newTask){
        taskList.add(newTask);
    }

    public List<TestTask> getTaskList(){
        return taskList;
    }
}
