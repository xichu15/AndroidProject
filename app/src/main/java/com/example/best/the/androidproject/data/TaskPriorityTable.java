package com.example.best.the.androidproject.data;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Michał on 2016-12-11.
 */

public class TaskPriorityTable {
    public static final String TABLE_NAME = "PriorytetZadania";

    public static class TaskPriorityColumns implements BaseColumns {
        public static final String NAME = "nazwa";
    }

    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TaskPriorityTable.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(TaskPriorityTable.TaskPriorityColumns.NAME + " TEXT ");
        sb.append(");");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TaskPriorityTable.TABLE_NAME);
        TaskPriorityTable.onCreate(db);
    }
}
