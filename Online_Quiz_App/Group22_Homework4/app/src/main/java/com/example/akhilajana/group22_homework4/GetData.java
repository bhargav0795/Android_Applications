package com.example.akhilajana.group22_homework4;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;


import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by akhilajana on 10/1/17.
 */

public class GetData extends AsyncTask<String,Integer,ArrayList<Questions>> {

    MainActivity mainActivity;

    public GetData(MainActivity mainActivity) {

        this.mainActivity = mainActivity;
    }

    @Override
    protected void onPreExecute() {


        mainActivity.loadTrivia.setVisibility(View.VISIBLE);
        mainActivity.triviaImgStatus.setText(R.string.loadTrivia);


    }

    @Override
    protected ArrayList<Questions> doInBackground(String... strings) {

        BufferedReader reader = null;
        //InputStream in;
        ArrayList<Questions> qList = new ArrayList<>();
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
           // in =
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder(); //linkedlist of strings
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line +"\n");
            }
            qList = parseJSON.listOfQuestions(sb.toString());
            Log.d("questions",qList.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader!=null)
                {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.d("demo", "DatadoInBackground: "+qList);
        return qList;
    }


    @Override
    protected void onPostExecute(ArrayList<Questions> obtainedQList) {

        Log.d("demo", "Get DataonPostExecute: "+obtainedQList.size()+obtainedQList);
        if(obtainedQList.size()>0)
        {
            mainActivity.questionsList.addAll(obtainedQList);
            mainActivity.btnStart.setEnabled(true);
            mainActivity.loadTrivia.setVisibility(View.GONE);
            mainActivity.triviaImg.setImageResource(R.drawable.trivia);
            mainActivity.triviaImgStatus.setText(R.string.readyTrivia);
        }

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mainActivity.loadTrivia.setProgress(values[0]);
    }
}
