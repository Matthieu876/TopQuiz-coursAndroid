package com.matthieuc.topquiz.controller;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.matthieuc.topquiz.R;
import com.matthieuc.topquiz.model.Question;
import com.matthieuc.topquiz.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mQuestion;
    private Button mAnswerBtn1;
    private Button mAnswerBtn2;
    private Button mAnswerBtn3;
    private Button mAnswerBtn4;

    private int mScore;
    private int mNumberOfQuestions;

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuestionBank = this.generateQuestions();

        mScore = 0;
        mNumberOfQuestions = 5;

        mQuestion = (TextView) findViewById(R.id.activity_game_question_txt);
        mAnswerBtn1 = (Button) findViewById(R.id.activity_game_answerbtn1);
        mAnswerBtn2 = (Button) findViewById(R.id.activity_game_answerbtn2);
        mAnswerBtn3 = (Button) findViewById(R.id.activity_game_answerbtn3);
        mAnswerBtn4 = (Button) findViewById(R.id.activity_game_answerbtn4);

        //Valeur donnée aux boutons de réponse
        mAnswerBtn1.setTag(0);
        mAnswerBtn2.setTag(1);
        mAnswerBtn3.setTag(2);
        mAnswerBtn4.setTag(3);

        //Ecouteur sur les boutons de réponse
        mAnswerBtn1.setOnClickListener(this);
        mAnswerBtn2.setOnClickListener(this);
        mAnswerBtn3.setOnClickListener(this);
        mAnswerBtn4.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);
    }

        private void displayQuestion(final Question question) {
            mQuestion.setText(question.getQuestion());
            mAnswerBtn1.setText(question.getChoiceList().get(0));
            mAnswerBtn2.setText(question.getChoiceList().get(1));
            mAnswerBtn3.setText(question.getChoiceList().get(2));
            mAnswerBtn4.setText(question.getChoiceList().get(3));
        }

        private QuestionBank generateQuestions() {
        Question question1 = new Question("Qui est l'actuel Président de la France?",
        Arrays.asList("François Hollande", "Emmanuel Macron", "Jacques Chirac", "François Mitterand"), 1);
        Question question2 = new Question("Combien de pays comporte l'Union Européenne?",
        Arrays.asList("15", "24", "28", "32"), 2);
        Question question3 = new Question("Qui est le créateur du Système ANDROID?",
        Arrays.asList("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"), 0);
        Question question4 = new Question("De quelle année date le Code Civil?",
        Arrays.asList("1904", "1867", "1967", "1804"), 3);
        Question question5 = new Question("Quel mathématicien a decrypté Enigma pendant la seconde guerre mondiale?",
        Arrays.asList("Alan Turing", "Albert Einstein", "John Von Neumann", "George Boole"), 0);
        return new QuestionBank(Arrays.asList(
                question1,
                question2,
                question3,
                question4,
                question5));
        }

    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();
            if (responseIndex == mCurrentQuestion.getAnswerIndex()) {
            //Bonne réponse
                Toast.makeText(this, "Bonne réponse", Toast.LENGTH_SHORT).show();
                mScore++;
            } else {
            // Mauvaise réponse
                Toast.makeText(this, "Mauvaise réponse!", Toast.LENGTH_SHORT).show();
            }

            if(--mNumberOfQuestions == 0){
                //End the game
                endGame();
            }else{
                mCurrentQuestion = mQuestionBank.getQuestion();
                displayQuestion(mCurrentQuestion);
            }

    }

    private void endGame(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Bien joué!")
                .setMessage("Votre score est de " + mScore + " points")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Fin de l'activité
                        finish();
                    }
                })
                .create()
                .show();
    }

}
