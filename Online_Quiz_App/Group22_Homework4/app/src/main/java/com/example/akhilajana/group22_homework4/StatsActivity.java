package com.example.akhilajana.group22_homework4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity implements View.OnClickListener {

    Button qButton, tButton;
    ProgressBar p1;
    TextView statCounter;
    int marks =0;
    ArrayList<Questions> qList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        qButton = (Button) findViewById(R.id.buttonQuit);
        tButton = (Button) findViewById(R.id.buttonTry);
        p1 = (ProgressBar) findViewById(R.id.progress1);
        statCounter = (TextView) findViewById(R.id.percentView);

        qButton.setOnClickListener(this);
        tButton.setOnClickListener(this);

        if(getIntent().getParcelableArrayListExtra(MainActivity.Question_KEY) != null)
        {
            qList = getIntent().getParcelableArrayListExtra(MainActivity.Question_KEY);
            Log.d("list", String.valueOf(qList.size()));
        }

            marks = getIntent().getExtras().getInt(TriviaActivity.MARKS_KEY);
        Log.d("marks", String.valueOf(marks));

        float totalQuestions = qList.size();
        float totalMarks = marks;



        float stats = (totalMarks/totalQuestions)*100;
        Log.d("stats", String.valueOf(stats));
        p1.setMax(100);
        p1.setProgress((int) stats);
        statCounter.setText((stats + "%").toString());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonQuit: {
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
                break;
            }
            case R.id.buttonTry: {
               // public static String QUESTION_LIST = "QUESTIONLIST";
                Intent i = new Intent(this, TriviaActivity.class);
                i.putParcelableArrayListExtra(MainActivity.Question_KEY,qList);
                startActivity(i);
                break;
            }
        }
    }
}
