package com.example.best.the.androidproject;

import java.util.Calendar;

/**
 * Created by NP550P5C-SO4PL on 2016-12-04.
 */

public class TestTask {

    private String taskName;
    private Calendar taskStartDate;
    private String taskDescription;
    private boolean notified = false;

    public TestTask(String name, Calendar taskStartDate, String taskDescription){
        this.taskName = name;
        this.taskStartDate = taskStartDate;
        this.taskDescription = taskDescription;
    }


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Calendar getTaskStartDate() {
        return taskStartDate;
    }

    public void setTaskStartDate(Calendar startDate) {
        this.taskStartDate = startDate;
    }

    @Override
    public String toString(){
        return this.taskName;
    }

    public String getDateString(){
        return taskStartDate.getTime().toString();
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isNotified() {
        return notified;
    }

    public void setNotified(boolean notified) {
        this.notified = notified;
    }
}
