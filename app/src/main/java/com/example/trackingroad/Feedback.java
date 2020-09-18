package com.example.trackingroad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends AppCompatActivity implements View.OnClickListener{

    EditText nameText,emailText,feedbackText;
    Button submitButton,clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        //addingBackButton
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        submitButton=(Button)findViewById(R.id.submitButtonId);
        clearButton=(Button)findViewById(R.id.clearButtonId);
        nameText=(EditText)findViewById(R.id.nameTextId);
        emailText=(EditText)findViewById(R.id.emailTextId);
        feedbackText=(EditText)findViewById(R.id.feedbackTextId);

        submitButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        try {
            String name=nameText.getText().toString();
            String email=emailText.getText().toString();
            String feedback=feedbackText.getText().toString();

            if(v.getId()==R.id.submitButtonId)
            {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/email");

                intent.putExtra(Intent.EXTRA_EMAIL,new String[] {"mohaimenhasan7@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT,"Feedback from App");
                intent.putExtra(Intent.EXTRA_TEXT,"Email:"+email+"\nName:"+name+"\nMessage:"+feedback);
                startActivity(Intent.createChooser(intent,"Feedback with"));
            }
            if(v.getId()==R.id.clearButtonId)
            {
                nameText.setText("");
                emailText.setText("");
                feedbackText.setText("");
            }

        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Exception:"+e, Toast.LENGTH_SHORT).show();
        }
    }
}