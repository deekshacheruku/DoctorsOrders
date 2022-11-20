package com.illinois.cs465.doctorsorders;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DoctorsOrders.db";

    private static final String TABLE_NAME = "Scheduler_Patients_List";
    private static final String COL1 = "Scheduler_Name";
    private static final String COL2 = "Patient_First_Name";
    private static final String COL3 = "Patient_Last_Name";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 + " TEXT, " + COL2 + " TEXT, " + COL3 + " TEXT)";
        db.execSQL(createTable);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addNearbyPatient(Bundle bundle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, bundle.getString("scheduler_name"));
        contentValues.put(COL2, bundle.getString("patient_first_name"));
        contentValues.put(COL3, bundle.getString("patient_last_name"));

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            Log.d("-1", "this is false");
            return false;
        } else {
            Log.d("1", "this is true");
            return true;
        }
    }

    public Cursor getAllAssignedPatients() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
