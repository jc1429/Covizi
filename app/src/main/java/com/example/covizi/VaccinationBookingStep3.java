package com.example.covizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class VaccinationBookingStep3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_booking_step3);

        Spinner spinner = findViewById(R.id.vb_vd_Q1_Ans);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Vaccine_Type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner_1 = findViewById(R.id.vb_vd_Q2A_Ans);
        ArrayAdapter<CharSequence> adapter_1 = ArrayAdapter.createFromResource(this, R.array.spinner_Ans, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_1.setAdapter(adapter_1);
        spinner_1.setOnItemSelectedListener(this);

        Spinner spinner_2 = findViewById(R.id.vb_vd_Q2B_Ans);
        ArrayAdapter<CharSequence> adapter_2 = ArrayAdapter.createFromResource(this, R.array.spinner_Ans, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_2.setAdapter(adapter_2);
        spinner_2.setOnItemSelectedListener(this);

        Spinner spinner_3 = findViewById(R.id.vb_vd_Q2C_Ans);
        ArrayAdapter<CharSequence> adapter_3 = ArrayAdapter.createFromResource(this, R.array.spinner_Ans, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_3.setAdapter(adapter_3);
        spinner_3.setOnItemSelectedListener(this);
    }

    public void back(View view) {
        finish();
    }

    public void Submit(View view) {
        Intent i = new Intent(VaccinationBookingStep3.this, Vaccine_Status_2.class);
        startActivity(i);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        if (!text.equals("Please Select Your Answer")) {
            Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}