package com.example.covizi;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covizi.db.History;
import com.example.covizi.ui.home.HomeFragment;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QrCodeScan extends AppCompatActivity {

    Button btnScan;
    ProgressBar loadingPB;
    ArrayList<History> historyArrayList;
    RecyclerView recyclerView;
    HistoryListAdapter adapter;

    String ic_Num, Username, Phone_number ;
    SharedPreferences sharedPreferences;
    private User user;
    private DatabaseHelper databaseHelper;
    private static final String SHARED_PREF_NAME = "login_user";
    private static final String KEY_NAME = "IC";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_scan);



        sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        ic_Num = sharedPreferences.getString(KEY_NAME,null);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        user = databaseHelper.getUserData(ic_Num);
        Phone_number = (user.getPhoneNumber());
        Username =(user.getFirstName()+ " " + user.getLastName());

        btnScan = findViewById(R.id.btnScan);
        loadingPB = findViewById(R.id.idPB);
        historyArrayList = new ArrayList<>(); //initialize an empty arraylist

        //Scanning button listener for camera scan
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ZXing for QRcode scanning
                IntentIntegrator integrator = new IntentIntegrator(QrCodeScan.this);
                //set the properties of the scan
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.setOrientationLocked(true);

                integrator.initiateScan();


            }
        });

        // load existing history list from MockAPI
        retrieveHistoryFromApi();

        // Set up recycler view.
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new HistoryListAdapter(new HistoryListAdapter.HistoryDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(QrCodeScan.this));
        adapter.submitList(historyArrayList);

    } // end of onCreate

    // Get history data from MockAPI
    private void retrieveHistoryFromApi() {

        String url = "https://61cdfa0d7067f600179c5d4a.mockapi.io/places";

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(QrCodeScan.this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        loadingPB.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                // we are getting each json object.
                                JSONObject responseObj = response.getJSONObject(i);

                                String date = responseObj.getString("date");
                                String time = responseObj.getString("time");
                                String location = responseObj.getString("location");


                                // Add history item one by one onto the history Array List
                                historyArrayList.add(new History(date, time, location));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(QrCodeScan.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(jsonArrayRequest);

    } // end of retrieveHistoryFromApi

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //ZXing for receiving scan result
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                //Log.e("Scan*******", "Cancelled scan");
                Toast.makeText(QrCodeScan.this,"Cancelled scan",Toast.LENGTH_SHORT);
            } else {
                //Log.e("Scan", "Scanned");

                // result.getContents() returns a string of the QR code
                Toast.makeText(QrCodeScan.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

                // Get the location string from the QR Code scan
                String location = result.getContents(); //QR code location string
                String current_date = java.time.LocalDate.now().toString();//QR code date string
                String current_time = java.time.LocalTime.now().toString();//QR code time string

                // Insert / POST a new history item to the MockAPI using Volley
                History history = new History(current_date,current_time,location);
                saveHistory(current_date,current_time,location);

                // Add a new history to the historyList.
                historyArrayList.add(history);

                // Update the current recyclerview with the new history item added
                int historyListSize = historyArrayList.size();
                recyclerView.getAdapter().notifyItemInserted(historyListSize);
                recyclerView.getAdapter().notifyDataSetChanged();
                // Scroll to the bottom.
                recyclerView.smoothScrollToPosition(historyListSize);



                Intent i = new Intent(QrCodeScan.this, CheckInInfo.class);
                i.putExtra("Location", location);
                i.putExtra("Date",current_date);
                i.putExtra("Time",current_time);
                i.putExtra("Uname",Username);
                i.putExtra("PNum",Phone_number);
                startActivity(i);

            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void saveHistory(String current_date, String current_time, String location) {
        // url to post our data
        String url = "https://61cdfa0d7067f600179c5d4a.mockapi.io/places";

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(QrCodeScan.this);

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            // on below line we are displaying a success toast message.
                            Toast.makeText(QrCodeScan.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // method to handle errors.
                        Toast.makeText(QrCodeScan.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                    }
                }) {  // Body of the POST request

            @Override
            protected Map<String, String> getParams() {

                // below line we are creating a map (dictionary data type) for storing
                // our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our
                // key and value pair to our parameters.
                params.put("location", location);
                params.put("date", current_date);
                params.put("time", current_time);

                // at last we are returning our params.
                return params;
            }
        };

        // a json object request.
        queue.add(request);
    }

    public void back(View view) {

        finish();
    }
}