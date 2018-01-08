package com.example.akhilajana.group22_homework4;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by akhilajana on 10/1/17.
 */

public class parseJSON {

    static ArrayList<Questions> listOfQuestions(String question)
    {
        int i =0, count =0;
        String [] listOfChoices;

        Questions questions;
        ArrayList<Questions> qList = new ArrayList<>();

        try
        {
            //create a JSONObject with the received response string:
            JSONObject jsonObject = new JSONObject(question);
            //Get the main object from the created json object by using getJSONObject() method:
            JSONArray questionsArray = jsonObject.getJSONArray("questions");

            while (i< questionsArray.length())
            {
                JSONObject currentJsonObject = questionsArray.getJSONObject(i);
                //get strings by using getString() method.
                int qNo = currentJsonObject.getInt("id");
                String qText = currentJsonObject.getString("text");
                String qImg = "";
                if(currentJsonObject.has("image")) {

                    qImg = currentJsonObject.getString("image");
                }
                JSONArray choicesArray = currentJsonObject.getJSONObject("choices").getJSONArray("choice");  //get this JSON array by using getJSONArray() method:
                count = choicesArray.length();
                listOfChoices = new String[count+1];
                for(int j=0; j<choicesArray.length(); j++)
                {
                    listOfChoices[j] = choicesArray.getString(j);
                }
                int answer = currentJsonObject.getJSONObject("choices").getInt("answer");
                questions = new Questions(qNo,answer,listOfChoices,qText,qImg);
                qList.add(questions);
                i++;
            }
            Log.d("demo", "listOfQuestions: "+qList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return qList;
    }


}
