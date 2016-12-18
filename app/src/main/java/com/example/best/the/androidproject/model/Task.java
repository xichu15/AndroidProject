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
    private Calendar date;
    private TaskPeriodicity taskPeriodicity;
    private TaskType taskType;
    private TaskPriority taskPriority;

    public Task(){}

    @Override
    public String toString() {
        return this.getName();
    }

    public Task(long id, String name, String description, TaskPeriodicity taskPeriodicity, Calendar date, TaskPriority taskPriority, TaskType taskType) {
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
        this.taskPeriodicity = null;
        this.taskPriority = null;
        this.taskType = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public TaskPeriodicity getTaskPeriodicity() {
        return taskPeriodicity;
    }

    public void setTaskPeriodicity(TaskPeriodicity taskPeriodicity) {
        this.taskPeriodicity = taskPeriodicity;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setDateFromMilis(long timeInMilis) { this.date.setTimeInMillis(timeInMilis); }

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
        if (taskPeriodicity != null ? !taskPeriodicity.equals(task.taskPeriodicity) : task.taskPeriodicity != null)
            return false;
        if (taskType != null ? !taskType.equals(task.taskType) : task.taskType != null)
            return false;
        return taskPriority != null ? taskPriority.equals(task.taskPriority) : task.taskPriority == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (taskPeriodicity != null ? taskPeriodicity.hashCode() : 0);
        result = 31 * result + (taskType != null ? taskType.hashCode() : 0);
        result = 31 * result + (taskPriority != null ? taskPriority.hashCode() : 0);
        return result;
    }
}
