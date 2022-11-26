package com.illinois.cs465.doctorsorders;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.Bundle;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    String[] medicines = {"Atorvastatin", "Metformin", "Simvastatin", "Omeprazole", "Amlodipine"};

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "DoctorsOrders.db";

    private static final String SCHEDULER_PATIENTS_TABLE = "Scheduler_Patients_List";
    private static final String SCHEDULER_PATIENTS_TABLE_SCHEDULER_NAME = "Scheduler_Name";
    private static final String SCHEDULER_PATIENTS_TABLE_PATIENT_FIRST_NAME = "Patient_First_Name";
    private static final String SCHEDULER_PATIENTS_TABLE_PATIENT_LAST_NAME = "Patient_Last_Name";


    private static final String SCHEDULES_TABLE = "Registered_Schedules";
    private static final String SCHEDULES_TABLE_PATIENT_NAME = "Patient_Name";
    private static final String SCHEDULES_TABLE_MED_NAME = "Medicine_Name";
    private static final String SCHEDULES_TABLE_NUMBER_PILLS = "Number_Pills";
    private static final String SCHEDULES_TABLE_INSTRUCTIONS = "Instructions";
    private static final String SCHEDULES_TABLE_DAY_FREQUENCY = "Medicine_Day_Frequency";
    private static final String SCHEDULES_TABLE_SPECIFIC_TIME = "Medicine_Day_Specific_Time";

    private static final String MEDICINES_TABLE = "Registered_Medicines";
    private static final String MEDICINES_TABLE_MED_NAME = "Medicine_Name";
    private static final String MEDICINES_TABLE_MED_PICTURE = "Medicine_Picture";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createPatientsListTable = "CREATE TABLE " + SCHEDULER_PATIENTS_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SCHEDULER_PATIENTS_TABLE_SCHEDULER_NAME + " TEXT, " + SCHEDULER_PATIENTS_TABLE_PATIENT_FIRST_NAME + " TEXT, " + SCHEDULER_PATIENTS_TABLE_PATIENT_LAST_NAME + " TEXT)";

        String createMedicineTable = "CREATE TABLE " + MEDICINES_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MEDICINES_TABLE_MED_NAME + " TEXT, " + MEDICINES_TABLE_MED_PICTURE + " BLOB)";

        String createScheduleTable = "CREATE TABLE " + SCHEDULES_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + SCHEDULES_TABLE_PATIENT_NAME + " TEXT, " +
                SCHEDULES_TABLE_MED_NAME + " TEXT, " + SCHEDULES_TABLE_NUMBER_PILLS + " TEXT, " +
                SCHEDULES_TABLE_INSTRUCTIONS + " TEXT, " + SCHEDULES_TABLE_DAY_FREQUENCY + " INTEGER, " + SCHEDULES_TABLE_SPECIFIC_TIME + " TEXT)";

        db.execSQL(createPatientsListTable);
        db.execSQL(createMedicineTable);
        db.execSQL(createScheduleTable);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SCHEDULER_PATIENTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SCHEDULES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MEDICINES_TABLE);

        onCreate(db);
    }

    public boolean addDefaultMedications() { // this function would be called to add the default medications
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        for (String med : medicines) {
            contentValues.put(MEDICINES_TABLE, med);
        }

        long result = db.insert(MEDICINES_TABLE, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * THE MEDICINE NAME IS HARDCODED RIGHT NOW!
     * @param bundle
     * @return
     */

    public boolean addNewSchedule(Bundle bundle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SCHEDULES_TABLE_PATIENT_NAME, bundle.getString("patientName"));
        contentValues.put(SCHEDULES_TABLE_MED_NAME, "MedFormin");
        contentValues.put(SCHEDULES_TABLE_INSTRUCTIONS, bundle.getString("instructions"));
        contentValues.put(SCHEDULES_TABLE_DAY_FREQUENCY, bundle.getString("days"));
        contentValues.put(SCHEDULES_TABLE_NUMBER_PILLS, bundle.getString("pillNumber"));
        contentValues.put(SCHEDULES_TABLE_SPECIFIC_TIME, bundle.getString("specific_time"));

//        contentValues.put(SCHEDULES_TABLE_MED_NAME, bundle.getString());
        long result = db.insert(SCHEDULES_TABLE, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            Log.d("1", "New Schedule inserted!");
            return true;
        }
    }

    public boolean addNearbyPatient(Bundle bundle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SCHEDULER_PATIENTS_TABLE_SCHEDULER_NAME, bundle.getString("scheduler_name"));
        contentValues.put(SCHEDULER_PATIENTS_TABLE_PATIENT_FIRST_NAME, bundle.getString("patient_first_name"));
        contentValues.put(SCHEDULER_PATIENTS_TABLE_PATIENT_LAST_NAME, bundle.getString("patient_last_name"));

        long result = db.insert(SCHEDULER_PATIENTS_TABLE, null, contentValues);

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
        String query = "SELECT * FROM " + SCHEDULER_PATIENTS_TABLE;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getAllMedNameFrequencySchedules(String patientName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT ID, " + SCHEDULES_TABLE_MED_NAME + ", " + SCHEDULES_TABLE_DAY_FREQUENCY + " FROM " + SCHEDULES_TABLE + " WHERE " + SCHEDULES_TABLE_PATIENT_NAME + " = \"" + patientName + "\"";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getAllMedScheduleInformation(Long recordId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + SCHEDULES_TABLE + " WHERE ID = " + recordId;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
