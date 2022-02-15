package com.example.covizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Vaccine_Health_Dec_2 extends AppCompatActivity {
    private RadioGroup radioGroup1a,radioGroup1b,radioGroup1c,radioGroup1d,radioGroup1e,radioGroup1f,radioGroup1g,radioGroup2,radioGroup3,radioGroup4;
    private RadioButton radioButton1a, radioButton1b, radioButton1c, radioButton1d, radioButton1e, radioButton1f, radioButton1g, radioButton2, radioButton3, radioButton4;
    private Button update_btn;
    private String q1a,q1b,q1c,q1d,q1e,q1f,q1g,q2,q3,q4;
    private DatabaseHelper databaseHelper;
    SharedPreferences sharedPreferences;
    private String ic_num = null;
    private static final String SHARED_PREF_NAME = "login_user";
    private static final String KEY_NAME = "IC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_health_dec2);
        radioGroup1a = Vaccine_Health_Dec_2.this.findViewById(R.id.spinnerQ1A_Ans);
        radioGroup1b = Vaccine_Health_Dec_2.this.findViewById(R.id.spinnerQ1B_Ans);
        radioGroup1c = Vaccine_Health_Dec_2.this.findViewById(R.id.spinnerQ1C_Ans);
        radioGroup1d = Vaccine_Health_Dec_2.this.findViewById(R.id.spinnerQ1D_Ans);
        radioGroup1e = Vaccine_Health_Dec_2.this.findViewById(R.id.spinnerQ1E_Ans);
        radioGroup1f = Vaccine_Health_Dec_2.this.findViewById(R.id.spinnerQ1F_Ans);
        radioGroup1g = Vaccine_Health_Dec_2.this.findViewById(R.id.spinnerQ1G_Ans);
        radioGroup2 = Vaccine_Health_Dec_2.this.findViewById(R.id.spinnerQ2_Ans);
        radioGroup3 = Vaccine_Health_Dec_2.this.findViewById(R.id.spinnerQ3_Ans);
        radioGroup4 = Vaccine_Health_Dec_2.this.findViewById(R.id.spinnerQ4_Ans);
        update_btn = Vaccine_Health_Dec_2.this.findViewById(R.id.button_submit_status);


        //Shared preference to get ic
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        ic_num = sharedPreferences.getString(KEY_NAME,null);

        update_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                int radioIdq1a = radioGroup1a.getCheckedRadioButtonId();
                int radioIdq1b = radioGroup1b.getCheckedRadioButtonId();
                int radioIdq1c = radioGroup1c.getCheckedRadioButtonId();
                int radioIdq1d = radioGroup1d.getCheckedRadioButtonId();
                int radioIdq1e = radioGroup1e.getCheckedRadioButtonId();
                int radioIdq1f = radioGroup1f.getCheckedRadioButtonId();
                int radioIdq1g = radioGroup1g.getCheckedRadioButtonId();
                int radioIdq2 = radioGroup2.getCheckedRadioButtonId();
                int radioIdq3 = radioGroup3.getCheckedRadioButtonId();
                int radioIdq4 = radioGroup4.getCheckedRadioButtonId();

                radioButton1a = findViewById(radioIdq1a);
                radioButton1b = findViewById(radioIdq1b);
                radioButton1c = findViewById(radioIdq1c);
                radioButton1d = findViewById(radioIdq1d);
                radioButton1e = findViewById(radioIdq1e);
                radioButton1f = findViewById(radioIdq1f);
                radioButton1g = findViewById(radioIdq1g);
                radioButton2 = findViewById(radioIdq2);
                radioButton3 = findViewById(radioIdq3);
                radioButton4 = findViewById(radioIdq4);

                if (radioButton1a != null){
                    q1a = radioButton1a.getText().toString();
                }
                if (radioButton1b != null){
                    q1b = radioButton1b.getText().toString();
                }
                if (radioButton1c != null){
                    q1c = radioButton1c.getText().toString();
                }
                if (radioButton1d !=null){
                    q1d = radioButton1d.getText().toString();
                }
                if (radioButton1e !=null){
                    q1e = radioButton1e.getText().toString();
                }
                if (radioButton1f != null) {
                    q1f = radioButton1f.getText().toString();
                }
                if(radioButton1g !=null){
                    q1g = radioButton1g.getText().toString();
                }
                if(radioButton2 !=null){
                    q2 = radioButton2.getText().toString();
                }
                if(radioButton3 !=null){
                    q3 = radioButton3.getText().toString();
                }
                if(radioButton4 !=null){
                    q4 = radioButton4.getText().toString();
                }

                if (!q1a.isEmpty() && !q1b.isEmpty() && !q1c.isEmpty() && !q1d.isEmpty() &&
                        !q1e.isEmpty() && !q1f.isEmpty() && !q1g.isEmpty() && !q2.isEmpty() && !q3.isEmpty() && !q4.isEmpty()) {
                    databaseHelper = new DatabaseHelper(getApplicationContext());

                    boolean isUpdate = databaseHelper.UpdateUserSymptoms(ic_num,q1a,q1b,q1c,q1d,q1e,q1f,q1g,q2,q3,q4);

                    if(isUpdate)
                    {
                        Toast.makeText(getApplicationContext(), "User Health Declaration Status Updated ! ",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "User Health Declaration Status Not Updated ! ",Toast.LENGTH_SHORT).show();
                    }

                    Intent i = new Intent(Vaccine_Health_Dec_2.this, Vaccine_Status_3.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Please answer all questions!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void back(View view) {
        Intent i = new Intent(Vaccine_Health_Dec_2.this, Vaccine_Health_Dec_1.class);
        startActivity(i);
    }

}