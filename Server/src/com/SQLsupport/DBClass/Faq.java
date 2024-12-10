package com.SQLsupport.DBClass;

import java.io.Serializable;

public class Faq implements Serializable {
    private int id, iduser;
    private String question, answer;

    public Faq(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.id = 0;
        this.iduser = 0;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
