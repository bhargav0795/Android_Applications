package com.example.akhilajana.inclass10_group22;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    FirebaseDatabase database;
    DatabaseReference myRef;
    User user;
    Button login,create_account;
    EditText inputEmail, inputPassword;
    FirebaseAuth auth;
    String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        database = FirebaseDatabase.getInstance();

        inputEmail = (EditText) findViewById(R.id.editEmail);
        inputPassword = (EditText) findViewById(R.id.editPassword);

        login= (Button) findViewById(R.id.buttonLogin);
        create_account= (Button) findViewById(R.id.buttonSignUp);

        login.setOnClickListener(this);
        create_account.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();


        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, ContactsActivity.class));
            finish();
        }

    }


    public boolean validateForLogin(){
        if(email.equalsIgnoreCase("")){inputEmail.setError("Please enter this field");return false;}
        else if(password.equalsIgnoreCase("")){inputPassword.setError("Please enter this field");return false;}
        return true;
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonLogin:
                email=inputEmail.getText().toString().trim();
                password=inputPassword.getText().toString().trim();
                if(validateForLogin()){
                    auth.signInWithEmailAndPassword(inputEmail.getText().toString(),inputPassword.getText().toString())
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.

                                    if (!task.isSuccessful()) {
                                        // there was an error
                                        if (inputPassword.length() < 6) {
                                            inputPassword.setError("Password must be six length in minimum");
                                        } else {
                                            Toast.makeText(MainActivity.this, "Authentication failed, check your email and password or sign up", Toast.LENGTH_LONG).show();
                                        }
                                    } else {

                                        Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                }
                break;
            case R.id.buttonSignUp:
                Intent intent=new Intent(MainActivity.this,Activity_SignIn.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
