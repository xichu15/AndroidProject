package com.example.best.the.androidproject.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michał on 2016-12-11.
 */

public class TaskType {
    private long id;
    private String name;
    List<Task> tasks;

    public TaskType(){ this.tasks = new ArrayList<>(); }

    public TaskType(List<Task> tasks, String name, long id) {
        this.tasks = tasks;
        this.name = name;
        this.id = id;
    }

    public TaskType(long id, String name) {
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
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

        TaskType taskType = (TaskType) o;

        if (id != taskType.id) return false;
        if (name != null ? !name.equals(taskType.name) : taskType.name != null) return false;
        return tasks != null ? tasks.equals(taskType.tasks) : taskType.tasks == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tasks != null ? tasks.hashCode() : 0);
        return result;
    }
}
