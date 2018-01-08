package com.example.akhilajana.inclass08_group22;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements SearchFragment.OnFragmentInteractionListener, DisplayFragment.OnFragmentInteractionListener{


    ArrayList<Recipe> jsonList;
    ArrayList<String> ingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jsonList = new ArrayList<>();
        ingList = new ArrayList<>();

        getFragmentManager().beginTransaction()
                .add(R.id.container, new SearchFragment(), "search")
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void addIngredients() {


    }

    public String convertListToString()
    {
        return "onion,garlic";
    }

    @Override
    public ArrayList<String> getIngList() {
        return ingList;
    }

    public void gotoNextActivity() {

        getFragmentManager().beginTransaction()
                .replace(R.id.container, new DisplayFragment(), "display")
                .addToBackStack(null)
                .commit();
    }


    @Override
    public ArrayList<Recipe> getResultList() {
        return jsonList;
    }

    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStack();
        }else{

            super.onBackPressed();}
    }

    public void gotopreviousActivity(){

        getFragmentManager().beginTransaction()
                .replace(R.id.container, new SearchFragment(), "first")
                .addToBackStack(null)
                .commit();
        jsonList.clear();

    }

    public void setResultList(ArrayList<Recipe> obtainedList) {

        if(obtainedList.size()>0) {
            jsonList.addAll(obtainedList);
        }
        else
        {
            Toast.makeText(this,"Recipe list is empty",Toast.LENGTH_SHORT).show();
        }

    }
}
