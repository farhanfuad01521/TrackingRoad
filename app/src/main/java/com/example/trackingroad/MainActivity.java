package com.example.trackingroad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText userEmailText,passwordText;
    Button loginButton,clearButton,forgetButton,signUpButton;

    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("SignIn");

        userEmailText=(EditText)findViewById(R.id.userETextId);
        passwordText=(EditText)findViewById(R.id.passwordTextId);

        loginButton=(Button)findViewById(R.id.loginButtonId);
        clearButton=(Button)findViewById(R.id.clearButtonId);
        forgetButton=(Button)findViewById(R.id.forgetPasswordId);
        signUpButton=(Button)findViewById(R.id.signUpId);


        mAuth = FirebaseAuth.getInstance();
        progressBar=(ProgressBar)findViewById(R.id.progressBarX);

        loginButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
        forgetButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.shareId)
        {
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");

            String subject="c programming app";
            String body="This app is very useful to learn c programming";

            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            intent.putExtra(Intent.EXTRA_SUBJECT,body);

            startActivity(Intent.createChooser(intent,"share with"));

        }

        if(item.getItemId()==R.id.feedbackId)
        {
            Intent intent=new Intent(getApplicationContext(),Feedback.class);
            startActivity(intent);
        }

        if(item.getItemId()==R.id.aboutUsId)
        {
            Intent aboutIntent=new Intent(getApplicationContext(),About.class);
            startActivity(aboutIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            //Login
            case R.id.loginButtonId:

                userLogin();
                break;


            //clear
            case R.id.clearButtonId:

                userEmailText.setText("");
                passwordText.setText("");

                break;



            //forgetPassword
            case R.id.forgetPasswordId:

                final EditText resetMail=new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog=new AlertDialog.Builder(v.getContext());

                passwordResetDialog.setTitle("Reset Password");
                passwordResetDialog.setMessage("Enter your email To Received Reset Link");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String mail=resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"Reset link sent to your email",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Error ! Reset link is not send"+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });
                passwordResetDialog.create().show();

                break;




            //signUp
            case  R.id.signUpId:

                Intent signUp=new Intent(MainActivity.this,CreateAccount.class);
                startActivity(signUp);

                break;

        }
    }

    private void userLogin() {

        String email=userEmailText.getText().toString().trim();
        String password=passwordText.getText().toString().trim();

        if(email.isEmpty())
        {
            userEmailText.setError("Enter an email address");
            userEmailText.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            userEmailText.setError("Enter a valid email address");
            userEmailText.requestFocus();
            return;
        }


        if(password.isEmpty())
        {
            passwordText.setError("Enter a password");
            passwordText.requestFocus();
            return;
        }

        if(password.length()<6)
        {
            passwordText.setError("Minimum length of a password should be 6");
            passwordText.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful())
                {
                    Intent login=new Intent(getApplicationContext(),Login.class);
                    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(login);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Login Unsuccessful",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}