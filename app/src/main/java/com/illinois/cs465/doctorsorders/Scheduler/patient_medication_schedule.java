package com.illinois.cs465.doctorsorders.Scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.illinois.cs465.doctorsorders.DatabaseHelper;
import com.illinois.cs465.doctorsorders.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class patient_medication_schedule extends AppCompatActivity {
    ArrayList<String> schedulesList;
    DatabaseHelper databaseHelper;
    SimpleDateFormat dateFormat;
    TextView last_update_view;
    Calendar calendar;
    ListView listView;
    Bundle bundle;

    String mostRecent = "N/A";
    String date = "N/A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_medication_schedule);
        databaseHelper = new DatabaseHelper(this);
        calendar = Calendar.getInstance();

        getMostRecent();

        bundle = getIntent().getExtras();

        String stuff = bundle.getString("patientName");
        Log.d("patientName schedule is", stuff);

        TextView nameView = findViewById(R.id.schedule_for);
        nameView.setText(stuff);

        listView = findViewById(R.id.medicationList);

        populateAssignedSchedules();

        RegisteredSchedulesAdapter adapter = new RegisteredSchedulesAdapter(getApplicationContext(), schedulesList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Object medName = adapterView.getItemAtPosition(i);

            Bundle bundleForMedicineInfo = new Bundle();
            bundleForMedicineInfo.putString("medicineName", medName.toString());
            bundleForMedicineInfo.putString("patientName", bundle.getString("patientName"));
            bundleForMedicineInfo.putLong("recordId", adapter.getItemId(i));

            Intent intent = new Intent(patient_medication_schedule.this, medicine_info.class);
            intent.putExtras(bundleForMedicineInfo);

            startActivity(intent);
        });

        Button sendSummary = findViewById(R.id.summary_btn);
        sendSummary.setOnClickListener(view -> {
            String number = "4479021076";
            StringBuilder medications = new StringBuilder();
            try {
                Cursor medData = databaseHelper.getAllMedNameFrequencySchedules(bundle.getString("patientName"));
                int numOfMeds = 0;
                while (medData.moveToNext()) {
                    String medName = medData.getString(1);
                    String frequency = medData.getString(2) + " times per day";
                    medications.append("Medicine Name: ").append(numOfMeds).append(medName).append("\n")
                            .append(frequency).append("\n");
                    numOfMeds++;
                    Log.d("Adding Med Entry:", medName + ";" + frequency);
                }
                medications.append("Most Recent Medicine Name: ").append(mostRecent).append("\n");
                medications.append("Date").append(date);

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(number, null, String.valueOf(medications), null, null);
                Toast.makeText(getApplicationContext(), "Summary sent!", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Some fields is Empty", Toast.LENGTH_LONG).show();
            }
        });

        Button backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(view -> startActivity(new Intent(patient_medication_schedule.this, DashboardActivity.class)));

        Button addBtn = findViewById(R.id.add);

        Intent intent = new Intent(patient_medication_schedule.this, set_medication_1.class);

        addBtn.setOnClickListener(view -> {
            Bundle bundleForStep1 = bundle;
            intent.putExtras(bundleForStep1);
            startActivity(intent);
        });
    }

    private void populateAssignedSchedules() {
        ArrayList<String> schedules = new ArrayList<>();
        Cursor data = databaseHelper.getAllMedNameFrequencySchedules(bundle.getString("patientName"));
        while (data.moveToNext()) {
            Integer recordID = data.getInt(0);
            Log.d("id: ", recordID.toString());
            String medName = data.getString(1);
            String frequency = "" + data.getString(2) + " times per day";
            String finalInfo = "" + recordID + "" + medName + "       " + frequency;
            Log.d("finalInfo", finalInfo);
            schedules.add(finalInfo);
        }
        schedulesList = schedules;
    }

    private void getMostRecent() {
        Cursor data = databaseHelper.getMostRecentUpdatedSchedule();
        while (data.moveToNext()) {
            mostRecent = data.getString(0);
            Log.d("med name: ", data.getString(0));
            dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            date = dateFormat.format(calendar.getTime());
        }
        last_update_view = findViewById(R.id.med_last_update);
        TextView takenAt = findViewById(R.id.takenAt);

        last_update_view.setText("Last Update: " + mostRecent);
        takenAt.setText(getString(R.string.taken_at, date));
    }
}

