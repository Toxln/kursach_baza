package com.SQLsupport.DBClass;

import java.io.Serializable;

public class Rebate implements Serializable {
    private int idcoefficient, iduser, idmark;
    private float coefficient;

    public Rebate(int idcoefficient, float coefficient, int iduser, int idmark) {
        this.idcoefficient = idcoefficient;
        this.coefficient = coefficient;
        this.iduser = iduser;
        this.idmark = idmark;
    }

    public int getIdcoefficient() {
        return idcoefficient;
    }

    public int getIdmark() {
        return idmark;
    }

    public int getIduser() {
        return iduser;
    }

    public float getCoefficient() {
        return coefficient;
    }
}
