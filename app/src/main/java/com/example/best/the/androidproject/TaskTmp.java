package com.example.best.the.androidproject;

/**
 * Created by Michał on 2016-12-04.
 */

public class TaskTmp {
    private String taskName;
    private String taskDescription;
    private String taskDate;

    public TaskTmp(String taskDate, String taskName, String taskDescription) {
        this.taskDate = taskDate;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public String toString() {
        return taskName;
    }
}
