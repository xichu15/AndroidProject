package com.example.best.the.androidproject.model;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by Michał on 2016-12-11.
 */

public class Task {
    private long id;
    private String name;
    private String description;
    private Calendar date = Calendar.getInstance();
    private long taskPeriodicity;
    private long taskType;
    private long taskPriority;

    public Task(){}

    @Override
    public String toString() {
        return this.getName();
    }

    public Task(long id, String name, String description, long taskPeriodicity, Calendar date, long taskPriority, long taskType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.taskPeriodicity = taskPeriodicity;
        this.date = date;
        this.taskPriority = taskPriority;
        this.taskType = taskType;
    }

    public Task(long id, String name, String description, Calendar date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.taskPeriodicity = 0;
        this.taskPriority = 0;
        this.taskType = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(long taskPriority) {
        this.taskPriority = taskPriority;
    }

    public long getTaskType() {
        return taskType;
    }

    public void setTaskType(long taskType) {
        this.taskType = taskType;
    }

    public long getTaskPeriodicity() {
        return taskPeriodicity;
    }

    public void setTaskPeriodicity(long taskPeriodicity) {
        this.taskPeriodicity = taskPeriodicity;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setDateFromMilis(long time) { this.date.setTimeInMillis(time);  }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        if (description != null ? !description.equals(task.description) : task.description != null)
            return false;
        if (date != null ? !date.equals(task.date) : task.date != null) return false;
        if (taskPeriodicity != 0 ? taskPeriodicity != task.taskPeriodicity : task.taskPeriodicity != 0)
            return false;
        if (taskType != 0 ? taskType != task.taskType : task.taskType != 0)
            return false;
        return taskPriority != 0 ? taskPriority == task.taskPriority : task.taskPriority == 0;

    }
}
