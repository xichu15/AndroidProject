package com.example.best.the.androidproject;

/**
 * Created by Adrianna on 09.12.2016.
 */

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public final class TableZadanie {

    public static final String TABLE_NAME = "movie";

    public static class ZadanieColumns implements BaseColumns {
        public static final String TYPE_ID = "type_id";
        public static final String PRIORITY_ID = "priority_id";
        public static final String FREQUENCY_ID = "frequency_id";
        public static final String NAME = "name";
        public static final String OPIS = "opis";
        public static final String DATE = "date";
    }

    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE " + TableZadanie.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(ZadanieColumns.NAME + " TEXT UNIQUE NOT NULL, ");
        sb.append(ZadanieColumns.OPIS + " TEXT, ");
        sb.append(ZadanieColumns.DATE + " DATE, ");
        sb.append(ZadanieColumns.TYPE_ID + " INTEGER NOT NULL, ");
        sb.append(ZadanieColumns.PRIORITY_ID + " INTEGER NOT NULL, ");
        sb.append(ZadanieColumns.FREQUENCY_ID + " INTEGER NOT NULL, ");
        sb.append("FOREIGN KEY(" + ZadanieColumns.TYPE_ID + ") REFERENCES " + TableTyp.TABLE_NAME + "("
                + BaseColumns._ID + "), ");
        sb.append("FOREIGN KEY(" + ZadanieColumns.PRIORITY_ID + ") REFERENCES " + TablePriorytet.TABLE_NAME + "("
                + BaseColumns._ID + "), ");
        sb.append("FOREIGN KEY(" + ZadanieColumns.FREQUENCY_ID + ") REFERENCES " + TableCyklicznosc.TABLE_NAME + "("
                + BaseColumns._ID + "), ");
        sb.append(");");
        db.execSQL(sb.toString());
    }


    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableZadanie.TABLE_NAME);
        TableZadanie.onCreate(db);
    }
}