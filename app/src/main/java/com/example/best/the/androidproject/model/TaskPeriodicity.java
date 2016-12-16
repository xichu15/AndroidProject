package com.example.best.the.androidproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Michał on 2016-12-11.
 */

public class TaskPeriodicity {
    private long id;
    private String name;
    List<Task> tasks;

    public TaskPeriodicity(){this.tasks = new ArrayList<>();}

    public TaskPeriodicity(long id, List<Task> tasks, String name) {
        this.id = id;
        this.tasks = tasks;
        this.name = name;
    }

    public TaskPeriodicity(long id, String name) {
        this.id = id;
        this.name = name;
        List<Task> tasks = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskPeriodicity that = (TaskPeriodicity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return tasks != null ? tasks.equals(that.tasks) : that.tasks == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tasks != null ? tasks.hashCode() : 0);
        return result;
    }
}

