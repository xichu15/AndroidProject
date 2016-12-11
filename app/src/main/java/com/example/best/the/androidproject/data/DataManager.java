package com.example.best.the.androidproject.data;

import com.example.best.the.androidproject.model.Task;
import com.example.best.the.androidproject.model.TaskPeriodicity;
import com.example.best.the.androidproject.model.TaskPriority;
import com.example.best.the.androidproject.model.TaskType;

import java.util.List;

/**
 * Created by Michał on 2016-12-11.
 */

public interface DataManager {
    public Task getTask(long taskId);
    public List<Task> getAllTasks();
    public Task findTask(String name);
    public long saveTask(Task task);
    public boolean updateTask(Task task, long taskId);
    public void deleteTask(Task task);

    public TaskPeriodicity getTaskPeriodicity(long taskPeriodicityId);
    public List<TaskPeriodicity> getAllTaskPeriodicty();
    public TaskPeriodicity findTaskPeriodicity(String name);
    public long saveTaskPeriodicity(TaskPeriodicity taskPeriodicity);
    public boolean updateTaskPeriodicity(TaskPeriodicity taskPeriodicity, long taskPeriodicityId);
    public void deleteTaskPeriodicity(TaskPeriodicity taskPeriodicity);

    public TaskType getTaskType(long taskTypeId);
    public List<TaskType> getAllTaskType();
    public TaskType findTaskType(String name);
    public long saveTaskType(TaskType taskType);
    public boolean updateTaskType(TaskType taskType, long taskTypeId);
    public void deleteTaskType(TaskType taskType);

    public TaskPriority getTaskPriority(long taskPriorityId);
    public List<TaskPriority> getAllTaskPriority();
    public TaskPriority findTaskPriority(String name);
    public long saveTaskPriority(TaskPriority taskPriority);
    public boolean updateTaskPriority(TaskPriority taskPriority, long taskPriorityId);
    public void deleteTaskPriority(TaskPriority taskPriority);
}
