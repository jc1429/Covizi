package com.example.covizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Vaccine_Status_1 extends AppCompatActivity {

    private TextView cert_name,cert_ic;
    String ic_Num;
    SharedPreferences sharedPreferences;
    private User user;
    private DatabaseHelper databaseHelper;
    private static final String SHARED_PREF_NAME = "login_user";
    private static final String KEY_NAME = "IC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_status1);

        cert_name = findViewById(R.id.digitalCertName);
        cert_ic = findViewById(R.id.digitalCertIC);

        sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        ic_Num = sharedPreferences.getString(KEY_NAME,null);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        user = databaseHelper.getUserData(ic_Num);
        cert_ic.setText(user.getIcNumber());
        cert_name.setText(user.getFirstName()+ " " + user.getLastName());
    }

    public void Register_For_Vaccine(View view) {

        Intent i = new Intent(Vaccine_Status_1.this, VaccinationBookingStep1.class);
        startActivity(i);
    }

    public void back(View view) {

        finish();
    }
}