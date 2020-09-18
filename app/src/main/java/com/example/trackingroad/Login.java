package com.example.trackingroad;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    TextView setProfileNameText;
    static TextView distance,time;
    Button staticButton,mapButton,vehicleButton,locationButton,gasStationButton,trackButton,
            internetConnection,distanceTwoPlace,btnStart,btnStop,btnPause;
    ImageButton profileSetting,logout,vehicleInformation;

    static boolean status;
    LocationManager locationManager;
    static long startTime,endTime;
    static ProgressDialog progressDialog;
    static int p=0;

    LocationService myService;
    FirebaseAuth mAuth;

    private ServiceConnection sc=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            LocationService.LocalBinder binder=(LocationService.LocalBinder)iBinder;
            myService=binder.getServices();
            status=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            status=false;
        }
    };


    @Override
    protected void onDestroy() {
        if(status==true)
        {
            unbindService();
        }
        super.onDestroy();
    }

    private void unbindService() {

        if(status==false)
        {
            return;
        }
        Intent i=new Intent(getApplicationContext(),LocationService.class);
        unbindService(sc);
        status=false;
    }

    @Override
    public void onBackPressed() {
        if(status==false)
        {
            super.onBackPressed();
        }
        else
        {
            moveTaskToBack(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case 1000:
            {
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this,"GRANTED",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this,"DENIED",Toast.LENGTH_SHORT).show();
                }
            }
            return;
        }
    }

    //ON CREATE//
    ///////////////
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Home");

        //Request Permission
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED )
        {
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            },1000);
        }

        distance=(TextView)findViewById(R.id.distance);
        time=(TextView)findViewById(R.id.time);
        btnStart=(Button)findViewById(R.id.btnStart);
        btnPause=(Button)findViewById(R.id.btnPause);
        btnStop=(Button)findViewById(R.id.btnStop);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        setProfileNameText=(TextView)findViewById(R.id.profileEmailTextId);

        staticButton=(Button)findViewById(R.id.staticId);
        mapButton=(Button)findViewById(R.id.mapId);
        vehicleButton=(Button)findViewById(R.id.vehicleId);
        locationButton=(Button)findViewById(R.id.locationId);
        gasStationButton=(Button)findViewById(R.id.gasStationId);
        trackButton=(Button)findViewById(R.id.trackId);
        distanceTwoPlace=(Button)findViewById(R.id.distanceTwoPlaceId);
        internetConnection=(Button)findViewById(R.id.checkInternetId);

        mAuth=FirebaseAuth.getInstance();

        vehicleInformation=(ImageButton)findViewById(R.id.vehicleInformationButton);
        profileSetting=(ImageButton)findViewById(R.id.profileSettingButton);
        logout=(ImageButton)findViewById(R.id.logoutButton);


        staticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent statics=new Intent(Login.this,Static.class);
                startActivity(statics);

            }
        });


        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent currentLocation=new Intent(getApplicationContext(),CurrentLocation.class);
                startActivity(currentLocation);
            }
        });




        vehicleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent vehicle=new Intent(Login.this,Vehicle.class);
                startActivity(vehicle);
            }
        });




        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });




        //
        trackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent track=new Intent(getApplicationContext(),TrackOn.class);
                startActivity(track);
            }
        });




        vehicleInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vehicleInfo=new Intent(Login.this,VehicleInformation.class);
                startActivity(vehicleInfo);
            }
        });




        profileSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent showSetting=new Intent(Login.this,profileSetting.class);
                startActivity(showSetting);
            }
        });




        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                finish();
                Intent logout=new Intent(Login.this,MainActivity.class);
                startActivity(logout);
            }
        });



        internetConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkConnection();

            }
        });


        distanceTwoPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent dist=new Intent(getApplicationContext(),DistanceTwoPlace.class);
                startActivity(dist);

            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkGPS();
                locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);

                if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                    return;

                if(status==false)
                {
                    bindService();
                }
                progressDialog=new ProgressDialog(Login.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Getting Location....");
                progressDialog.show();


                btnStart.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);
                btnPause.setText("Pause");
                btnStop.setVisibility(View.VISIBLE);

            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnPause.getText().toString().equalsIgnoreCase("Pause"))
                {
                    btnPause.setText("Resume");
                    p=1;

                }

                else if(btnPause.getText().toString().equalsIgnoreCase("Resume"))
                {
                    checkGPS();
                    locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);

                    if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                        return;

                    btnPause.setText("Pause");
                    p=0;
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(status==true)
                {
                    unbindService();
                }
                btnStart.setVisibility(View.VISIBLE);
                btnPause.setText("Pause");
                btnPause.setVisibility(View.GONE);
                btnStop.setVisibility(View.GONE);
            }
        });


        gasStationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nearBy=new Intent(getApplicationContext(),NearBY.class);
                startActivity(nearBy);
            }
        });
    }

    private void checkGPS() {

        locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);

        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            showGPSDisabledAlert();
    }

    private void showGPSDisabledAlert() {

        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Enable GPS to use application")
                .setCancelable(false)
                .setPositiveButton("Enable GPS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);

                    }
                });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();
            }
        });
        AlertDialog alert=alertDialogBuilder.create();
        alert.show();
    }


    private void bindService() {

        if(status==true)
            return;

        Intent i=new Intent(getApplicationContext(),LocationService.class);
        bindService(i,sc,BIND_AUTO_CREATE);
        status=true;
        startTime=System.currentTimeMillis();

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void checkConnection()
    {
        ConnectivityManager manager=(ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork=manager.getActiveNetworkInfo();

        if(null!=activeNetwork)
        {
            if(activeNetwork.getType()==ConnectivityManager.TYPE_WIFI)
            {
                Toast.makeText(getApplicationContext(),"Wifi Enable",Toast.LENGTH_SHORT).show();
            }
            else if(activeNetwork.getType()==ConnectivityManager.TYPE_MOBILE)
            {
                Toast.makeText(getApplicationContext(),"Network Enable",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
        }
    }
}