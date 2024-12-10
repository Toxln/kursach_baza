package com.SQLsupport.DBClass;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Manufacturer implements Serializable {
    private int idmark;
    private String first_name, last_name;
    private int maths, programming, unity, course_work;

    public Manufacturer(int idmark, int maths, int programming, int unity, int course_work) {
        this.idmark = idmark;
        this.maths = maths;
        this.programming = programming;
        this.unity = unity;
        this.course_work = course_work;
    }

    public Manufacturer(int idmark, String first_name, String last_name, int maths, int programming, int unity, int course_work) {
        this.idmark = idmark;
        this.first_name = first_name;
        this.last_name = last_name;
        this.maths = maths;
        this.programming = programming;
        this.unity = unity;
        this.course_work = course_work;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setIdmark(int idmark) {
        this.idmark = idmark;
    }

    public void setMaths(Integer maths) {
        this.maths = maths;
    }

    public void setProgramming(Integer programming) {
        this.programming = programming;
    }

    public void setUnity(Integer unity) {
        this.unity = unity;
    }

    public void setCourse_work(Integer course_work) {
        this.course_work = course_work;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getIdmark() {
        return idmark;
    }

    public Integer getMaths() {
        return maths;
    }

    public Integer getProgramming() {
        return programming;
    }

    public Integer getUnity() {
        return unity;
    }

    public Integer getCourse_work() {
        return course_work;
    }

    public void print(FileWriter writer){
        try{
            writer.write("id: "+idmark+"\nfirst_name: "+first_name+"\nlast_name: " + last_name+
                    "\nmaths: " + maths + "\nprogramming: " + programming + "\nunity: " + unity + "\ncourse_work: "+
                    course_work + "\n");
        }catch (IOException e){
            System.out.printf("\ncannot write purchase on file\n");
            e.printStackTrace();
        }
    }
}
