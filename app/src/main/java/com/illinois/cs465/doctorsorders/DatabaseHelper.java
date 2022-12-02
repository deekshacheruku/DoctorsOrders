package com.illinois.cs465.doctorsorders;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;

public class DatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 9;
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

    private static final String REGISTERED_PATIENTS_TABLE = "Registered_Patients";
    private static final String REGISTERED_PATIENTS_NAME = "Name";
    private static final String REGISTERED_PATIENTS_PIN = "Pin_Num";
    private static final String REGISTERED_PATIENTS_DOCTOR_NAME = "Doctor_Name";
    private static final String REGISTERED_PATIENTS_CLINIC_NAME = "Clinic_Name";
    private static final String REGISTERED_PATIENTS_DOCTOR_OFFICE_NUMBER = "Doctor_Office_Number";

    private static final String REGISTERED_PATIENTS_FRIEND_NAME0 = "Friend_Name0";
    private static final String REGISTERED_PATIENTS_FRIEND_RELATION0 = "Friend_Relation0";
    private static final String REGISTERED_PATIENTS_PHONE_NUMBER0 = "Phone_Number0";
    private static final String REGISTERED_PATIENTS_FRIEND_NAME1 = "Friend_Name1";
    private static final String REGISTERED_PATIENTS_FRIEND_RELATION1 = "Friend_Relation1";
    private static final String REGISTERED_PATIENTS_PHONE_NUMBER1 = "Phone_Number1";
    private static final String REGISTERED_PATIENTS_FRIEND_NAME2 = "Friend_Name2";
    private static final String REGISTERED_PATIENTS_FRIEND_RELATION2 = "Friend_Relation2";
    private static final String REGISTERED_PATIENTS_PHONE_NUMBER2 = "Phone_Number2";
    private static final String REGISTERED_PATIENTS_FRIEND_NAME3 = "Friend_Name3";
    private static final String REGISTERED_PATIENTS_FRIEND_RELATION3 = "Friend_Relation3";
    private static final String REGISTERED_PATIENTS_PHONE_NUMBER3 = "Phone_Number3";
    private static final String REGISTERED_PATIENTS_FRIEND_NAME4 = "Friend_Name4";
    private static final String REGISTERED_PATIENTS_FRIEND_RELATION4 = "Friend_Relation4";
    private static final String REGISTERED_PATIENTS_PHONE_NUMBER4 = "Phone_Number4";

    private static final String REGISTERED_SCHEDULER_TABLE = "Registered_Scheduler";
    private static final String REGISTERED_SCHEDULER_LASTNAME = "Last_Name";
    private static final String REGISTERED_SCHEDULER_FIRSTNAME = "First_Name";
    private static final String REGISTERED_SCHEDULER_USERNAME = "User_Name";
    private static final String REGISTERED_SCHEDULER_PASSWORD = "Password";

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

        String createPatientTable = "CREATE TABLE " + REGISTERED_PATIENTS_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                REGISTERED_PATIENTS_NAME + " TEXT, " + REGISTERED_PATIENTS_PIN + " INTEGER, " +
                REGISTERED_PATIENTS_DOCTOR_NAME + " TEXT, " + REGISTERED_PATIENTS_CLINIC_NAME + " TEXT, " + REGISTERED_PATIENTS_DOCTOR_OFFICE_NUMBER + " TEXT, " +
                REGISTERED_PATIENTS_FRIEND_NAME0 + " TEXT, " + REGISTERED_PATIENTS_FRIEND_RELATION0 + " TEXT, " + REGISTERED_PATIENTS_PHONE_NUMBER0 + " TEXT, " +
                REGISTERED_PATIENTS_FRIEND_NAME1 + " TEXT, " + REGISTERED_PATIENTS_FRIEND_RELATION1 + " TEXT, " + REGISTERED_PATIENTS_PHONE_NUMBER1 + " TEXT, " +
                REGISTERED_PATIENTS_FRIEND_NAME2 + " TEXT, " + REGISTERED_PATIENTS_FRIEND_RELATION2 + " TEXT, " + REGISTERED_PATIENTS_PHONE_NUMBER2 + " TEXT, " +
                REGISTERED_PATIENTS_FRIEND_NAME3 + " TEXT, " + REGISTERED_PATIENTS_FRIEND_RELATION3 + " TEXT, " + REGISTERED_PATIENTS_PHONE_NUMBER3 + " TEXT, " +
                REGISTERED_PATIENTS_FRIEND_NAME4 + " TEXT, " + REGISTERED_PATIENTS_FRIEND_RELATION4 + " TEXT, " + REGISTERED_PATIENTS_PHONE_NUMBER4 + " TEXT)";

        String createSchedulerTable = "CREATE TABLE " + REGISTERED_SCHEDULER_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + REGISTERED_SCHEDULER_LASTNAME + " TEXT, " +
                REGISTERED_SCHEDULER_FIRSTNAME + " TEXT, " + REGISTERED_SCHEDULER_USERNAME + " TEXT, " +
                REGISTERED_SCHEDULER_PASSWORD + " TEXT)";

        db.execSQL(createPatientsListTable);
        db.execSQL(createMedicineTable);
        db.execSQL(createScheduleTable);
        db.execSQL(createPatientTable);
        db.execSQL(createSchedulerTable);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SCHEDULER_PATIENTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SCHEDULES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MEDICINES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + REGISTERED_PATIENTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + REGISTERED_SCHEDULER_TABLE);

        onCreate(db);
    }

    public boolean addNewPatients(Bundle fromPart0, Bundle allFFEntries) { //for adding new registered patients
        ContentValues contentValues = new ContentValues();

        contentValues.put(REGISTERED_PATIENTS_NAME, fromPart0.getString("name"));
        contentValues.put(REGISTERED_PATIENTS_PIN, fromPart0.getString("pin"));
        contentValues.put(REGISTERED_PATIENTS_DOCTOR_NAME, fromPart0.getString("docName"));
        contentValues.put(REGISTERED_PATIENTS_CLINIC_NAME, fromPart0.getString("clinicName"));
        contentValues.put(REGISTERED_PATIENTS_DOCTOR_OFFICE_NUMBER, fromPart0.getString("offNum"));

        for(int i = 0; i < allFFEntries.getInt("numEntries"); i++)
        {
            switch(i)
            {
                case 0:
                    contentValues.put(REGISTERED_PATIENTS_FRIEND_NAME0, allFFEntries.getString("ffName"+i));
                    contentValues.put(REGISTERED_PATIENTS_FRIEND_RELATION0, allFFEntries.getString("ffRelation"+i));
                    contentValues.put(REGISTERED_PATIENTS_PHONE_NUMBER0, allFFEntries.getString("ffNum"+i));
                    break;
                case 1:
                    contentValues.put(REGISTERED_PATIENTS_FRIEND_NAME1, allFFEntries.getString("ffName"+i));
                    contentValues.put(REGISTERED_PATIENTS_FRIEND_RELATION1, allFFEntries.getString("ffRelation"+i));
                    contentValues.put(REGISTERED_PATIENTS_PHONE_NUMBER1, allFFEntries.getString("ffNum"+i));
                    break;
                case 2:
                    contentValues.put(REGISTERED_PATIENTS_FRIEND_NAME2, allFFEntries.getString("ffName"+i));
                    contentValues.put(REGISTERED_PATIENTS_FRIEND_RELATION2, allFFEntries.getString("ffRelation"+i));
                    contentValues.put(REGISTERED_PATIENTS_PHONE_NUMBER3, allFFEntries.getString("ffNum"+i));
                    break;
                case 3:
                    contentValues.put(REGISTERED_PATIENTS_FRIEND_NAME3, allFFEntries.getString("ffName"+i));
                    contentValues.put(REGISTERED_PATIENTS_FRIEND_RELATION3, allFFEntries.getString("ffRelation"+i));
                    contentValues.put(REGISTERED_PATIENTS_PHONE_NUMBER3, allFFEntries.getString("ffNum"+i));
                    break;
                case 4:
                    contentValues.put(REGISTERED_PATIENTS_FRIEND_NAME4, allFFEntries.getString("ffName"+i));
                    contentValues.put(REGISTERED_PATIENTS_FRIEND_RELATION4, allFFEntries.getString("ffRelation"+i));
                    contentValues.put(REGISTERED_PATIENTS_PHONE_NUMBER4, allFFEntries.getString("ffNum"+i));
                    break;
            }
        }

        long result = db.insert(REGISTERED_PATIENTS_TABLE, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            Log.d("1", "Patient just Registered!");
            return true;
        }
    }

    public boolean addNewScheduler(Bundle bundle) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(REGISTERED_SCHEDULER_LASTNAME, bundle.getString("lastName"));
        contentValues.put(REGISTERED_SCHEDULER_FIRSTNAME, bundle.getString("firstName"));
        contentValues.put(REGISTERED_SCHEDULER_USERNAME, bundle.getString("username"));
        contentValues.put(REGISTERED_SCHEDULER_PASSWORD, bundle.getString("password"));

        long result = db.insert(REGISTERED_SCHEDULER_TABLE, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            Log.d("1", "New Schedule inserted!");
            return true;
        }
    }

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

    public Cursor loginInfoExistsPatient(Bundle bundle) {
        String pin = bundle.getString("password");
        String lastName = bundle.getString("name");
        String query = "SELECT * FROM " + REGISTERED_PATIENTS_TABLE + " WHERE "
                + REGISTERED_PATIENTS_PIN + " = \"" + pin + "\"" + " AND " + REGISTERED_PATIENTS_NAME + " = \"" + lastName + "\"";
        return db.rawQuery(query, null);
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
