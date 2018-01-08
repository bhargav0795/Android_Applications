package com.example.akhilajana.inclass08_group22;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by akhilajana on 10/9/17.
 */

public class parseJSON {

    public static ArrayList<Recipe> listOfSongs(String movies) {

        int i =0;

        Recipe music1;
        ArrayList<Recipe> iTunesList = new ArrayList<>();

        try
        {
            JSONObject jsonObject = new JSONObject(movies);
            JSONArray resultArray = jsonObject.getJSONArray("results");

            while (i< resultArray.length())
            {
                JSONObject currentTrackObject = resultArray.getJSONObject(i);

                String title = currentTrackObject.getString("title");
                String href = currentTrackObject.getString("href");
                String ingredients = currentTrackObject.getString("ingredients");
                String thumbnail = currentTrackObject.getString("thumbnail");

                music1 = new Recipe(title,href,ingredients,thumbnail);
                iTunesList.add(music1);
                i++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return iTunesList;
    }

}
