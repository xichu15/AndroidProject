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

        //SQLiteOpenHelper openHelper = new ExampleOpenHelper(this.context);
        // db = openHelper.getWritableDatabase();

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
        return tasks;
    }

    @Override
    public Task findTask(String name) {
        Task task = taskDao.find(name);
        return task;
    }

    @Override
    public long saveTask(Task task) {
        long taskId = 0;

        try {
            db.beginTransaction();
            taskId = taskDao.save(task);
            db.setTransactionSuccessful();
            } catch(SQLException e) {
                Log.e("save task error", "Blad przy zapisie zadania", e);
                taskId = 0;
            } finally {
                db.endTransaction();
            }
            return taskId;
    }

    @Override
    public boolean updateTask(Task task, long taskId) {
        saveTask(task);
        deleteTask(getTask(taskId));
        return true;
    }

    @Override
    public void deleteTask(Task task) {
        try {
            db.beginTransaction();
            taskDao.delete(task);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("delete task error", "Błąd przy usuwaniu zadania", e);
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public TaskPeriodicity getTaskPeriodicity(long taskPeriodicityId) {
        return taskPeriodicityDao.get(taskPeriodicityId);
    }

    @Override
    public List<TaskPeriodicity> getAllTaskPeriodicty() {
        return taskPeriodicityDao.getAll();
    }

    @Override
    public TaskPeriodicity findTaskPeriodicity(String name) {
        TaskPeriodicity taskPeriodicity = taskPeriodicityDao.find(name);
        return taskPeriodicity;
    }

    @Override
    public long saveTaskPeriodicity(TaskPeriodicity taskPeriodicity) {
        long taskPeriodicityId = 0;
        taskPeriodicityId = taskPeriodicityDao.save(taskPeriodicity);
        return taskPeriodicityId;
    }

    @Override
    public boolean updateTaskPeriodicity(TaskPeriodicity taskPeriodicity, long taskPeriodicityId) {
        saveTaskPeriodicity(taskPeriodicity);
        deleteTaskPeriodicity(taskPeriodicityDao.get(taskPeriodicityId));
        return true;
    }

    @Override
    public void deleteTaskPeriodicity(TaskPeriodicity taskPeriodicity) {
        taskPeriodicityDao.delete(taskPeriodicity);
    }

    @Override
    public TaskType getTaskType(long taskTypeId) {
        return taskTypeDao.get(taskTypeId);
    }

    @Override
    public List<TaskType> getAllTaskType() {
        return taskTypeDao.getAll();
    }

    @Override
    public TaskType findTaskType(String name) {
        return taskTypeDao.find(name);
    }

    @Override
    public long saveTaskType(TaskType taskType) {
        long taskTypeId = 0;
        taskTypeId = taskTypeDao.save(taskType);
        return taskTypeId;
    }

    @Override
    public boolean updateTaskType(TaskType taskType, long taskTypeId) {
        saveTaskType(taskType);
        deleteTaskPeriodicity(taskPeriodicityDao.get(taskTypeId));
        return true;
    }

    @Override
    public void deleteTaskType(TaskType taskType) {
        taskTypeDao.delete(taskType);
    }

    @Override
    public TaskPriority getTaskPriority(long taskPriorityId) {
        return taskPriorityDao.get(taskPriorityId);
    }

    @Override
    public List<TaskPriority> getAllTaskPriority() {
        return taskPriorityDao.getAll();
    }

    @Override
    public TaskPriority findTaskPriority(String name) {
        return taskPriorityDao.find(name);
    }

    @Override
    public long saveTaskPriority(TaskPriority taskPriority) {
        long taskPriorityId = 0;
        taskPriorityDao.save(taskPriority);
        return taskPriorityId;
    }

    @Override
    public boolean updateTaskPriority(TaskPriority taskPriority, long taskPriorityId) {
        saveTaskPriority(taskPriority);
        deleteTaskPriority(taskPriorityDao.get(taskPriorityId));
        return true;
    }

    @Override
    public void deleteTaskPriority(TaskPriority taskPriority) {
        taskPriorityDao.delete(taskPriority);
    }
}
