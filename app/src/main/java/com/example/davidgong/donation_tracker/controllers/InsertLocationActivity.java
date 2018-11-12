package com.example.davidgong.donation_tracker.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.davidgong.donation_tracker.R;
import com.example.davidgong.donation_tracker.model.Location;
import com.example.davidgong.donation_tracker.model.Model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class InsertLocationActivity extends AppCompatActivity {

    private EditText locationName;
    private Spinner locationTypeSpinner;
    private EditText locationLatitude;
    private EditText locationLongitude;
    private EditText locationAddress;
    private EditText locationCity;
    private EditText locationState;
    private EditText locationZip;
    private EditText locationNumber;

    private Button locationAddButton;

    private Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_location);

        locationName = (EditText) findViewById(R.id.locationName);
        locationTypeSpinner = (Spinner) findViewById(R.id.locationTypeSpinner);
        locationLatitude = (EditText) findViewById(R.id.locationLatitude);
        locationLongitude = (EditText) findViewById(R.id.locationLongitude);
        locationAddress = (EditText) findViewById(R.id.locationStreetAddress);
        locationCity = (EditText) findViewById(R.id.locationCity);
        locationState = (EditText) findViewById(R.id.locationState);
        locationZip = (EditText) findViewById(R.id.locationZip);
        locationNumber = (EditText) findViewById(R.id.locationPhoneNumber);

        locationAddButton = (Button) findViewById(R.id.locationAddButton);

        locationAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Location loc = new Location(
                        locationName.getText().toString(),
                        locationTypeSpinner.getSelectedItem().toString(),
                        Double.parseDouble(locationLatitude.getText().toString()),
                        Double.parseDouble(locationLongitude.getText().toString()),
                        locationAddress.getText().toString(),
                        locationCity.getText().toString(),
                        locationState.getText().toString(),
                        locationZip.getText().toString(),
                        locationNumber.getText().toString());
//                Location loc = new Location;
                model.addLocation(loc);
                writeModel();

                toastLocationAdded();
            }

        });

    }
    private void toastLocationAdded(){
        Toast.makeText(this, "Location has been added.", Toast.LENGTH_SHORT).show();
    }

    private void writeModel() {
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        try {
            fout = getApplicationContext().openFileOutput(model.locationFile, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(model);
            oos.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}