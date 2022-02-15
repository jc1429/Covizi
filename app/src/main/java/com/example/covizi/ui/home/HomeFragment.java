package com.example.covizi.ui.home;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Debug;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covizi.DatabaseHelper;
import com.example.covizi.FeaturesFragment;
import com.example.covizi.QrCodeScan;
import com.example.covizi.R;
import com.example.covizi.ThingstoKnowFragment;
import com.example.covizi.User;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.Date;

public class HomeFragment extends Fragment {

    private static final int NUM_PAGES = 3;
    private ViewPager2 viewPager2;
    private CardView checkIN,vac_status,risk_status;
    private FragmentStateAdapter pagerAdapter;
    private HomeViewModel mViewModel;
    private TextView name,ic;
    private User user;
    private DatabaseHelper databaseHelper;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "login_user";
    private static final String KEY_NAME = "IC";
    private String[] titles = new String[]{"Home","Things to Know", "Features"};

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.home_fragment, container, false);

        //Initialization
        viewPager2 = v.findViewById(R.id.viewpager2);
        checkIN = v.findViewById(R.id.checkIn_bg);
        vac_status = v.findViewById(R.id.vaccinationStatus_bg);
        risk_status = v.findViewById(R.id.covidRiskStatus_bg);
        ic = v.findViewById(R.id.txtICNumber);
        name = v.findViewById(R.id.txtUsername);


        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String ic_Num = sharedPreferences.getString(KEY_NAME,null);
        databaseHelper = new DatabaseHelper(getActivity());

        //Initialize new user object
        user = databaseHelper.getUserData(ic_Num);
        ic.setText(user.getIcNumber());
        name.setText(user.getFirstName() + " " + user.getLastName());



        pagerAdapter = new PagerAdapter(this);
        viewPager2.setAdapter(pagerAdapter);
        TabLayout tabLayout = v.findViewById(R.id.tab);
        new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> tab.setText(titles[position]))).attach();



        return v;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

}

class PagerAdapter extends FragmentStateAdapter {

    public PagerAdapter(HomeFragment fa) {super(fa);}

    @Override
    public Fragment createFragment(int pos){
        switch(pos) {
            case 1:{
                return new ThingstoKnowFragment();
            }
            case 2:{
                return new FeaturesFragment();
            }
            default:
                return new HomeFragmentForTab();
        }
    }

    @Override
    public int getItemCount() { return 3; }
}

