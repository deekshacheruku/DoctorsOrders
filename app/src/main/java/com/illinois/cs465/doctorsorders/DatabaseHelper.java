package com.illinois.cs465.doctorsorders;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;

public class DatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 5;
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
    private static final String SCHEDULES_TABLE_TIMESTAMP = "Timestamp";

    private static final String MEDICINES_TABLE = "Registered_Medicines";
    private static final String MEDICINES_TABLE_MED_NAME = "Medicine_Name";
    private static final String MEDICINES_TABLE_MED_PICTURE = "Medicine_Picture";

    SQLiteDatabase db = this.getWritableDatabase();


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
                SCHEDULES_TABLE_INSTRUCTIONS + " TEXT, " + SCHEDULES_TABLE_DAY_FREQUENCY + " INTEGER, " + SCHEDULES_TABLE_SPECIFIC_TIME + " TEXT, " + SCHEDULES_TABLE_TIMESTAMP
                + " TEXT)";


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

    /**
     * THE MEDICINE NAME IS HARDCODED RIGHT NOW!
     * @param bundle
     * @return
     */

    public boolean addNewSchedule(Bundle bundle) {
        String currentTime = Calendar.getInstance().getTime().toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put(SCHEDULES_TABLE_PATIENT_NAME, bundle.getString("patientName"));
        contentValues.put(SCHEDULES_TABLE_MED_NAME, bundle.getString("medicationName"));
        contentValues.put(SCHEDULES_TABLE_INSTRUCTIONS, bundle.getString("instructions"));
        contentValues.put(SCHEDULES_TABLE_DAY_FREQUENCY, bundle.getString("days"));
        contentValues.put(SCHEDULES_TABLE_NUMBER_PILLS, bundle.getString("pillNumber"));
        contentValues.put(SCHEDULES_TABLE_SPECIFIC_TIME, bundle.getString("specific_time"));
        contentValues.put(SCHEDULES_TABLE_TIMESTAMP, currentTime);

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
        String query = "SELECT * FROM " + SCHEDULER_PATIENTS_TABLE;
        return db.rawQuery(query, null);
    }

    public Cursor getAllMedNameFrequencySchedules(String patientName) {
        String query = "SELECT ID, " + SCHEDULES_TABLE_MED_NAME + ", " + SCHEDULES_TABLE_DAY_FREQUENCY + " FROM " + SCHEDULES_TABLE + " WHERE " + SCHEDULES_TABLE_PATIENT_NAME + " = \"" + patientName + "\"";
        return db.rawQuery(query, null);
    }

    public Cursor getSchedule(String patientName) {
        String query = "SELECT * FROM " + SCHEDULES_TABLE + " WHERE " + SCHEDULES_TABLE_PATIENT_NAME + " = \"" + patientName + "\"";
        return db.rawQuery(query, null);
    }

    public Cursor getAllMedScheduleInformation(Long recordId) {
        String query = "SELECT * FROM " + SCHEDULES_TABLE + " WHERE ID = " + recordId;
        return db.rawQuery(query, null);
    }

    public void addMedicineImages(String name, byte[] image) {
        ContentValues cv = new ContentValues();
        cv.put(MEDICINES_TABLE_MED_NAME, name);
        cv.put(MEDICINES_TABLE_MED_PICTURE, image);
        db.insert(MEDICINES_TABLE, null, cv);
    }

    public Cursor getMedicineImage(String name) {
        String query = "SELECT * FROM " + MEDICINES_TABLE + " WHERE " + MEDICINES_TABLE_MED_NAME + " = \"" + name + "\"";
        return db.rawQuery(query, null);
    }

    public boolean updateMedScheduleInformation(Bundle bundle) {

        String currentTime = Calendar.getInstance().getTime().toString();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SCHEDULES_TABLE_PATIENT_NAME, bundle.getString("patientName"));
        contentValues.put(SCHEDULES_TABLE_MED_NAME, bundle.getString("medicationName"));
        contentValues.put(SCHEDULES_TABLE_INSTRUCTIONS, bundle.getString("instructions"));
        contentValues.put(SCHEDULES_TABLE_DAY_FREQUENCY, bundle.getString("days"));
        contentValues.put(SCHEDULES_TABLE_NUMBER_PILLS, bundle.getString("pillNumber"));
        contentValues.put(SCHEDULES_TABLE_SPECIFIC_TIME, bundle.getString("specific_time"));
        contentValues.put(SCHEDULES_TABLE_TIMESTAMP, currentTime);

        long result = db.update(SCHEDULES_TABLE, contentValues, "ID = " + bundle.getLong("recordIdToUpdate"), null);

        if (result == -1) {
            Log.d("-1", "update record failed");
            return false;
        } else {
            Log.d("1", "update record succeeded");
            return true;
        }
    }

    public Cursor getMostRecentUpdatedSchedule() {
        String query = "SELECT " + SCHEDULES_TABLE_MED_NAME + " From " + SCHEDULES_TABLE + " ORDER BY " + SCHEDULES_TABLE_TIMESTAMP + " DESC LIMIT 1";
        return db.rawQuery(query, null);
    }
}
