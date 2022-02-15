package com.example.covizi.ui.profile;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.covizi.DatabaseHelper;
import com.example.covizi.EditProfileActivity;
import com.example.covizi.HotspotActivity;
import com.example.covizi.R;
import com.example.covizi.Symptoms;
import com.example.covizi.User;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    private Button update_btn;
    private User user;
    private Integer count;
    private Symptoms symptoms;
    private TextView name,phone,state,ic,risk_user;
    private DatabaseHelper databaseHelper;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "login_user";
    private static final String KEY_NAME = "IC";


    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         View v = inflater.inflate(R.layout.profile_fragment, container, false);
         update_btn = v.findViewById(R.id.update_profilebtn);
         name = v.findViewById(R.id.name_content);
         ic = v.findViewById(R.id.ic_content);
         phone = v.findViewById(R.id.phone_content);
         state = v.findViewById(R.id.state_content);
         risk_user = v.findViewById(R.id.risk_user);

        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String ic_Num = sharedPreferences.getString(KEY_NAME,null);
        databaseHelper = new DatabaseHelper(getActivity());
        user = databaseHelper.getUserData(ic_Num);
        symptoms = databaseHelper.getUserSymptomData(ic_Num);
        name.setText(user.getFirstName() + " " + user.getLastName());
        ic.setText(user.getIcNumber());
        phone.setText(user.getPhoneNumber());
        state.setText(user.getState());
        count=0;

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
            risk_user.setText("Low Risk");
        }else if(count >3 && count <7){
            risk_user.setText("Medium Risk");
        }else if(count>6){
            risk_user.setText("High Risk");
        }

         update_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                 startActivity(intent);
             }
         });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}