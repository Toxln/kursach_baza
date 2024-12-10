package com.SQLsupport.DBClass;

import java.io.Serializable;

public class InformationForPieChart implements Serializable {
    private Float  ave_grade;
    private Integer studentCount;

    public InformationForPieChart(Float ave_grade, int studentCount) {
        this.ave_grade = ave_grade;
        this.studentCount = studentCount;
    }

    public Float getAve_grade() {
        return ave_grade;
    }

    public Integer getStudentCount() {
        return studentCount;
    }
}
