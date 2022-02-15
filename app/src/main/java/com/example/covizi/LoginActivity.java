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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextView tv_signup;
    private Button signin_btn;
    private TextInputEditText ic_num,password;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "login_user";
    private static final String KEY_NAME = "IC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_signup = findViewById(R.id.tv_signup);
        signin_btn = findViewById(R.id.button_login);
        ic_num = findViewById(R.id.icNum);
        password = findViewById(R.id.passwords);
        sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        String text = "Don't have an account? Sign up";
        SpannableString ss = new SpannableString(text);

        ForegroundColorSpan fcsBlue = new ForegroundColorSpan(Color.rgb(62, 100, 255));
        ss.setSpan(fcsBlue, 23, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        };
        ss.setSpan(clickableSpan1, 23, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_signup.setText(ss);
        tv_signup.setMovementMethod(LinkMovementMethod.getInstance());

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String ic = ic_num.getEditableText().toString();
                String pass = password.getEditableText().toString().trim();

                editor.putString(KEY_NAME,ic);
                editor.apply();

                if (ic.isEmpty()){
                    ic_num.setError("Username is required!");
                    ic_num.requestFocus();
                }

                if(pass.isEmpty()){
                    password.setError("Password is required!");
                    password.requestFocus();
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                boolean userpass = databaseHelper.checkpass(ic,pass);

                if(!ic.isEmpty() && !pass.isEmpty()){
                    if(userpass){
                        Toast.makeText(getApplicationContext(),"Login successfully.",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"Your username/password is incorrect!",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Your username/password is incorrect!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}