package com.example.best.the.androidproject.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.example.best.the.androidproject.model.Task;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        insertStatement.bindString(2, entity.getDate().toString());
        insertStatement.bindString(3, entity.getDescription());
        insertStatement.bindLong(4, entity.getTaskType().getId());
        insertStatement.bindLong(5, entity.getTaskPriority().getId());
        insertStatement.bindLong(6,entity.getTaskPeriodicity().getId());
        return insertStatement.executeInsert();
    }

    @Override
    public void update(Task entity) {
        final ContentValues values = new ContentValues();
        values.put(TaskTable.TaskColumns.NAME, entity.getName());
        values.put(TaskTable.TaskColumns.DATE, entity.getDate().toString());
        values.put(TaskTable.TaskColumns.DESCRIPTION, entity.getDescription());
        values.put(TaskTable.TaskColumns.ID_TYPE_FK, Long.toString(entity.getTaskType().getId()));
        values.put(TaskTable.TaskColumns.ID_PRIORITY_FK, Long.toString(entity.getTaskPriority().getId()));
        values.put(TaskTable.TaskColumns.ID_PERIODICITY_FK, Long.toString(entity.getTaskPeriodicity().getId()));
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

    public List<Task> getTasksByType(long id) {
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
                if(task.getTaskType().getId() == id){
                    list.add(task);
                }
            } while(c.moveToNext());
        }
        if(!c.isClosed()){
            c.close();
        }
        return list;
    }

    public List<Task> getTasksByPriority(long id) {
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
                if(task.getTaskPriority().getId() == id){
                    list.add(task);
                }
            } while(c.moveToNext());
        }
        if(!c.isClosed()){
            c.close();
        }
        return list;
    }


    public List<Task> getTasksByPeriodicity(long id) {
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
                if(task.getTaskPeriodicity().getId() == id){
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

            task.setDate(convertToDate(c.getString(2)));
            task.setDescription(c.getString(3));

            TaskTypeDao type = new TaskTypeDao(db);
            TaskPriorityDao priority = new TaskPriorityDao(db);
            TaskPeriodicityDao periodicity = new TaskPeriodicityDao(db);

            task.setTaskType(type.get(c.getLong(4)));
            task.setTaskPriority(priority.get(c.getLong(5)));
            task.setTaskPeriodicity(periodicity.get(c.getLong(6)));
        }
        return task;
    }

    private Date convertToDate(String dateString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dow mon dd hh:mm:ss zzz yyyy");
        //dow mon dd hh:mm:ss zzz yyyy
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
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
