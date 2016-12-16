package com.example.best.the.androidproject.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michał on 2016-12-11.
 */

public class TaskPriority {
    private long id;
    private String name;
    List<Task> tasks;

    public TaskPriority(){this.tasks = new ArrayList<>();}

    public TaskPriority(long id, String name, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
    }

    public TaskPriority(long id, String name) {
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

        TaskPriority that = (TaskPriority) o;

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
