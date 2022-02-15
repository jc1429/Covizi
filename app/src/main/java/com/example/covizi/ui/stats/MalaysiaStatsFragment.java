package com.example.covizi.ui.stats;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covizi.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.Date;

public class MalaysiaStatsFragment extends Fragment {

    private MalaysiaStatsViewModel mViewModel;

    public static MalaysiaStatsFragment newInstance() {
        return new MalaysiaStatsFragment();
    }

    TextView TotalCaseValue, TodayCaseValue, TotalRecoveredCaseValue, TodayRecoveredCaseValue, TotalDeathCaseValue, TodayDeathCaseValue, TotalActiveCaseValue;

    TextView DateTimeTotalCase, DateTimeTotalRecovered, DateTimeTotalDeath, DateTimeTotalActiveCase, DateRValue;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.malaysia_stats_fragment, container, false);


        TodayCaseValue = view.findViewById(R.id.txtNewCases2);
        TodayRecoveredCaseValue = view.findViewById(R.id.txtNewCases3);
        TodayDeathCaseValue = view.findViewById(R.id.txtNewCases4);

        TotalCaseValue = view.findViewById(R.id.txtValue2);
        TotalRecoveredCaseValue = view.findViewById(R.id.txtValue3);
        TotalDeathCaseValue = view.findViewById(R.id.txtValue4);
        TotalActiveCaseValue = view.findViewById(R.id.txtValue5);

        DateRValue = view.findViewById(R.id.txtdateTime);
        DateTimeTotalCase = view.findViewById(R.id.txtdateTime2);
        DateTimeTotalRecovered = view.findViewById(R.id.txtdateTime3);
        DateTimeTotalDeath = view.findViewById(R.id.txtdateTime4);
        DateTimeTotalActiveCase = view.findViewById(R.id.txtdateTime5);

        fetchAPIUsingVolley();




        return view;
    }

    private void fetchAPIUsingVolley() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://disease.sh/v3/covid-19/countries/malaysia";

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());

                            TotalCaseValue.setText(
                                    jsonObject.getString(
                                            "cases"));
                            TodayCaseValue.setText(
                                    jsonObject.getString(
                                            "todayCases"));
                            TotalRecoveredCaseValue.setText(
                                    jsonObject.getString(
                                            "recovered"));
                            TodayRecoveredCaseValue.setText(
                                    jsonObject.getString(
                                            "todayRecovered"));
                            TotalDeathCaseValue.setText(
                                    jsonObject.getString(
                                            "deaths"));
                            TodayDeathCaseValue.setText(
                                    jsonObject.getString(
                                            "todayDeaths") );
                            TotalActiveCaseValue.setText(
                                    jsonObject.getString(
                                            "active"));

                            // timestamp -> date
                            String current_timestamp = jsonObject.getString("updated");
                            Timestamp ts = new Timestamp(Long.parseLong(current_timestamp));
                            Date date = new Date(ts.getTime());

                            DateRValue.setText(date.toString());
                            DateTimeTotalCase.setText(date.toString());
                            DateTimeTotalRecovered.setText(date.toString());
                            DateTimeTotalDeath.setText(date.toString());
                            DateTimeTotalActiveCase.setText(date.toString());


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

        queue.add(stringRequest);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MalaysiaStatsViewModel.class);
        // TODO: Use the ViewModel
    }

}