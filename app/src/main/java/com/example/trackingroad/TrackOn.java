package com.example.trackingroad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TrackOn extends AppCompatActivity {

    EditText etSource,etDestination;
    Button btTrack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_on);


        etSource=(EditText)findViewById(R.id.et_sourceT);
        etDestination=(EditText)findViewById(R.id.et_destinationT);
        btTrack=(Button)findViewById(R.id.bt_trackT);


        btTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sSource=etSource.getText().toString().trim();
                String sDestination=etDestination.getText().toString().trim();


                if(sSource.equals("") && sDestination.equals(""))
                {
                    //when both location are blank
                    Toast.makeText(getApplicationContext(),"Enter both location",Toast.LENGTH_SHORT).show();
                }else {

                    DisplayTrack(sSource,sDestination);
                }
            }
        });
    }



    private void DisplayTrack(String sSource, String sDestination) {

        try{
            //when google map is installed
            Uri uri=Uri.parse("https://www.google.co.in/maps/dir/"+sSource+"/"+sDestination);

            Intent map=new Intent(Intent.ACTION_VIEW,uri);
            map.setPackage("com.google.android.apps.maps");
            map.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(map);
        }catch (ActivityNotFoundException e)
        {
            //when google map is not installed
            Uri uri=Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent mapS=new Intent(Intent.ACTION_VIEW,uri);
            mapS.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mapS);
        }
    }
}