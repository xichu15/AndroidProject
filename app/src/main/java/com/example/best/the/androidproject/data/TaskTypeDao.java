package com.example.best.the.androidproject.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.example.best.the.androidproject.model.TaskType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NP550P5C-SO4PL on 2016-12-11.
 */

public class TaskTypeDao implements Dao<TaskType> {

    private static final String INSERT =
            "insert into " + TaskTypeTable.TABLE_NAME
                    + "(" + TaskTypeTable.TaskTypeColumns.NAME
                    + ")" + " values (?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public TaskTypeDao(SQLiteDatabase db){
        this.db = db;
        insertStatement = db.compileStatement(TaskTypeDao.INSERT);
    }

    @Override
    public long save(TaskType entity) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, entity.getName());
        return insertStatement.executeInsert();
    }

    @Override
    public void update(TaskType entity) {
        final ContentValues values = new ContentValues();
        values.put(TaskTypeTable.TaskTypeColumns.NAME, entity.getName());
        db.update(TaskTypeTable.TABLE_NAME, values, BaseColumns._ID + " = ?", new String[] { String.valueOf(entity.getId()) });
    }

    @Override
    public void delete(TaskType entity) {
        if (entity.getId() > 0) {
            db.delete(TaskTypeTable.TABLE_NAME, BaseColumns._ID + " = ?", new String[]{String.valueOf(entity.getId())});
        }
    }

    @Override
    public TaskType get(long id) {
        TaskType taskType = null;
        Cursor c = db.query(TaskTypeTable.TABLE_NAME, new String[]{
                        BaseColumns._ID, TaskTypeTable.TaskTypeColumns.NAME
                },
                BaseColumns._ID + " = ?", new String[] { String.valueOf(id) },
                null, null, null, "1");
        if(c.moveToFirst()){
            taskType = buildTaskTypeFromCursor(c);
        }
        if(!c.isClosed()){
            c.close();
        }
        return taskType;
    }

    @Override
    public List<TaskType> getAll() {
        List<TaskType> list = new ArrayList<>();
        Cursor c = db.query(TaskTypeTable.TABLE_NAME, new String[]{
                        BaseColumns._ID,TaskTypeTable.TaskTypeColumns.NAME
                },
                null, null, null, null, TaskTypeTable.TaskTypeColumns.NAME, null);
        if(c.moveToFirst()){
            do{
                TaskType taskType = this.buildTaskTypeFromCursor(c);
                list.add(taskType);
            } while(c.moveToNext());
        }
        if(!c.isClosed()){
            c.close();
        }
        return list;
    }

    private TaskType buildTaskTypeFromCursor(Cursor c){
        TaskType taskType = null;
        if(c != null){
            taskType = new TaskType();
            taskType.setId(c.getLong(0));
            taskType.setName(c.getString(1));

            TaskDao td = new TaskDao(db);

            taskType.getTasks().addAll(td.getTasksByType(taskType.getId()));
        }
        return taskType;
    }

    public TaskType find(String name){
        long taskTypeId = 0L;
        String sql = "select _id from " + TaskTypeTable.TABLE_NAME + " where upper(" + TaskTypeTable.TaskTypeColumns.NAME + ") = ? limit 1";
        Cursor c = db.rawQuery(sql, new String[] { name.toUpperCase() });
        if(c.moveToFirst()){
            taskTypeId = c.getLong(0);
        }
        if(!c.isClosed()){
            c.close();
        }
        return this.get(taskTypeId);
    }
}
