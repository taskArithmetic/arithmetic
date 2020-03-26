package com.smart.entity;

import java.io.Serializable;

/**
 * Question 实体类：包含问题本身和它对于的答案
 */
public class Question implements Serializable {
    //
    private String suffixQuestion;
    private String infixQuestion;
    private String answer;

    public String getSuffixQuestion() {
        return suffixQuestion;
    }

    public void setSuffixQuestion(String suffixQuestion) {
        this.suffixQuestion = suffixQuestion;
    }

    public String getInfixQuestion() {
        return infixQuestion;
    }

    public void setInfixQuestion(String infixQuestion) {
        this.infixQuestion = infixQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "suffixQuestion= " + suffixQuestion + ' ' +
                ", infixQuestion= " + infixQuestion + ' ' +
                ", answer= " + answer + ' ' +
                '}';
    }
}
