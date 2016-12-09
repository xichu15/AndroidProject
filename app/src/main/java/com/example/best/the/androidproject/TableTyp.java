package com.example.best.the.androidproject;

/**
 * Created by Adrianna on 09.12.2016.
 */

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public final class TableTyp {

    public static final String TABLE_NAME = "typ";

    public static class TypColumns implements BaseColumns {
        public static final String NAME = "name";
    }

    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE " + TableTyp.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(TypColumns.NAME + " TEXT UNIQUE NOT NULL");
        sb.append(");");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableTyp.TABLE_NAME);
        TableTyp.onCreate(db);
    }

}
