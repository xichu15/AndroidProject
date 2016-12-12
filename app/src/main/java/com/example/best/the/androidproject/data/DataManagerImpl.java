package com.example.best.the.androidproject.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.best.the.androidproject.model.Task;
import com.example.best.the.androidproject.model.TaskPeriodicity;
import com.example.best.the.androidproject.model.TaskPriority;
import com.example.best.the.androidproject.model.TaskType;

import java.util.List;

/**
 * Created by Michał on 2016-12-11.
 */

public class DataManagerImpl implements DataManager {

    private static final int DATABASE_VERSION = 1;

    private Context context;

    private SQLiteDatabase db;
    private TaskDao taskDao;
    private TaskPeriodicityDao taskPeriodicityDao;
    private TaskPriorityDao taskPriorityDao;
    private TaskTypeDao taskTypeDao;

    public DataManagerImpl(Context context) {
        this.context = context;

        SQLiteOpenHelper openHelper = new ExampleOpenHelper(this.context);
        db = openHelper.getWritableDatabase();

        taskDao = new TaskDao(db);
        taskPeriodicityDao = new TaskPeriodicityDao(db);
        taskPriorityDao = new TaskPriorityDao(db);
        taskTypeDao = new TaskTypeDao(db);
    }

    @Override
    public Task getTask(long taskId) {
        Task task = taskDao.get(taskId);
        return task;
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = taskDao.getAll();
        return task;
    }

    @Override
    public Task findTask(String name) {
        Task task = taskDao.find(name);
        return task;
    }

    @Override
    public long saveTask(Task task) {
        return 0;
    }

    @Override
    public boolean updateTask(Task task, long taskId) {
        return false;
    }

    @Override
    public void deleteTask(Task task) {

    }

    @Override
    public TaskPeriodicity getTaskPeriodicity(long taskPeriodicityId) {
        return null;
    }

    @Override
    public List<TaskPeriodicity> getAllTaskPeriodicty() {
        return null;
    }

    @Override
    public TaskPeriodicity findTaskPeriodicity(String name) {
        return null;
    }

    @Override
    public long saveTaskPeriodicity(TaskPeriodicity taskPeriodicity) {
        return 0;
    }

    @Override
    public boolean updateTaskPeriodicity(TaskPeriodicity taskPeriodicity, long taskPeriodicityId) {
        return false;
    }

    @Override
    public void deleteTaskPeriodicity(TaskPeriodicity taskPeriodicity) {

    }

    @Override
    public TaskType getTaskType(long taskTypeId) {
        return null;
    }

    @Override
    public List<TaskType> getAllTaskType() {
        return null;
    }

    @Override
    public TaskType findTaskType(String name) {
        return null;
    }

    @Override
    public long saveTaskType(TaskType taskType) {
        return 0;
    }

    @Override
    public boolean updateTaskType(TaskType taskType, long taskTypeId) {
        return false;
    }

    @Override
    public void deleteTaskType(TaskType taskType) {

    }

    @Override
    public TaskPriority getTaskPriority(long taskPriorityId) {
    }

    @Override
    public List<TaskPriority> getAllTaskPriority() {
        return null;
    }

    @Override
    public TaskPriority findTaskPriority(String name) {
        return null;
    }

    @Override
    public long saveTaskPriority(TaskPriority taskPriority) {
        return 0;
    }

    @Override
    public boolean updateTaskPriority(TaskPriority taskPriority, long taskPriorityId) {
        return false;
    }

    @Override
    public void deleteTaskPriority(TaskPriority taskPriority) {

    }
}
