package com.example.best.the.androidproject.data;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Michał on 2016-12-11.
 */

public class TaskPeriodicityTable {
    public static final String TABLE_NAME = "CyklicznoscZadania";

    public static class TaskPeriodicityColumns implements BaseColumns {
        public static final String NAME = "nazwa";
    }

    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TaskPeriodicityTable.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(TaskPeriodicityTable.TaskPeriodicityColumns.NAME + " TEXT ");
        sb.append(");");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TaskPeriodicityTable.TABLE_NAME);
        TaskPeriodicityTable.onCreate(db);
    }
}
