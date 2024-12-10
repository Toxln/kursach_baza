package com.SQLsupport.DBClass;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Product implements Serializable {
    private int idstudent;
    private String first_name;
    private String last_name;
    private float ave_grade;
    private int idmark;
    private int idscholarship;
    private float iduser;

    public Product(int idstudent, String first_name, String last_name, float ave_grade, int idmark, int idscholarship, float iduser) {
        this.idstudent = idstudent;
        this.first_name = first_name;
        this.last_name = last_name;
        this.ave_grade = ave_grade;
        this.idmark = idmark;
        this.idscholarship = idscholarship;
        this.iduser = iduser;
    }

    public Product(int idstudent, String first_name, float ave_grade, int idmark, int idscholarship, float iduser) {
        this.idstudent = idstudent;
        this.first_name = first_name;
        this.ave_grade = ave_grade;
        this.idmark = idmark;
        this.idscholarship = idscholarship;
        this.iduser = iduser;
    }

    public int getIdstudent() {
        return idstudent;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public float getAve_grade() {
        return ave_grade;
    }

    public float getIduser() {
        return iduser;
    }

    public int getIdmark() {
        return idmark;
    }

    public int getIdscholarship() {
        return idscholarship;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setAve_grade(float ave_grade) {
        this.ave_grade = ave_grade;
    }

    public void setIdmark(int idmark) {
        this.idmark = idmark;
    }

    public void setIdscholarship(int idscholarship) {
        this.idscholarship = idscholarship;
    }

    public void setIdstudent(int idstudent) {
        this.idstudent = idstudent;
    }

    public void setIduser(float iduser) {
        this.iduser = iduser;
    }

    public void print(FileWriter writer){
        try{
            writer.write("id: "+idstudent+"\nfirst_name: "+first_name+"\nlast_name: " + last_name+
                    "\naverage grade: " + ave_grade + "\ncoef: " + idmark + "\nscholarship: " + idscholarship + "\n");
        }catch (IOException e){
            System.out.printf("\ncannot write purchase on file\n");
            e.printStackTrace();
        }
    }
}
