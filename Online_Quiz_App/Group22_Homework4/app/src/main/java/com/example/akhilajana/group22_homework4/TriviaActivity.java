package com.example.akhilajana.group22_homework4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TriviaActivity extends AppCompatActivity implements View.OnClickListener {

    TextView questionNo,remainingTime,question;
    TickTock countDownTimer;
    ImageView questionImg;
    RadioGroup choiceGroup;
    RadioButton choice;
    ProgressBar progressBar;
    Button btnQuit,btnNext;
    Bitmap qImg;
    int value;
    int marks =0;
    ArrayList<Questions> qList = new ArrayList<>();
    final static String MARKS_KEY = "marks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        questionNo = (TextView) findViewById(R.id.questionNo);
        remainingTime = (TextView) findViewById(R.id.timer);
        question = (TextView) findViewById(R.id.question);
        questionImg = (ImageView) findViewById(R.id.questionImg);
        choiceGroup = (RadioGroup) findViewById(R.id.choiceGroup);
        btnQuit = (Button) findViewById(R.id.btnQuit);
        btnNext = (Button) findViewById(R.id.btnNext);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        btnNext.setOnClickListener(this);
        btnQuit.setOnClickListener(this);
        choiceGroup.setOnClickListener(this);

        if(getIntent().getParcelableArrayListExtra(MainActivity.Question_KEY) != null)
        {
            qList = getIntent().getParcelableArrayListExtra(MainActivity.Question_KEY);
        }
        Log.d("demo","trivialist"+qList.toString());
        //Timer
        countDownTimer = new TickTock(120000,1000);
        countDownTimer.start();
        //SetQuestions
        setQuiz(qList,value);
    }

    private void setQuiz(ArrayList<Questions> qList, int value) {

        questionNo.setText(getResources().getText(R.string.questionNo)+""+(value+1));

        if(qList.get(value).getImage().equals("")){

            questionImg.setImageResource(R.drawable.no_image_found);
        }
        else{
            new GetImage(this).execute(qList.get(value).getImage());
            questionImg.setImageBitmap(qImg);

        }
        question.setText(qList.get(value).getText());
        createChoiceGroup(qList.get(value));

    }

    private void createChoiceGroup(Questions questions) {
            Log.d("choices",questions.toString());
        choiceGroup.clearCheck();
        choiceGroup.removeAllViews();
        choiceGroup.setOrientation(LinearLayout.VERTICAL);
        for (int m=0; m< questions.getChoices().length-1;m++)
        {
            choice = new RadioButton(this);
            choice.setId(m+1);
            choice.setText(questions.getChoices()[m]);
            choiceGroup.addView(choice);
        }
    }

    private int countMarks(int value)
    {
        if(qList.get(value).getAnswer() == choiceGroup.getCheckedRadioButtonId())
        {
            marks = marks+1;
        }

        return marks;
    }

    // CountDownTimer class
    public class TickTock extends CountDownTimer {

        public TickTock(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            Intent resultIntent = new Intent(TriviaActivity.this, StatsActivity.class);
            resultIntent.putParcelableArrayListExtra(MainActivity.Question_KEY, qList);
            resultIntent.putExtra(MARKS_KEY, marks); //marks
            startActivity(resultIntent);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            remainingTime.setText(getResources().getText(R.string.timeLeft) + "" + millisUntilFinished / 1000 + getResources().getText(R.string.seconds));

            if ((millisUntilFinished / 1000) < 30 && (millisUntilFinished / 1000) > 15) {
                remainingTime.setBackgroundColor(Color.parseColor("#f5ab35"));

            } else if ((millisUntilFinished / 1000) < 15) {
                remainingTime.setBackgroundColor(Color.parseColor("#ef4836"));

            }
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btnNext:

                marks = countMarks(value);
                if(value>=0 && value< qList.size()-1)
                {
                    value++;
                    setQuiz(qList,value);
                }
                else if(value == qList.size()-1)
                {
                    Intent resultIntent = new Intent(TriviaActivity.this,StatsActivity.class);
                    resultIntent.putParcelableArrayListExtra(MainActivity.Question_KEY,qList);
                    resultIntent.putExtra(MARKS_KEY,marks);
                    startActivity(resultIntent);
                    countDownTimer.cancel();

                }

                break;
            case R.id.btnQuit:

                Intent resultIntent = new Intent(TriviaActivity.this,MainActivity.class);
                //resultIntent.putParcelableArrayListExtra(MainActivity.Question_KEY,qList);
                //resultIntent.putExtra(MARKS_KEY,marks);
                startActivity(resultIntent);
                countDownTimer.cancel();
                finish();
                break;
        }

    }
}
