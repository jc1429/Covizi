package com.example.covizi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {

    private TextView tv_loginback;
    private TextInputLayout state_box,password_box,conpassword_box;
    private TextInputEditText firstName,lastName,icNum,phoneNum,password,conPassword;
    private AutoCompleteTextView states;
    private ArrayList<String> arrayList_state;
    private Button signup_btn;
    ArrayAdapter<String> arrayAdapter_state;
    private DatabaseHelper databaseHelper;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "login_user";
    private static final String KEY_NAME = "IC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        tv_loginback = findViewById(R.id.tv_loginback);
        state_box = findViewById(R.id.state_box);
        states = findViewById(R.id.state);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        icNum = findViewById(R.id.ic);
        phoneNum = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        conPassword = findViewById(R.id.con_password);
        signup_btn = findViewById(R.id.button_signup);


        states.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String state = (String) adapterView.getItemAtPosition(i);

                signup_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //
                        String firstname = firstName.getEditableText().toString();
                        String phone = phoneNum.getEditableText().toString();
                        String pass = password.getEditableText().toString();
                        String confirm_password = conPassword.getEditableText().toString();
                        String lastname = lastName.getEditableText().toString();
                        String ic = icNum.getEditableText().toString();
                        String status = "0";

                        databaseHelper = new DatabaseHelper(getApplicationContext());

                        if (validateInput(firstname,lastname,ic,phone,state,pass,confirm_password)){
                            boolean anyuser = databaseHelper.checkuser(ic);
                            boolean anyphone = databaseHelper.checkphone(phone);


                            if(anyuser && anyphone){
                                boolean success = databaseHelper.insertData(ic,firstname,lastname,state,status,phone,pass);
                                if(success){
                                    sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString(KEY_NAME,ic);
                                    editor.apply();
                                    Intent i = new Intent(RegistrationActivity.this,HealthDeclarationStep1.class);
                                    startActivity(i);
                                    finish();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(),"IC Number or Phone number has registered.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });


            }  });

        String ad = "Already a user? Login here";
        SpannableString ss1 = new SpannableString(ad);

        ForegroundColorSpan fcsGrey = new ForegroundColorSpan(Color.rgb(107, 119, 154));
        ss1.setSpan(fcsGrey, 16, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        };
        ss1.setSpan(clickableSpan, 16, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_loginback.setText(ss1);
        tv_loginback.setMovementMethod(LinkMovementMethod.getInstance());

        arrayList_state = new ArrayList<>();
        arrayList_state.add("Johor");
        arrayList_state.add("Kedah");
        arrayList_state.add("Kelantan");
        arrayList_state.add("Malacca");
        arrayList_state.add("Negeri Sembilan");
        arrayList_state.add("Pahang");
        arrayList_state.add("Penang");
        arrayList_state.add("Perak");
        arrayList_state.add("Perlis");
        arrayList_state.add("Sabah");
        arrayList_state.add("Sarawak");
        arrayList_state.add("Selangor");
        arrayList_state.add("Terengganu");
        arrayList_state.add("Kuala Lumpur");
        arrayList_state.add("Labuan");
        arrayList_state.add("Putrajaya");

        arrayAdapter_state = new ArrayAdapter<>(getApplicationContext(),R.layout.state_item,arrayList_state);
        states.setAdapter(arrayAdapter_state);
    }

    private Boolean validateInput(String firstname, String lastname, String ic, String phonenum, String state, String pass, String con_pass ){

        if (firstname.isEmpty() || lastname.isEmpty() ||
        ic.isEmpty() || phonenum.isEmpty() || state.isEmpty() || pass.isEmpty() || con_pass.isEmpty() || !pass.equals(con_pass)) {

            if (firstname.isEmpty()) {
                firstName.setError("First Name is required!");
                firstName.requestFocus();
            }
            if (lastname.isEmpty()) {
               lastName.setError("Last Name is required!");
                lastName.requestFocus();
            }
            if (ic.isEmpty()){
                icNum.setError("IC Number is required!");
                icNum.requestFocus();
            }
            if (pass.isEmpty()) {
                password.setError("Password is required!");
            }
            if (con_pass.isEmpty()) {
                conPassword.setError("Please type password again!");
                conPassword.requestFocus();
            }
            if (phonenum.isEmpty()) {
                phoneNum.setError("Phone Number is required!");
            }
            if(!pass.equals(con_pass)){
                conPassword.setError("Please enter same password!");
            }
            if(state.isEmpty()){
                state_box.setError("Please select your state.");
                state_box.requestFocus();
            }
            return false;
        }
        return true;
    }
}