package com.example.best.the.androidproject.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.example.best.the.androidproject.model.TaskPeriodicity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NP550P5C-SO4PL on 2016-12-11.
 */

public class TaskPeriodicityDao implements Dao<TaskPeriodicity> {

    private static final String INSERT =
            "insert into " + TaskPeriodicityTable.TABLE_NAME
                    + "(" + TaskPeriodicityTable.TaskPeriodicityColumns.NAME
                    + ")" + " values (?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public TaskPeriodicityDao(SQLiteDatabase db){
        this.db = db;
        insertStatement = db.compileStatement(TaskPeriodicityDao.INSERT);
    }

    @Override
    public long save(TaskPeriodicity entity) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, entity.getName());
        return insertStatement.executeInsert();
    }

    @Override
    public void update(TaskPeriodicity entity) {
        final ContentValues values = new ContentValues();
        values.put(TaskPeriodicityTable.TaskPeriodicityColumns.NAME, entity.getName());
        db.update(TaskPeriodicityTable.TABLE_NAME, values, BaseColumns._ID + " = ?", new String[] { String.valueOf(entity.getId()) });
    }

    @Override
    public void delete(TaskPeriodicity entity) {
        if (entity.getId() > 0) {
            db.delete(TaskPeriodicityTable.TABLE_NAME, BaseColumns._ID + " = ?", new String[]{String.valueOf(entity.getId())});
        }
    }

    @Override
    public TaskPeriodicity get(long id) {
        TaskPeriodicity taskPeriodicity = null;
        Cursor c = db.query(TaskPeriodicityTable.TABLE_NAME, new String[]{
                        BaseColumns._ID, TaskPeriodicityTable.TaskPeriodicityColumns.NAME
                },
                BaseColumns._ID + " = ?", new String[] { String.valueOf(id) },
                null, null, null, "1");
        if(c.moveToFirst()){
            taskPeriodicity = buildTaskPeriodicityFromCursor(c);
        }
        if(!c.isClosed()){
            c.close();
        }
        return taskPeriodicity;
    }

    @Override
    public List<TaskPeriodicity> getAll() {
        List<TaskPeriodicity> list = new ArrayList<>();
        Cursor c = db.query(TaskPeriodicityTable.TABLE_NAME, new String[]{
                        BaseColumns._ID, TaskPeriodicityTable.TaskPeriodicityColumns.NAME
                },
                null, null, null, null, TaskPeriodicityTable.TaskPeriodicityColumns.NAME, null);
        if(c.moveToFirst()){
            do{
                TaskPeriodicity taskPeriodicity = buildTaskPeriodicityFromCursor(c);
                list.add(taskPeriodicity);
            } while(c.moveToNext());
        }
        if(!c.isClosed()){
            c.close();
        }
        return list;
    }

    private TaskPeriodicity buildTaskPeriodicityFromCursor(Cursor c){
        TaskPeriodicity taskPeriodicity = null;
        if(c != null){
            taskPeriodicity = new TaskPeriodicity();
            taskPeriodicity.setId(c.getLong(0));
            taskPeriodicity.setName(c.getString(1));

            TaskDao td = new TaskDao(db);

            taskPeriodicity.setTasks(td.getTasksByPeriodicity(taskPeriodicity.getId()));
        }
        return taskPeriodicity;
    }

    public TaskPeriodicity find(String name){
        long taskPeriodicityId = 0L;
        String sql = "select _id from " + TaskPeriodicityTable.TABLE_NAME + " where upper(" + TaskPeriodicityTable.TaskPeriodicityColumns.NAME + ") = ? limit 1";
        Cursor c = db.rawQuery(sql, new String[] { name.toUpperCase() });
        if(c.moveToFirst()){
            taskPeriodicityId = c.getLong(0);
        }
        if(!c.isClosed()){
            c.close();
        }
        return this.get(taskPeriodicityId);
    }
}
