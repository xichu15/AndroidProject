package com.example.best.the.androidproject.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.best.the.androidproject.R;
import com.example.best.the.androidproject.model.TaskPeriodicity;
import com.example.best.the.androidproject.model.TaskPriority;
import com.example.best.the.androidproject.model.TaskType;

import java.security.acl.Group;

/**
 * Created by Michał on 2016-12-16.
 */

public class OpenHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "projekt.db";
    static final int DATABASE_VERSION = 1;

    private Context context;

    OpenHelper(final Context context) {
        super(context, OpenHelper.DATABASE_NAME , null, OpenHelper.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onOpen(final SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        TaskTypeTable.onCreate(sqLiteDatabase);
        TaskTypeDao taskTypeDao = new TaskTypeDao(sqLiteDatabase);
        String[] taskTypes = context.getResources().getStringArray(R.array.taskType);
        for(String gr: taskTypes) {
            taskTypeDao.save(new TaskType(0, gr));
        }

        TaskPriorityTable.onCreate(sqLiteDatabase);
        TaskPriorityDao taskPriorityDao = new TaskPriorityDao(sqLiteDatabase);
        String[] taskPriorites = context.getResources().getStringArray(R.array.taskPriority);
        for(String gr: taskPriorites) {
            taskPriorityDao.save(new TaskPriority(0, gr));
        }

        TaskPeriodicityTable.onCreate(sqLiteDatabase);
        TaskPeriodicityDao taskPeriodicityDao = new TaskPeriodicityDao(sqLiteDatabase);
        String[] taskPeriodicities = context.getResources().getStringArray(R.array.taskPeriodicity);
        for(String gr: taskPeriodicities) {
            taskPeriodicityDao.save(new TaskPeriodicity(0, gr));
        }

        TaskTable.onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, final int oldVersion, final int newVersion) {
        TaskTypeTable.onUpgrade(db, oldVersion, newVersion);
        TaskPriorityTable.onUpgrade(db, oldVersion, newVersion);
        TaskPeriodicityTable.onUpgrade(db, oldVersion, newVersion);
        TaskTable.onUpgrade(db, oldVersion, newVersion);
    }
}
