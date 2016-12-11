package com.example.best.the.androidproject.data;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Michał on 2016-12-11.
 */

public class TaskTypeTable {
    public static final String TABLE_NAME = "TypZadania";

    public static class TaskTypeColumns implements BaseColumns {
        public static final String NAME = "nazwa";
    }

    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TaskTypeTable.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(TaskTypeTable.TaskTypeColumns.NAME + " TEXT ");
        sb.append(");");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TaskTypeTable.TABLE_NAME);
        TaskTypeTable.onCreate(db);
    }
}
