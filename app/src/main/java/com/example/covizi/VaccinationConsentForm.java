package com.example.covizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class VaccinationConsentForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_consent_form);

        Spinner spinner = findViewById(R.id.spinnerQ1A_Ans);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_Ans, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner_1 = findViewById(R.id.spinnerQ1B_Ans);
        ArrayAdapter<CharSequence> adapter_1 = ArrayAdapter.createFromResource(this, R.array.spinner_Ans, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_1.setAdapter(adapter_1);
        spinner_1.setOnItemSelectedListener(this);

        Spinner spinner_2 = findViewById(R.id.spinnerQ1C_Ans);
        ArrayAdapter<CharSequence> adapter_2 = ArrayAdapter.createFromResource(this, R.array.spinner_Ans, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_2.setAdapter(adapter_2);
        spinner_2.setOnItemSelectedListener(this);

        Spinner spinner_3 = findViewById(R.id.spinnerQ1D_Ans);
        ArrayAdapter<CharSequence> adapter_3 = ArrayAdapter.createFromResource(this, R.array.spinner_Ans, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_3.setAdapter(adapter_3);
        spinner_3.setOnItemSelectedListener(this);

        Spinner spinner_4 = findViewById(R.id.spinnerQ1E_Ans);
        ArrayAdapter<CharSequence> adapter_4 = ArrayAdapter.createFromResource(this, R.array.spinner_Ans, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_4.setAdapter(adapter_4);
        spinner_4.setOnItemSelectedListener(this);

        Spinner spinner_5 = findViewById(R.id.spinnerQ1F_Ans);
        ArrayAdapter<CharSequence> adapter_5 = ArrayAdapter.createFromResource(this, R.array.spinner_Ans, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_5.setAdapter(adapter_5);
        spinner_5.setOnItemSelectedListener(this);

        Spinner spinner_6 = findViewById(R.id.spinnerQ1G_Ans);
        ArrayAdapter<CharSequence> adapter_6 = ArrayAdapter.createFromResource(this, R.array.spinner_Ans, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_6.setAdapter(adapter_6);
        spinner_6.setOnItemSelectedListener(this);

        Spinner spinner_7 = findViewById(R.id.spinnerQ1H_Ans);
        ArrayAdapter<CharSequence> adapter_7 = ArrayAdapter.createFromResource(this, R.array.spinner_Ans, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_7.setAdapter(adapter_7);
        spinner_7.setOnItemSelectedListener(this);

        Spinner spinner_8 = findViewById(R.id.spinnerQ1I_Ans);
        ArrayAdapter<CharSequence> adapter_8 = ArrayAdapter.createFromResource(this, R.array.spinner_Ans, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_8.setAdapter(adapter_8);
        spinner_8.setOnItemSelectedListener(this);

        Spinner spinner_9 = findViewById(R.id.spinnerQ1J_Ans);
        ArrayAdapter<CharSequence> adapter_9 = ArrayAdapter.createFromResource(this, R.array.spinner_Ans, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_9.setAdapter(adapter_9);
        spinner_9.setOnItemSelectedListener(this);

        Spinner spinner_10 = findViewById(R.id.spinnerQ1K_Ans);
        ArrayAdapter<CharSequence> adapter_10 = ArrayAdapter.createFromResource(this, R.array.spinner_Ans, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_10.setAdapter(adapter_10);
        spinner_10.setOnItemSelectedListener(this);

        Spinner spinner_11 = findViewById(R.id.spinnerQ1L_Ans);
        ArrayAdapter<CharSequence> adapter_11 = ArrayAdapter.createFromResource(this, R.array.spinner_Ans, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_11.setAdapter(adapter_11);
        spinner_11.setOnItemSelectedListener(this);
    }

    public void onCheckboxClicked_3(View view) {
    }

    public void onCheckboxClicked_2(View view) {
    }

    public void onCheckboxClicked_1(View view) {
    }

    public void back(View view) {
        finish();
    }

    public void Submit(View view) {
        Toast.makeText(getApplicationContext(), "Form submitted successfully ! ",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(VaccinationConsentForm.this, HomeActivity.class);
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