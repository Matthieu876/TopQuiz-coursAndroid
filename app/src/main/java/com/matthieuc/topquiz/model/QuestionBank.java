package com.matthieuc.topquiz.model;

import java.util.*;

/**
 * Created by Matthieu C - Openclassrooms on 06/12/17.
 */

public class QuestionBank {
    //Liste des questions
    private List<Question> mQuestionList;
    //Index identifiant la question suivante
    private int mNextQuestionIndex;

    //Constructeur
    public QuestionBank(List<Question> questionList){

        mQuestionList = questionList;

        //Mélange des questions
        Collections.shuffle(mQuestionList);

        mNextQuestionIndex = 0;
    }

    //Méthode getQuestion
    public Question getQuestion(){

        if(mNextQuestionIndex == mQuestionList.size()){
            mNextQuestionIndex = 0;
        }

        return mQuestionList.get(mNextQuestionIndex++);

    }

}
