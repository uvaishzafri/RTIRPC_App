package com.example.yumnaasim.rtirpc;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.io.Serializable;


import model.AccidentDetails;
import model.AccidentRecord;
import model.Patient;
import model.PatientHealth;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.accident_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.btnNext);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*taking user input*/
                RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.respiratorRate);
                int id1 = radioGroup1.getCheckedRadioButtonId();

                RadioButton radioButton1 = (RadioButton) findViewById(id1);
                String respiratorRate = radioButton1.getText().toString();

                RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.bloodPressure);
                int id2 = radioGroup2.getCheckedRadioButtonId();
                RadioButton radioButton2 = (RadioButton) findViewById(id2);
                String bloodPressure = radioButton2.getText().toString();

                RadioGroup radioGroup3 = (RadioGroup) findViewById(R.id.gcs);
                int id3 = radioGroup3.getCheckedRadioButtonId();
                RadioButton radioButton3 = (RadioButton) findViewById(id3);
                String gcs = radioButton3.getText().toString();

                RadioGroup radioGroup4 = (RadioGroup) findViewById(R.id.eyeRes1);
                int id4 = radioGroup4.getCheckedRadioButtonId();
                RadioButton radioButton4 = (RadioButton) findViewById(id4);
                String eyeRes1 = radioButton4.getText().toString();

                RadioGroup radioGroup5 = (RadioGroup) findViewById(R.id.eyeRes2);
                int id5 = radioGroup5.getCheckedRadioButtonId();
                RadioButton radioButton5 = (RadioButton) findViewById(id5);
                String eyeRes2 = radioButton5.getText().toString();

                RadioGroup radioGroup6 = (RadioGroup) findViewById(R.id.verbalRes);
                int id6 = radioGroup6.getCheckedRadioButtonId();
                RadioButton radioButton6 = (RadioButton) findViewById(id6);
                String verRes = radioButton6.getText().toString();

                PatientHealth patientHealth = new PatientHealth();
                patientHealth.setRespiratorRate(respiratorRate);
                patientHealth.setBloodPressure(bloodPressure);
                patientHealth.setGcs(gcs);
                patientHealth.setEyeResponse1(eyeRes1);
                patientHealth.setEyeResponse2(eyeRes2);
                patientHealth.setVerbalResponse1(verRes);

                Spinner spinner1 = (Spinner) findViewById(R.id.spinner);
                String locDes = spinner1.getSelectedItem().toString();

                /*getting intent values*/
                Patient patient = (Patient) getIntent().getSerializableExtra("Patient");
                AccidentRecord accidentRecord = (AccidentRecord) getIntent().getSerializableExtra("AccidentRecord");
                AccidentDetails details = (AccidentDetails) getIntent().getSerializableExtra("AccidentDetails");

                details.setLocDescription(locDes);
                /*passing intent*/
                Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
                intent.putExtra("PatientHealth", patientHealth);
                intent.putExtra("Patient",patient);
                intent.putExtra("AccidentRecord",accidentRecord);
                intent.putExtra("AccidentDetails",details);
                startActivity(intent);

            }
        });
        Button buttonPrev = (Button) findViewById(R.id.btnPrevious);
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
