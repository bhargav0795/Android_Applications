package com.example.akhilajana.inclass10_group22;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity implements View.OnClickListener {


    Button btnNewContact, btnLogout;
    TextView defaultText;
    ArrayList<Contacts> contactsList;
    ListView listView;
    FirebaseDatabase database;
    ContactsAdapter adapter;
    DatabaseReference myRef;

    FirebaseAuth auth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        user = FirebaseAuth.getInstance().getCurrentUser();

        btnNewContact = findViewById(R.id.buttonAddContact);
        btnLogout = findViewById(R.id.buttonLogOut);
        listView= (ListView) findViewById(R.id.ContactsListView);
        defaultText = (TextView) findViewById(R.id.defaultList) ;
        btnNewContact.setOnClickListener(this);
        btnLogout.setOnClickListener(this);

        database = FirebaseDatabase.getInstance();
        myRef=database.getReference();
        contactsList=new ArrayList<Contacts>();

        auth = FirebaseAuth.getInstance();

        adapter = new ContactsAdapter(this, R.layout.contacts_row_layout, contactsList);
        listView.setAdapter(adapter);


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //Delete contact
                myRef.child("Contacts")
                        .child("Users")
                        .child(auth.getCurrentUser().getUid())
                        .child(contactsList.get(position).getHashKey())
                        .removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        Toast.makeText(ContactsActivity.this, "Contact Deleted", Toast.LENGTH_SHORT).show();
                        Contacts contact=contactsList.get(position);
                        contactsList.remove(contact);
                        Log.d("List",contactsList.size()+""+contactsList.toString());
                        adapter.notifyDataSetChanged();
                        if(contactsList.size()==0){

                            defaultText.setText("No Contacts Added!");
                            defaultText.setVisibility(View.VISIBLE);
                        }
                    }
                });
                return true;
            }
        });


        DatabaseReference query=myRef.child("Contacts")
                .child("Users")
                .child(auth.getCurrentUser().getUid());

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Contacts contact = dataSnapshot.getValue(Contacts.class);
                Log.e("Get Data", contact.toString());
                contactsList.add(contact);
                if(contactsList.size()>0){
                    //noLabel.setVisibility(View.INVISIBLE);
                }
                Log.e("Count List " ,""+ contactsList.size());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.buttonAddContact:

                Intent intent = new Intent(this, CreateContactActivity.class);
                startActivity(intent);
                finish();

                break;

            case R.id.buttonLogOut:

                FirebaseAuth.getInstance().signOut();
                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
                finish();
                break;
        }

    }
}
