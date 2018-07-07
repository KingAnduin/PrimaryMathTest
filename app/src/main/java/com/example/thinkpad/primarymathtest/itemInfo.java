package com.example.thinkpad.primarymathtest;

import java.io.Serializable;

/**
 * Created by Thinkpad on 2018/7/5.
 */

public class itemInfo implements Serializable{
    String question;
    String answer;
    String yourAnswer;

    public void setQuestion(String question){
        this.question = question;
    }
    public void setAnswer(String answer){
        this.answer = answer;
    }
    public void setYourAnswer(String yourAnswer){this.yourAnswer = yourAnswer;}

    public String getQuestion(){
        return question;
    }
    public String getAnswer(){
        return answer;
    }
    public String getYourAnswer() {return yourAnswer;}
    public itemInfo(String question,String answer,String yourAnswer){
        this.question = question;
        this.answer = answer;
        this.yourAnswer = yourAnswer;
    }
}
