package com.example.best.the.androidproject;

/**
 * Created by Adrianna on 09.12.2016.
 */

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public final class TablePriorytet {

    public static final String TABLE_NAME = "priorytet";

    public static class PriorytetColumns implements BaseColumns {
        public static final String NAME = "name";
    }

    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE " + TablePriorytet.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(PriorytetColumns.NAME + " TEXT UNIQUE NOT NULL");
        sb.append(");");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TablePriorytet.TABLE_NAME);
        TablePriorytet.onCreate(db);
    }

}