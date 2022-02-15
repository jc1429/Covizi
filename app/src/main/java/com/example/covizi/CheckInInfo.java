package com.example.covizi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.covizi.ui.home.HomeFragment;

public class CheckInInfo extends AppCompatActivity {

    private ImageView checkin_backbtn;
    private TextView date, time, Location, name, phoneNum,covidStatus,covidRisk;
    private CardView covidStatus_ctn, covidRisk_ctn;
    private User user;
    private Symptoms symptoms;
    private DatabaseHelper databaseHelper;
    private Integer count;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "login_user";
    private static final String KEY_NAME = "IC";

    public static String LOCATION, DATE, TIME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_info);

        checkin_backbtn = findViewById(R.id.checkin_btn);
        date = findViewById(R.id.checkInDate);
        time = findViewById(R.id.checkInTime);
        Location = findViewById(R.id.checkInLocation);
        name = findViewById(R.id.checkInUsername);
        phoneNum= findViewById(R.id.checkInPhoneNum);
        covidRisk = findViewById(R.id.txt_covidStatus);
        covidStatus = findViewById(R.id.txt_vaccinationStatus);
        covidRisk_ctn = findViewById(R.id.covidRiskStatus_BG);
        covidStatus_ctn = findViewById(R.id.vaccinationStatus_BG);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String location = extras.getString("Location");
            String current_date = extras.getString("Date");
            String current_time = extras.getString("Time");
            String Username = extras.getString("Uname");
            String Phone_number = extras.getString("PNum");
            //The key argument here must match that used in the other activity
            Location.setText("Location: "+ location);
            time.setText("Current Time: "+ current_time);
            date.setText("Date : "+ current_date);
            phoneNum.setText("Phone Number: "+ Phone_number);
            name.setText("Name: "+ Username);

            DATE = current_date;
            TIME = current_time;
            LOCATION=location;

            sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            String ic_Num = sharedPreferences.getString(KEY_NAME,null);

            databaseHelper = new DatabaseHelper(getApplicationContext());
            count=0;

            //Initialize new user object
            user = databaseHelper.getUserData(ic_Num);
            symptoms = databaseHelper.getUserSymptomData(ic_Num);

            String status = user.getStatus();

            if (status.equals("0")){
                covidStatus_ctn.setCardBackgroundColor(Color.rgb(253,0,0));
                covidStatus.setText("Not Vaccinated");
            }else if (status.equals("1")){
                covidStatus_ctn.setCardBackgroundColor(Color.rgb(255,81,81));
                covidStatus.setText("Partially Vaccinated");
            }else if (status.equals("2")){
                covidStatus_ctn.setCardBackgroundColor(Color.rgb(240,180,136));
                covidStatus.setText("Fully Vaccinated");
            }

            if (symptoms.getClosecontact().equals("Yes")){
                count+=1;
            }
            if (symptoms.getCough().equals("Yes")){
                count+=1;
            }
            if (symptoms.getFatigue().equals("Yes")){
                count+=1;
            }
            if (symptoms.getFever().equals("Yes")){
                count+=1;
            }
            if (symptoms.getLosstaste().equals("Yes")){
                count+=1;
            }
            if (symptoms.getMuscleache().equals("Yes")){
                count+=1;
            }
            if (symptoms.getPositive().equals("Yes")){
                count+=1;
            }
            if (symptoms.getShortbreath().equals("Yes")){
                count+=1;
            }
            if (symptoms.getTravel().equals("Yes")){
                count+=1;
            }
            if (symptoms.getVomit().equals("Yes")){
                count+=1;
            }
            if (symptoms.equals("Yes")){
                count+=1;
            }
            if (count<4){
                covidRisk_ctn.setCardBackgroundColor(Color.rgb(0,204,178));
                covidRisk.setText("No Symptom Low Risk");
            }else if(count >3 && count <7){
                covidRisk_ctn.setCardBackgroundColor(Color.rgb(255,81,81));
                covidRisk.setText("Some Symptoms Medium Risk");
            }else if(count>6){
                covidRisk_ctn.setCardBackgroundColor(Color.rgb(253,0,0));
                covidRisk.setText("Many Symptoms High Risk");
            }
        }



        checkin_backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                Intent i = new Intent(CheckInInfo.this, HomeFragment.class);
                startActivity(i);
            }

        });

    }

}