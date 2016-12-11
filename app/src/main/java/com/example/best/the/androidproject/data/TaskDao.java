package com.example.best.the.androidproject.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.example.best.the.androidproject.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NP550P5C-SO4PL on 2016-12-11.
 */

public class TaskDao implements Dao<Task> {

    private static final String INSERT =
            "insert into " + TaskTable.TABLE_NAME + "("
                    + TaskTable.TaskColumns.NAME + ", "
                    + TaskTable.TaskColumns.DATE + ", "
                    + TaskTable.TaskColumns.DESCRIPTION + ", "
                    + TaskTable.TaskColumns.ID_TYPE_FK + ", "
                    + TaskTable.TaskColumns.ID_PRIORITY_FK + ", "
                    + TaskTable.TaskColumns.ID_PERIODICITY_FK
                    + ")" + " values (?,?,?,?,?,?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public TaskDao(SQLiteDatabase db){
        this.db = db;
        insertStatement = db.compileStatement(TaskDao.INSERT);
    }

    @Override
    public long save(Task entity) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, entity.getName());
        insertStatement.bindString(1, entity.getDate());
        insertStatement.bindString(1, entity.getDescription());
        insertStatement.bindString(1, entity.getTaskType());
        insertStatement.bindString(1, entity.getTaskPriority());
        insertStatement.bindString(1, entity.getTaskPeriodicity());
        return insertStatement.executeInsert();
    }

    @Override
    public void update(Task entity) {
        final ContentValues values = new ContentValues();
        values.put(TaskTable.TaskColumns.NAME, entity.getName());
        values.put(TaskTable.TaskColumns.DATE, entity.getDate());
        values.put(TaskTable.TaskColumns.DESCRIPTION, entity.getDescription());
        values.put(TaskTable.TaskColumns.ID_TYPE_FK, entity.getTaskType());
        values.put(TaskTable.TaskColumns.ID_PRIORITY_FK, entity.getTaskPriority());
        values.put(TaskTable.TaskColumns.ID_PERIODICITY_FK, entity.getTaskPeriodicity());
        db.update(TaskTable.TABLE_NAME, values, BaseColumns._ID + " = ?", new String[] { String.valueOf(entity.getId()) });
    }

    @Override
    public void delete(Task entity) {
        if (entity.getId() > 0) {
            db.delete(TaskTypeTable.TABLE_NAME, BaseColumns._ID + " = ?", new String[]{String.valueOf(entity.getId())});
        }
    }

    @Override
    public Task get(long id) {
        Task task = null;
        Cursor c = db.query(TaskTable.TABLE_NAME, new String[]{
                        BaseColumns._ID,TaskTable.TaskColumns.NAME,
                        TaskTable.TaskColumns.DATE,TaskTable.TaskColumns.DESCRIPTION,
                        TaskTable.TaskColumns.ID_TYPE_FK,TaskTable.TaskColumns.ID_PRIORITY_FK,
                        TaskTable.TaskColumns.ID_PERIODICITY_FK
                },
                BaseColumns._ID + " = ?", new String[] { String.valueOf(id) },
                null, null, null, "1");
        if(c.moveToFirst()){
            task = this.buildTaskFromCursor(c);
        }
        if(!c.isClosed()){
            c.close();
        }
        return task;
    }

    @Override
    public List<Task> getAll() {
        List<Task> list = new ArrayList<>();
        Cursor c = db.query(TaskTable.TABLE_NAME, new String[]{
                        BaseColumns._ID,TaskTable.TaskColumns.NAME,
                        TaskTable.TaskColumns.DATE,TaskTable.TaskColumns.DESCRIPTION,
                        TaskTable.TaskColumns.ID_TYPE_FK,TaskTable.TaskColumns.ID_PRIORITY_FK,
                        TaskTable.TaskColumns.ID_PERIODICITY_FK
                },
                null, null, null, null, TaskTable.TaskColumns.NAME, null);
        if(c.moveToFirst()){
            do{
                Task task = this.buildTaskFromCursor(c);
                if(task != null){
                    list.add(task);
                }
            } while(c.moveToNext());
        }
        if(!c.isClosed()){
            c.close();
        }
        return list;
    }

    private Task buildTaskFromCursor(Cursor c){
        Task task = null;
        if(c != null){
            task = new Task();
            task.setId(c.getLong(0));
            task.setName(c.getString(1));
            task.setDate(c.getString(2));
            task.setDescription(c.getString(3));
            task.setTaskType(c.getString(4));
            task.setTaskPriority(c.getString(5));
            task.setTaskPeriodicity(c.getShort(6));
        }
        return task;
    }

    public Task find(String name){
        long taskId = 0L;
        String sql = "select _id from " + TaskTable.TABLE_NAME + " where upper(" + TaskTable.TaskColumns.NAME + ") = ? limit 1";
        Cursor c = db.rawQuery(sql, new String[] { name.toUpperCase() });
        if(c.moveToFirst()){
            taskId = c.getLong(0);
        }
        if(!c.isClosed()){
            c.close();
        }
        return this.get(taskId);
    }
}
