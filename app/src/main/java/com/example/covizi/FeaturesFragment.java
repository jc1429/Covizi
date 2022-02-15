package com.example.covizi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FeaturesFragment extends Fragment {

    private ImageView hotspot,faq,report,vac_status,vac_form,logout;
    private User user;
    private DatabaseHelper databaseHelper;
    private String status;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "login_user";
    private static final String KEY_NAME = "IC";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_features, container, false);

        hotspot = v.findViewById(R.id.img_Hotspot);
        faq = v.findViewById(R.id.img_qna);
        report = v.findViewById(R.id.img_report);
        vac_status = v.findViewById(R.id.img_vaccine_status);
        vac_form = v.findViewById(R.id.img_vaccine_form);
        logout = v.findViewById(R.id.img_logout);

        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String ic_Num = sharedPreferences.getString(KEY_NAME,null);
        databaseHelper = new DatabaseHelper(getActivity());
        //Initialize new user object
        user = databaseHelper.getUserData(ic_Num);
        status = user.getStatus();

        hotspot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HotspotActivity.class);
                startActivity(intent);
            }
        });

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FAQActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MakeReport.class);
                startActivity(intent);
            }
        });

        vac_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), VaccinationConsentForm.class);
                startActivity(intent);
            }
        });

        vac_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (status.equals("0")){
                    Intent intent = new Intent(getActivity(), Vaccine_Status_1.class);
                    startActivity(intent);
                }else if (status.equals("1")){
                    Intent intent = new Intent(getActivity(), Vaccine_Status_4.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getActivity(), VaccinationStatus.class);
                    startActivity(intent);
                }

            }
        });
        // Inflate the layout for this fragment
        return v;
    }

}