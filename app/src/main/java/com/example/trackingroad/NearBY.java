package com.example.trackingroad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NearBY extends AppCompatActivity {

    Button fuelStation,hospital,school,college,university,gasStation,restaurant,shoppingMall,busStation,policeStation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_b_y);


        fuelStation=(Button)findViewById(R.id.fuelButtonId);
        gasStation=(Button)findViewById(R.id.gasStationButtonId);
        hospital=(Button)findViewById(R.id.hospitalButtonId);
        school=(Button)findViewById(R.id.schoolButtonId);
        college=(Button)findViewById(R.id.collegeButtonId);
        university=(Button)findViewById(R.id.universityButtonId);
        restaurant=(Button)findViewById(R.id.restaurantsButtonId);
        shoppingMall=(Button)findViewById(R.id.malButtonId);
        busStation=(Button)findViewById(R.id.busStationButtonId);
        policeStation=(Button)findViewById(R.id.policeStationButtonId);


        fuelStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    //when google map is installed
                    Uri uri=Uri.parse("geo:0,0?q=fuel station");

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
        });

        gasStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try{
                    //when google map is installed
                    Uri uri=Uri.parse("geo:0,0?q=gas station");

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
        });



        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try{
                    //when google map is installed
                    Uri uri=Uri.parse("geo:0,0?q=hospital");

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
        });



        school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try{
                    //when google map is installed
                    Uri uri=Uri.parse("geo:0,0?q=school");

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
        });


        college.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try{
                    //when google map is installed
                    Uri uri=Uri.parse("geo:0,0?q=college");

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
        });



        university.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    //when google map is installed
                    Uri uri=Uri.parse("geo:0,0?q=university");

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
        });


        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    //when google map is installed
                    Uri uri=Uri.parse("geo:0,0?q=restaurant");

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
        });



        shoppingMall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    //when google map is installed
                    Uri uri=Uri.parse("geo:0,0?q=shopping mall");

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
        });



        busStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    //when google map is installed
                    Uri uri=Uri.parse("geo:0,0?q=bus station");

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
        });



        policeStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    //when google map is installed
                    Uri uri=Uri.parse("geo:0,0?q=police station");

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
        });
    }
}