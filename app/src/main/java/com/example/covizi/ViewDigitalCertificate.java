package com.example.covizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewDigitalCertificate extends AppCompatActivity {
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
        setContentView(R.layout.activity_view_digital_certificate);

        cert_name = findViewById(R.id.digitalCertName);
        cert_ic = findViewById(R.id.digitalCertIC);

        sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        ic_Num = sharedPreferences.getString(KEY_NAME,null);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        user = databaseHelper.getUserData(ic_Num);
        cert_ic.setText(user.getIcNumber());
        cert_name.setText(user.getFirstName()+ " " + user.getLastName());

    }

    public void back(View view) {
        Intent i = new Intent(ViewDigitalCertificate.this, HomeActivity.class);
        startActivity(i);
    }
}