package com.matthieuc.topquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private TextView mQuestion;
    private Button mAnswerBtn1;
    private Button mAnswerBtn2;
    private Button mAnswerBtn3;
    private Button mAnswerBtn4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuestion = (TextView) findViewById(R.id.activity_game_question_txt);
        mAnswerBtn1 = (Button) findViewById(R.id.activity_game_answerbtn1);
        mAnswerBtn2 = (Button) findViewById(R.id.activity_game_answerbtn2);
        mAnswerBtn3 = (Button) findViewById(R.id.activity_game_answerbtn3);
        mAnswerBtn4 = (Button) findViewById(R.id.activity_game_answerbtn4);

    }
}
