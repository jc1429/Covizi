package com.example.covizi.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covizi.DatabaseHelper;
import com.example.covizi.R;
import com.example.covizi.Symptoms;
import com.example.covizi.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragmentForTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragmentForTab extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragmentForTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragmentForTab.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragmentForTab newInstance(String param1, String param2) {
        HomeFragmentForTab fragment = new HomeFragmentForTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private CardView vac_status,cov_status;
    private User user;
    private Symptoms symptoms;
    private DatabaseHelper databaseHelper;
    private TextView txtcov_status, txtvac_status, location, date, time;
    private Integer count;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "login_user";
    private static final String KEY_NAME = "IC";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_for_tab, container, false);

        vac_status = v.findViewById(R.id.vaccinationStatus_bg);
        cov_status = v.findViewById(R.id.covidRiskStatus_bg);
        txtcov_status = v.findViewById(R.id.txt_covidStatus);
        txtvac_status = v.findViewById(R.id.txt_vaccinationStatus);

        location = v.findViewById(R.id.txt_checkInLocation);
        date = v.findViewById(R.id.txt_checkInLocationDate);
        time = v.findViewById(R.id.txt_checkInLocationTime);

        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String ic_Num = sharedPreferences.getString(KEY_NAME,null);
        databaseHelper = new DatabaseHelper(getActivity());
        count=0;

        //Initialize new user object
        user = databaseHelper.getUserData(ic_Num);
        symptoms = databaseHelper.getUserSymptomData(ic_Num);
        String status = user.getStatus();

        if (status.equals("0")){
            vac_status.setCardBackgroundColor(Color.rgb(253,0,0));
            txtvac_status.setText("Not Vaccinated");
        }else if (status.equals("1")){
            vac_status.setCardBackgroundColor(Color.rgb(255,81,81));
            txtvac_status.setText("Partially Vaccinated");
        }else if (status.equals("2")){
            vac_status.setCardBackgroundColor(Color.rgb(240,180,136));
            txtvac_status.setText("Fully Vaccinated");
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
            cov_status.setCardBackgroundColor(Color.rgb(0,204,178));
            txtcov_status.setText("No Symptom Low Risk");
        }else if(count >3 && count <7){
            cov_status.setCardBackgroundColor(Color.rgb(255,81,81));
            txtcov_status.setText("Some Symptoms Medium Risk");
        }else if(count>6){
            cov_status.setCardBackgroundColor(Color.rgb(253,0,0));
            txtcov_status.setText("Many Symptoms High Risk");
        }

        fetchInfoUsingVolley();
        return v;
    }

    private void fetchInfoUsingVolley() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://61cdfa0d7067f600179c5d4a.mockapi.io/places";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i=0;i<response.length();i++){
                                JSONObject jsonObject = response.getJSONObject(response.length()-1);
                                location.setText(
                                        jsonObject.getString(
                                                "location"));
                                date.setText(
                                        jsonObject.getString(
                                                "date"));
                                time.setText(
                                        jsonObject.getString(
                                                "time"));
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Print error message
                Toast.makeText(
                        getContext(),
                        error.getMessage(),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        queue.add(jsonArrayRequest);

    }
}