package com.example.akhilajana.inclass08_group22;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by akhilajana on 10/9/17.
 */

public class GetDataWithParams extends AsyncTask<RequestParams,Void,ArrayList<Recipe>> {

        Context context;
        MainActivity activity;

        public GetDataWithParams(Context context) {

            this.context = context;
            this.activity = (MainActivity) context;
        }

        @Override
        protected void onPreExecute() {

//            mainActivity.loadTrivia.setVisibility(View.VISIBLE);
//            mainActivity.triviaImgStatus.setText(R.string.loadTrivia);
        }

        @Override
        protected ArrayList<Recipe> doInBackground(RequestParams... params) {

            BufferedReader reader = null;
            //InputStream in;
            ArrayList<Recipe> musicList = new ArrayList<>();

            try {
                HttpURLConnection conn = params[0].setUpConnection();
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line=reader.readLine())!= null)
                {
                    sb.append(line+"\n");
                }
                musicList = parseJSON.listOfSongs(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (reader!=null)
                    {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return musicList;
        }


        @Override
        protected void onPostExecute(ArrayList<Recipe> obtainedList) {

            Log.d("demo", "Get DataonPostExecute: "+obtainedList.size()+obtainedList);
            if(obtainedList.size() > 0)
            {
                activity.setResultList(obtainedList);
                activity.gotoNextActivity();
            }
            else
            {
                Toast.makeText(context,"No Reciepe Found!",Toast.LENGTH_LONG).show();

            }

        }

    }



