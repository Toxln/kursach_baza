package com.SQLsupport.DBClass;

import java.io.Serializable;

public class Review implements Serializable {
    private int idreview;
    private String nameUser;
    private int idstudent;
    private String review_text;
    private int iduser;

    public Review(String nameUser, String review_text) {
        this.nameUser = nameUser;
        this.review_text = review_text;
    }

    public Review(int idstudent, String review_text, int iduser) {
        this.idstudent = idstudent;
        this.review_text = review_text;
        this.iduser = iduser;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdreview() {
        return idreview;
    }

    public String getNameUser() {
        return nameUser;
    }

    public int getIdstudent() {
        return idstudent;
    }

    public String getReview_text() {
        return review_text;
    }
}
