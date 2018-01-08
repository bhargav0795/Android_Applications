package com.example.akhilajana.group22_homework4;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView triviaImg;
    Button btnStart,btnExit;
    TextView triviaImgStatus;
    ProgressBar loadTrivia;
    ArrayList<Questions> questionsList = new ArrayList<>();
    final static String Question_KEY = "QuestionsList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadTrivia = (ProgressBar) findViewById(R.id.progress1);

        loadTrivia.setVisibility(View.VISIBLE);

        triviaImg = (ImageView) findViewById(R.id.triviaImg);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnExit = (Button) findViewById(R.id.btnExit);
        triviaImgStatus = (TextView) findViewById(R.id.triviaImgStatus);

        btnStart.setOnClickListener(this);
        btnExit.setOnClickListener(this);



        new GetData(MainActivity.this).execute("http://dev.theappsdr.com/apis/trivia_json/index.php");

        loadTrivia.setVisibility(View.GONE);

        //Log.d("demo", "MainonCreate: "+questionsList);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btnStart:
                Log.d("demo", "btnStartonClick: "+questionsList);
                Intent startIntent = new Intent(MainActivity.this,TriviaActivity.class);
                startIntent.putParcelableArrayListExtra(Question_KEY,questionsList);
                startActivity(startIntent);

                break;
            case R.id.btnExit:
                finish();
                break;
        }
    }
}