package com.example.akhilajana.group22_homework4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by akhilajana on 10/1/17.
 */

public class GetImage extends AsyncTask<String,Void,Bitmap> {

    TriviaActivity triviaActivity;
    private Bitmap image;
    InputStream in;

    public GetImage(TriviaActivity triviaActivity) {

        this.triviaActivity = triviaActivity;
    }

    @Override
    protected void onPreExecute() {

        triviaActivity.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {

//retrive image in JSON Array

        try {
            URL url = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            in = conn.getInputStream();
            image = BitmapFactory.decodeStream(in);

            return image;

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(in!=null)
            {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

        triviaActivity.progressBar.setVisibility(View.GONE);
        triviaActivity.questionImg.setImageBitmap(bitmap);

    }
}
