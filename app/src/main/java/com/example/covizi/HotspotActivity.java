package com.example.covizi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.media.Image;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class HotspotActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

    private ImageView back_btn,tracker;
    private boolean isPermissionGranted;
    private TextInputEditText place;
    private TextView txt_covid_nearby;
    private ImageView search;
    GoogleMap mGoogleMap;
    private FusedLocationProviderClient mLocationClient;
    private int GPS_REQUEST_CODE;
    private User user;
    private DatabaseHelper databaseHelper;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "login_user";
    private static final String KEY_NAME = "IC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotspot);

        back_btn = findViewById(R.id.hotspot_backbtn);
        place = findViewById(R.id.places);
        search = findViewById(R.id.search_location);
        txt_covid_nearby = findViewById(R.id.txt_covidcases_near);

        mLocationClient = new FusedLocationProviderClient(this);

        sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String ic_Num = sharedPreferences.getString(KEY_NAME,null);
        databaseHelper = new DatabaseHelper(getApplicationContext());

        //Initialize new user object
        user = databaseHelper.getUserData(ic_Num);

        txt_covid_nearby.setText("Hi "+ user.getFirstName() + " , there have been 10 reported case(s) of COVID-19 within a 1km radius from your current location in the last 14 days.");

        initMap();

        search.setOnClickListener(this::geoLocate);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void geoLocate(View view) {

        String locationName = place.getText().toString();
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try{
        List<Address> addressList = geocoder.getFromLocationName(locationName,3);

        if(addressList.size()>0){
            Address address = addressList.get(0);

            gotoLocation(address.getLatitude(),address.getLongitude());

            mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(address.getLatitude(),address.getLongitude())));

        }
    } catch (IOException e) {
        e.printStackTrace();
        }
    }


    private void getCurrentLocation() {
        checkMyPermission();
        mLocationClient.getLastLocation().addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Location location = task.getResult();
                    if (location == null){
                        Intent i = new Intent(HotspotActivity.this, HomeActivity.class);
                        startActivity(i);
                        Toast.makeText(this,"Please try again!",Toast.LENGTH_SHORT).show();
                    }else{
                        gotoLocation(location.getLatitude(),location.getLongitude());
                    }
                }
                }
                );
    }

    private void gotoLocation(double latitude, double longitude) {
        LatLng latLng = new LatLng(latitude,longitude);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,18);
        MarkerOptions options = new MarkerOptions().position(latLng);
        mGoogleMap.addMarker(options);
        mGoogleMap.moveCamera(cameraUpdate);
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    private void initMap() {
        checkMyPermission();
        if(isPermissionGranted){
            if(isGPSenable()){
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        supportMapFragment.getMapAsync(this);
        getCurrentLocation();
    }}}

    private boolean isGPSenable(){
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        boolean providerEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if(providerEnable){
            return true;
        }else{
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("GPS Permission")
                    .setMessage("GPS is required for COVIZI to work. Please enable GPS.")
                    .setPositiveButton("Yes",((dialogInterface, i) -> {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(intent,GPS_REQUEST_CODE);
                    }))
                    .setCancelable(false)
                    .show();
        }
        return false;
    }

    private void checkMyPermission(){
        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Toast.makeText(HotspotActivity.this,"Permission Granted", Toast.LENGTH_SHORT).show();
                        isPermissionGranted = true;
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        finish();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                }).check();
    }

    @Override
    public void onMapReady(@NonNull @NotNull GoogleMap googleMap) {
        checkMyPermission();
        mGoogleMap = googleMap;
        mGoogleMap.setMyLocationEnabled(true);
    }

    @Override
    public void onConnected(@Nullable @org.jetbrains.annotations.Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull @NotNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GPS_REQUEST_CODE){
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            boolean providerEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            if (providerEnable){
                Toast.makeText(this,"GPS is enabled",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"GPS is not enable",Toast.LENGTH_SHORT).show();
            }
        }
    }
}