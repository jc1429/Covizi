package com.example.covizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void goToHome(View view) {

        Intent i = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(i);

    }

    public void goToScan(View view) {
        Intent i = new Intent(MainActivity.this, QrCodeScan.class);
        startActivity(i);
    }
}