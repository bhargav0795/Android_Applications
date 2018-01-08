package com.example.bhargavnallani.coursemanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by Bhargav Nallani on 11/4/2017.
 */

public class actionMenu extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_home:

                getFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, new Fragment_courseList(), "Course_List")
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.action_instructorFrame:
                getFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, new Fragment_courseList(), "Course_List")
                        .addToBackStack(null)
                        .commit();
                break;


            case R.id.action_add_instructor:

                getFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, new Fragment_addInstructor(), "Course_List")
                        .addToBackStack(null)
                        .commit();
                break;


            case R.id.action_logout:

                getFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, new Fragment_login(), "Course_List")
                        .addToBackStack(null)
                        .commit();
                break;


            case R.id.action_exit:
                finish();


        }
        return true;
    }

}
