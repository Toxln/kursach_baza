package com.SQLsupport.DBClass;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Purchase implements Serializable {
    private int idscholarship;
    private float scholarship, coefficient;

    public Purchase(int idscholarship, float scholarship, float coefficient) {
        this.idscholarship = idscholarship;
        this.scholarship = scholarship;
        this.coefficient = coefficient;
    }

    public void print(FileWriter writer){
        try{
            writer.write("id: "+idscholarship+"\nscholarship: "+scholarship+"\ncoefficient: " + coefficient);
        }catch (IOException e){
            System.out.printf("\ncannot write purchase on file\n");
            e.printStackTrace();
        }
    }

    public int getIdscholarship() {
        return idscholarship;
    }

    public float getScholarship() {
        return scholarship;
    }

    public float getCoefficient() {
        return coefficient;
    }
}
