package com.example.akhilajana.inclass10_group22;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class CreateContactActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    Contacts contacts;
    EditText eName,eEmail, ePhone;
    ImageView eImage;
    Button addContact;
    RadioGroup rg;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth auth;


    String department,imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        eImage = (ImageView) findViewById(R.id.buttonAddContactImage);
        eName= (EditText) findViewById(R.id.editAddContactName);
        eEmail= (EditText) findViewById(R.id.editAddContactEmail);
        ePhone= (EditText) findViewById(R.id.editAddContactPhone);
        rg= (RadioGroup) findViewById(R.id.radioContactDepartment);
        addContact = (Button) findViewById(R.id.buttonContactSubmit);

        eImage.setOnClickListener(this);
        addContact.setOnClickListener(this);
        rg.setOnCheckedChangeListener(this);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef=database.getReference();

        if(getIntent().getExtras() != null ) {
            imagePath = getIntent().getExtras().getString("imagePath");
            if (imagePath != null) {
                if(imagePath.equals("R.drawable.avatar_f_1"))
                {
                    eImage.setImageResource(R.drawable.avatar_f_1);

                }
                else if(imagePath.equals("R.drawable.avatar_f_2"))
                {
                    eImage.setImageResource(R.drawable.avatar_f_2);

                }
                else if(imagePath.equals("R.drawable.avatar_f_3"))
                {
                    eImage.setImageResource(R.drawable.avatar_f_3);

                }
                else if(imagePath.equals("R.drawable.avatar_m_1"))
                {
                    eImage.setImageResource(R.drawable.avatar_m_1);

                }
                else if(imagePath.equals("R.drawable.avatar_m_2"))
                {
                    eImage.setImageResource(R.drawable.avatar_m_2);

                }
                else if(imagePath.equals("R.drawable.avatar_m_3"))
                {
                    eImage.setImageResource(R.drawable.avatar_m_3);

                }
            }
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.buttonAddContactImage:

                Intent intent = new Intent(CreateContactActivity.this, SelectAvatarActivity.class);
                startActivityForResult(intent,0);

                break;

            case R.id.buttonContactSubmit:

                if(validate()) {

                    Contacts contactsObj = new Contacts(eName.getText().toString(),eEmail.getText().toString(), ePhone.getText().toString(),department, imagePath );

                    String key = myRef.child("Contacts").push().getKey();
                    contactsObj.setHashKey(key);

                    Map<String, Object> postValues = contactsObj.toMap();

                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put("/Contacts/Users/" + auth.getCurrentUser().getUid() + "/" + key, postValues);
                    myRef.updateChildren(childUpdates);

                    Intent in=new Intent(this,ContactsActivity.class);
                    startActivity(in);

                    Toast.makeText(this,"Contact Added!",Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }


    public boolean validate(){
        boolean flag=true;
        if(eName.getText().toString().equals("")){
            eName.setError("Please enter the field,can't be left blank!");
            flag=false;
        }
        else if(eEmail.getText().toString().equals("")){
            eEmail.setError("Please enter the field,can't be left blank!");
            flag=false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(eEmail.getText().toString()).matches()){
            eEmail.setError("Please enter the correct format!");
            flag=false;
        }

        else if(ePhone.getText().toString().equals("")){
            eEmail.setError("Please enter the field,can't be left blank!");
            flag=false;
        }
        else if(rg.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(this,"Please select a department",Toast.LENGTH_LONG).show();
            flag=false;
        }
        else if(imagePath == null){
            Toast.makeText(this,"Please select a image",Toast.LENGTH_LONG).show();
            flag = false;
        }
        return flag;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int position) {

                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radioSIS:
                        department = "SIS";
                        break;
                    case R.id.radioCS:
                        department = "CS";
                        break;
                    case R.id.radioBIO:
                        department = "BIO";
                        break;
                }
            }
        }

