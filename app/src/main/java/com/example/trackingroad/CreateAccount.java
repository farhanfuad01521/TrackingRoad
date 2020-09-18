package com.example.trackingroad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener{

    EditText emailText,signPasswordText,fullName,phoneNumber;
    Button signUp,clear,signIn;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;

    ProgressBar progressBar;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        this.setTitle("SignUp");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        mAuth = FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        progressBar=(ProgressBar)findViewById(R.id.progressBarId);


        fullName=(EditText)findViewById(R.id.FullNameId);
        emailText=(EditText)findViewById(R.id.emailTextId);
        phoneNumber=(EditText)findViewById(R.id.phoneNumberId);
        signPasswordText=(EditText)findViewById(R.id.passwordId);

        signUp=(Button)findViewById(R.id.newSignUptId);
        clear=(Button)findViewById(R.id.signUpClearId);
        signIn=(Button)findViewById(R.id.signInId);


        signIn.setOnClickListener(this);
        clear.setOnClickListener(this);
        signUp.setOnClickListener(this);
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
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.signInId:

                Intent signIn=new Intent(CreateAccount.this,MainActivity.class);
                startActivity(signIn);
                break;


            case  R.id.signUpClearId:


                fullName.setText("");
                phoneNumber.setText("");
                emailText.setText("");
                signPasswordText.setText("");

                break;


            case R.id.newSignUptId:

                userRegister();

                break;
        }

    }

    private void userRegister() {


        final String name=fullName.getText().toString();
        final String email=emailText.getText().toString().trim();
        final String phone=phoneNumber.getText().toString();
        final String password=signPasswordText.getText().toString().trim();

        if(email.isEmpty())
        {
            emailText.setError("Enter an email address");
            emailText.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailText.setError("Enter a valid email address");
            emailText.requestFocus();
            return;
        }


        if(password.isEmpty())
        {
            signPasswordText.setError("Enter a password");
            signPasswordText.requestFocus();
            return;
        }

        if(password.length()<6)
        {
            signPasswordText.setError("Minimum length of a password should be 6");
            signPasswordText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(getApplicationContext(),"Successfully SignUp",Toast.LENGTH_SHORT).show();
                    userID=mAuth.getCurrentUser().getUid();
                    DocumentReference documentReference=fStore.collection("users").document(userID);

                    Map<String,Object>user=new HashMap<>();
                    user.put("fName",name);
                    user.put("email",email);
                    user.put("phone",phone);

                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            Log.d("Success","onSuccess: User profile is created for "+userID);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("Failure","onFailure: "+e.toString());
                        }
                    });

                    Intent login=new Intent(getApplicationContext(),Login.class);
                    startActivity(login);

                } else {
                    // If sign in fails, display a message to the user.
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(),"User is already Registered",Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(getApplicationContext(),"Error : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}