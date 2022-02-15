package com.example.covizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class VaccinationBookingStep1 extends AppCompatActivity {

    private TextInputEditText name,ic,phone;
    private DatabaseHelper databaseHelper;
    private User user;
    SharedPreferences sharedPreferences;
    private String ic_num = null;
    private static final String SHARED_PREF_NAME = "login_user";
    private static final String KEY_NAME = "IC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_booking_step1);

        name = findViewById(R.id.first_name2);
        ic = findViewById(R.id.ic_number2);
        phone = findViewById(R.id.phone_number2);

        //Shared preference to get ic
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        ic_num = sharedPreferences.getString(KEY_NAME,null);

        //initialize databasehelper
        databaseHelper = new DatabaseHelper(this);
        user = databaseHelper.getUserData(ic_num);

        name.setText(user.getFirstName() + "" + user.getLastName());
        ic.setText(user.getIcNumber());
        phone.setText(user.getPhoneNumber());
    }

    public void back(View view) {
        finish();
    }

    public void goToStep2(View view) {
        Intent i = new Intent(VaccinationBookingStep1.this, VaccinationBookingStep2.class);
        startActivity(i);
    }
}