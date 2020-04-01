package com.codellion.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Button goBtn, button0, button1, button2, button3, playAgainBtn;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAns;
    TextView sumTextView, resultText, scoreText, timerText;
    int score = 0;
    int numberOfQuetions = 0;
    CountDownTimer timer;
    ConstraintLayout gameLayout, goButtonLayout;

    public void go(View view){
        goBtn.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    public void chooseAnswer(View view){
        Log.i(TAG, "chooseAnswer: Tag: " + view.getTag().toString());
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAns))){
            resultText.setText(getText(R.string.correct));
            resultText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
            score++;
            randomQuetionGen();
        }
        else{
            resultText.setText(getText(R.string.wrong));
            resultText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
            randomQuetionGen();
        }
        numberOfQuetions++;
        scoreText.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuetions));
    }

    public void randomQuetionGen(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        locationOfCorrectAns = random.nextInt(4);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        answers.clear();

        for (int i = 0; i < 4; i++){

            if(i == locationOfCorrectAns){
                answers.add(a + b);
            }
            else{
                int wrongAns = random.nextInt(41);
                while(wrongAns ==  a+b){
                    wrongAns = random.nextInt(41);
                }
                answers.add(wrongAns);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void setTimer(){
        timer = new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished > 9100 ){
                    timerText.setText(String.valueOf(millisUntilFinished/1000) + "s");
                }
                else{
                    timerText.setText(String.valueOf("0"+ millisUntilFinished/1000) + "s");
                }
            }

            @Override
            public void onFinish() {
                resultText.setText(R.string.done);
                resultText.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.tab_indicator_text));
                playAgainBtn.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();
    }

    public void playAgain(View view){
        setTimer();
        randomQuetionGen();
        playAgainBtn.setVisibility(View.INVISIBLE);
        scoreText.setText(getText(R.string.nullscore));
        resultText.setText("");
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goBtn = (Button) findViewById(R.id.goButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        resultText = (TextView) findViewById(R.id.resultTextView);
        scoreText = (TextView) findViewById(R.id.scoreTextView);
        timerText = (TextView) findViewById(R.id.timerTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        playAgainBtn = (Button) findViewById(R.id.playAgainButton);
        goButtonLayout = (ConstraintLayout) findViewById(R.id.goButtonCons);
        gameLayout = (ConstraintLayout) findViewById(R.id.gameLayout);

        goButtonLayout.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

        goBtn.setVisibility(View.VISIBLE);


    }
}
