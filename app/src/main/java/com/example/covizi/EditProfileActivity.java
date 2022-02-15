package com.example.covizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class EditProfileActivity extends AppCompatActivity {

    private ImageView back_btn;
    private Button update_btn;
    private User user;
    private TextInputEditText name, phone, ic,states;
    private DatabaseHelper databaseHelper;
    String ic_Num;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "login_user";
    private static final String KEY_NAME = "IC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        back_btn = findViewById(R.id.profile_backbtn);
        update_btn = findViewById(R.id.button_update1);
        name = findViewById(R.id.first_name_edt);
        ic = findViewById(R.id.ic_number_edt);
        phone = findViewById(R.id.phone_number_edt);
        states = findViewById(R.id.state_edt);
        sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        ic_Num = sharedPreferences.getString(KEY_NAME,null);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        user = databaseHelper.getUserData(ic_Num);

        name.setText(user.getFirstName() + "" + user.getLastName());
        ic.setText(user.getIcNumber());
        phone.setText(user.getPhoneNumber());
        states.setText(user.getState());

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phones = phone.getEditableText().toString();
                updateData(ic_Num,phones);
            }
        });
    }

    private void updateData(String ic, String phone){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        boolean phonechecker = databaseHelper.checkedetphone(phone,ic);

        if(phonechecker){
            boolean success = databaseHelper.updateUserData(ic,phone);

            if(success){
                Toast.makeText(getApplicationContext(),"Profile information updated successfully.",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(EditProfileActivity.this,HomeActivity.class);
                startActivity(i);
                finish();
            }else{
                Toast.makeText(getApplicationContext(),"Please try again!",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"The phone number has registered",Toast.LENGTH_SHORT).show();
        }
    }
}