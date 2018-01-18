package com.example.yumnaasim.rtirpc;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Databases.Database;
import model.AccidentDetails;
import model.AccidentRecord;
import model.Patient;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.buttonNext);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        getDateTime();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*getting all inputs from user*/
                /*accident record input*/
                    EditText editTextDate = (EditText) findViewById(R.id.date);
                    String date = editTextDate.getText().toString();

                    EditText editTextEmergencyNo = (EditText) findViewById(R.id.emergencyNo);
                    String emergencyNo = editTextEmergencyNo.getText().toString();

                    Spinner spinner = (Spinner) findViewById(R.id.spinner);
                    String hospitalName = spinner.getSelectedItem().toString();

                    AccidentRecord accidentRecord = new AccidentRecord();
                    accidentRecord.setDate(date);
                    accidentRecord.setEmergencyNo(emergencyNo);
                    accidentRecord.setHospitalName(hospitalName);
                /*patient record input*/
                    EditText editTextName = (EditText) findViewById(R.id.name);
                    String name = editTextName.getText().toString();
                    EditText editTextAge = (EditText) findViewById(R.id.age);
                    String age = editTextAge.getText().toString();
                    EditText editTextAddress = (EditText) findViewById(R.id.address);
                    String address = editTextAddress.getText().toString();
                    EditText editTextMobile = (EditText) findViewById(R.id.mobile);
                    String mobile = editTextMobile.getText().toString();
                    EditText editTextOccup = (EditText) findViewById(R.id.occupation);
                    String occupation = editTextOccup.getText().toString();

                    RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupGender);
                    int id = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton) findViewById(id);
                    String gender = radioButton.getText().toString();

                    RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup);
                    int id1 = radioGroup1.getCheckedRadioButtonId();
                    RadioButton radioButton1 = (RadioButton) findViewById(id1);
                    String distraction = radioButton1.getText().toString();

                    RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup1);
                    int id2 = radioGroup2.getCheckedRadioButtonId();
                    RadioButton radioButton2 = (RadioButton) findViewById(id2);
                    String lane = radioButton2.getText().toString();

                    Spinner spinner1 = (Spinner) findViewById(R.id.spinner6);
                    String patientState = spinner1.getSelectedItem().toString();

                /*accident details input*/
                    EditText editTextTimeAcc = (EditText) findViewById(R.id.timeAcc);
                    String timeAcc = editTextTimeAcc.getText().toString();

                    EditText editTextTimeArr = (EditText) findViewById(R.id.timeArr);
                    String timeArr = editTextTimeArr.getText().toString();

                    Spinner spinner2 = (Spinner) findViewById(R.id.spinner1);
                    String arrivalVehicle = spinner2.getSelectedItem().toString();

                    EditText editTextAmb = (EditText) findViewById(R.id.ambulance);
                    String ambulance = editTextAmb.getText().toString();

                    Spinner spinner3 = (Spinner) findViewById(R.id.spinner2);
                    String historyProvider = spinner3.getSelectedItem().toString();

                    Spinner spinner4 = (Spinner) findViewById(R.id.spinner3);
                    String travellingReason = spinner4.getSelectedItem().toString();

                    Spinner spinner5 = (Spinner) findViewById(R.id.spinner4);
                    String vehicle1 = spinner5.getSelectedItem().toString();

                    Spinner spinner6 = (Spinner) findViewById(R.id.spinner5);
                    String vehicle2 = spinner6.getSelectedItem().toString();

                    Spinner spinner7 = (Spinner) findViewById(R.id.spinner9);
                    String collisionType = spinner7.getSelectedItem().toString();

                    EditText editTextLoc = (EditText) findViewById(R.id.loc);
                    String loc = editTextLoc.getText().toString();

                    Spinner spinnerLoc = (Spinner) findViewById(R.id.spinner10);
                    String locDet = spinnerLoc.getSelectedItem().toString();

                    AccidentDetails details = new AccidentDetails();
                    details.setTimeArrival(timeArr);
                    details.setTimeAccident(timeAcc);
                    details.setArrivalVehicle(arrivalVehicle);
                    details.setAmbulanceName(ambulance);
                    details.setHistoryProvider(historyProvider);
                    details.setTravellingReason(travellingReason);
                    details.setVehicle1(vehicle1);
                    details.setVehicle2(vehicle2);
                    details.setCollisionType(collisionType);
                    details.setLocation(loc);
                    details.setLocDetails(locDet);

                    Patient patient = new Patient();
                    patient.setName(name);
                    patient.setAge(age);
                    patient.setAddress(address);
                    patient.setOccupation(occupation);
                    patient.setMobile(mobile);
                    patient.setGender(gender);
                    patient.setDistractedBy(distraction);
                    patient.setLane(lane);
                    patient.setPatientState(patientState);


                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("AccidentRecord", accidentRecord);
                    intent.putExtra("Patient", patient);
                    intent.putExtra("AccidentDetails", details);
                    startActivity(intent);

            }
        });
        /*setting data on spinners*/
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hospital_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.ambulance_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.history_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);

        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.travelling_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner3.setAdapter(adapter3);

        Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.vehicles_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner4.setAdapter(adapter4);

        Spinner spinner5 = (Spinner) findViewById(R.id.spinner5);
// Create an ArrayAdapter using the string array and a default spinner layout
// Apply the adapter to the spinner
        spinner5.setAdapter(adapter4);

        Spinner spinner6 = (Spinner) findViewById(R.id.spinner6);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
                R.array.patient_ride_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner6.setAdapter(adapter5);

        Spinner spinner9 = (Spinner) findViewById(R.id.spinner9);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this,
                R.array.collision_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner9.setAdapter(adapter7);

        Spinner spinner10 = (Spinner) findViewById(R.id.spinner10);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this,
                R.array.loc_detail_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner10.setAdapter(adapter8);
    }

    @Override
    public void onBackPressed() {
           /* new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_warning_black_24dp)
                    .setTitle("Exit")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();*/
           super.onBackPressed();
        }

    private void getDateTime() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());

        EditText date = (EditText) findViewById(R.id.date);
        date.setText(formattedDate);

        String currentTimeString = new SimpleDateFormat("HH:mm:ss").format(new Date());
        EditText time = (EditText) findViewById(R.id.timeAcc);
// textView is the TextView view that should display it
        time.setText(currentTimeString);

    }

}
