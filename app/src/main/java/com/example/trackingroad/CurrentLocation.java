package com.example.trackingroad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CurrentLocation extends AppCompatActivity {

    Button btLocation,saveLocation;
    TextView textView1, textView2, textView3, textView4, textView5;

    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location);


        //addingBackButton
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        saveLocation=(Button)findViewById(R.id.saveLocation);
        btLocation = (Button) findViewById(R.id.bt_location);
        textView1 = (TextView) findViewById(R.id.text_view1);
        textView2 = (TextView) findViewById(R.id.text_view2);
        textView3 = (TextView) findViewById(R.id.text_view3);
        textView4 = (TextView) findViewById(R.id.text_view4);
        textView5 = (TextView) findViewById(R.id.text_view5);


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(CurrentLocation.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(CurrentLocation.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });


        saveLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                Location location = task.getResult();
                if (location != null) {
                    try {

                        Geocoder geocoder = new Geocoder(CurrentLocation.this, Locale.getDefault());

                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                        //set Latitude
                        textView1.setText(Html.fromHtml("<font color='#6200EE'><b>Latitude :</b><br></font>" + addresses.get(0).getLatitude()));

                        //set Longitude
                        textView2.setText(Html.fromHtml("<font color='#6200EE'><b>Longitude :</b><br></font>" + addresses.get(0).getLongitude()));

                        //set Country Name
                        textView3.setText(Html.fromHtml("<font color='#6200EE'><b>Country Name :</b><br></font>" + addresses.get(0).getCountryName()));

                        //set Locality
                        textView4.setText(Html.fromHtml("<font color='#6200EE'><b>Locality :</b><br></font>" + addresses.get(0).getLocality()));

                        //get Address
                        textView5.setText(Html.fromHtml("<font color='#6200EE'><b>Address :</b><br></font>" + addresses.get(0).getAddressLine(0)));
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}