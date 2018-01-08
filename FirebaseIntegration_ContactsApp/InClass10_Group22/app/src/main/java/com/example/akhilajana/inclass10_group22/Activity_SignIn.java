package com.example.akhilajana.inclass10_group22;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activity_SignIn extends AppCompatActivity implements View.OnClickListener {

    EditText firstName, lastName, sEmail, sPassword, sPassword1;
    Button signup, cancel;

    FirebaseAuth auth;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__sign_in);

        auth = FirebaseAuth.getInstance();

        firstName = (EditText) findViewById(R.id.editAddFirstName);
        lastName = (EditText) findViewById(R.id.editAddLastName);
        sEmail = (EditText) findViewById(R.id.editAddEMail);
        sPassword = (EditText) findViewById(R.id.editAddPassword);
        sPassword1 = (EditText) findViewById(R.id.editAddConfirmPassword);
        signup = (Button) findViewById(R.id.buttonAddSignUp);
        cancel = (Button) findViewById(R.id.buttonAddCancel);

        signup.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    public boolean checkField(TextView field) {
        String fieldData = field.getText().toString();
        if (fieldData.equalsIgnoreCase("") && fieldData != null) {
            field.setError("Please fill this field");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonAddSignUp:

                if (checkField(firstName) && checkField(lastName) && validate(sEmail) && checkField(sPassword) && checkField(sPassword1)) {

                    if( sPassword1.getText().toString().equals(sPassword.getText().toString())) {
                        
                        auth.createUserWithEmailAndPassword(sEmail.getText().toString().trim(), sPassword.getText().toString().trim()).addOnCompleteListener(Activity_SignIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d("SignUp", "createUserWithEmail:onComplete: " + task.isSuccessful());
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.

                                if (!task.isSuccessful()) {
                                    Toast.makeText(Activity_SignIn.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Activity_SignIn.this,"User created!",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Activity_SignIn.this, ContactsActivity.class));
                                    finish();
                                }
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(this,"Passwords do not match!",Toast.LENGTH_LONG).show();
                        sPassword.setText("");
                        sPassword1.setText("");
                        sPassword.requestFocus();
                        
                    }
                }
                break;
            case R.id.buttonAddCancel:

                Intent intent = new Intent(Activity_SignIn.this, MainActivity.class);
                startActivity(intent);
                break;

        }
    }

    private boolean validate(EditText sEmail) {

        if(!Patterns.EMAIL_ADDRESS.matcher(sEmail.getText().toString()).matches()){
            sEmail.setError("Please enter the correct format!");
            flag=false;
        }
        else if(sEmail.getText().toString().equals("")){
            sEmail.setError("Please enter the field,can't be left blank!");
            flag=false;
        }
        return true;
    }
}
