package com.example.best.the.androidproject.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.example.best.the.androidproject.model.TaskPriority;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NP550P5C-SO4PL on 2016-12-11.
 */

public class TaskPriorityDao implements Dao<TaskPriority> {

    private static final String INSERT =
            "insert into " + TaskPriorityTable.TABLE_NAME
                    + "(" + TaskPriorityTable.TaskPriorityColumns.NAME
                    + ")" + " values (?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public TaskPriorityDao(SQLiteDatabase db){
        this.db = db;
        insertStatement = db.compileStatement(TaskPriorityDao.INSERT);
    }

    @Override
    public long save(TaskPriority entity) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, entity.getName());
        return insertStatement.executeInsert();
    }

    @Override
    public void update(TaskPriority entity) {
        final ContentValues values = new ContentValues();
        values.put(TaskPriorityTable.TaskPriorityColumns.NAME, entity.getName());
        db.update(TaskPriorityTable.TABLE_NAME, values, BaseColumns._ID + " = ?", new String[] { String.valueOf(entity.getId()) });
    }

    @Override
    public void delete(TaskPriority entity) {
        if (entity.getId() > 0) {
            db.delete(TaskPriorityTable.TABLE_NAME, BaseColumns._ID + " = ?", new String[]{String.valueOf(entity.getId())});
        }
    }

    @Override
    public TaskPriority get(long id) {
        TaskPriority taskPriority = null;
        Cursor c = db.query(TaskPriorityTable.TABLE_NAME, new String[]{
                        BaseColumns._ID, TaskPriorityTable.TaskPriorityColumns.NAME
                },
                BaseColumns._ID + " = ?", new String[] { String.valueOf(id) },
                null, null, null, "1");
        if(c.moveToFirst()){
            taskPriority = buildTaskPriorityFromCursor(c);
        }
        if(!c.isClosed()){
            c.close();
        }
        return taskPriority;
    }

    @Override
    public List<TaskPriority> getAll() {
        List<TaskPriority> list = new ArrayList<>();
        Cursor c = db.query(TaskPriorityTable.TABLE_NAME, new String[]{
                        BaseColumns._ID, TaskPriorityTable.TaskPriorityColumns.NAME
                },
                null, null, null, null, TaskPriorityTable.TaskPriorityColumns.NAME, null);
        if(c.moveToFirst()){
            do{
                TaskPriority taskPriority = buildTaskPriorityFromCursor(c);
                list.add(taskPriority);
            } while(c.moveToNext());
        }
        if(!c.isClosed()){
            c.close();
        }
        return list;
    }

    private TaskPriority buildTaskPriorityFromCursor(Cursor c){
        TaskPriority taskPriority = null;
        if(c != null){
            taskPriority = new TaskPriority();
            taskPriority.setId(c.getLong(0));
            taskPriority.setName(c.getString(1));

            TaskDao td = new TaskDao(db);

            taskPriority.setTasks(td.getTasksByPriority(taskPriority.getId()));
        }
        return taskPriority;
    }

    public TaskPriority find(String name){
        long taskPriorityId = 0L;
        String sql = "select _id from " + TaskPriorityTable.TABLE_NAME + " where upper(" + TaskPriorityTable.TaskPriorityColumns.NAME + ") = ? limit 1";
        Cursor c = db.rawQuery(sql, new String[] { name.toUpperCase() });
        if(c.moveToFirst()){
            taskPriorityId = c.getLong(0);
        }
        if(!c.isClosed()){
            c.close();
        }
        return this.get(taskPriorityId);
    }
}
