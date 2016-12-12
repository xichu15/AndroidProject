package com.example.best.the.androidproject.data;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Michał on 2016-12-11.
 */

public class TaskTable {
    public static final String TABLE_NAME = "Zadanie";

    public static class TaskColumns implements BaseColumns {
        public static final String NAME = "nazwa";
        public static final String DESCRIPTION = "opis";
        public static final String DATE = "data";
        public static final String ID_TYPE_FK = "idTypFK";
        public static final String ID_PRIORITY_FK = "idPriorytetFK";
        public static final String ID_PERIODICITY_FK = "idCyklicznoscFK";
    }

    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TaskTable.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(TaskColumns.ID_TYPE_FK + " INTEGER NOT NULL, ");
        sb.append(TaskColumns.ID_PRIORITY_FK + " INTEGER NOT NULL, ");
        sb.append(TaskColumns.ID_PERIODICITY_FK + " INTEGER NOT NULL, ");
        sb.append("FOREIGN KEY(" + TaskColumns.ID_TYPE_FK + ")"
                + "REFERENCES " + TaskTypeTable.TABLE_NAME + "("
                + BaseColumns._ID + "), ");
        sb.append("FOREIGN KEY(" + TaskColumns.ID_PRIORITY_FK + ")"
                + "REFERENCES " + TaskPriorityTable.TABLE_NAME + "("
                + BaseColumns._ID + "), ");
        sb.append("FOREIGN KEY(" + TaskColumns.ID_PERIODICITY_FK + ")"
                + "REFERENCES " + TaskPeriodicityTable.TABLE_NAME + "("
                + BaseColumns._ID + "), ");
        sb.append(TaskColumns.NAME + " TEXT, ");
        sb.append(TaskColumns.DESCRIPTION + " TEXT, ");
        sb.append(TaskColumns.DATE + " INTEGER ");
        sb.append(");");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TaskTable.TABLE_NAME);
        TaskTable.onCreate(db);
    }
}
