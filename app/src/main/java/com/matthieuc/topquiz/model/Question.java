package com.matthieuc.topquiz.model;

import java.util.List;

/**
 * Created by Matthieu C - Openclassrooms on 06/12/17.
 */

public class Question {
    //La question
    private String mQuestion;
    //Liste de réponse possible
    private List<String> mChoiceList;
    //Index identifiant la réponse
    private int mAnswerIndex;

    //Constructeur de la question
    public Question (String question, List<String> choiceList, int answerIndex){
        this.setQuestion(question);
        this.setChoiceList(choiceList);
        this.setAnswerIndex(answerIndex);
    }

    //Getter et Setter de chaque variable
    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public List<String> getChoiceList() {
        return mChoiceList;
    }

    public void setChoiceList(List<String> choiceList) {
        mChoiceList = choiceList;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        mAnswerIndex = answerIndex;
    }

    @Override
    public String toString() {
        return "Question{" +
                "mQuestion='" + mQuestion + '\'' +
                ", mChoiceList=" + mChoiceList +
                ", mAnswerIndex=" + mAnswerIndex +
                '}';
    }

}
