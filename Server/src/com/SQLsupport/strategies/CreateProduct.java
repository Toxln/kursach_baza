package com.SQLsupport.strategies;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.SQLsupport.Updatable;

import static com.SQLsupport.Constants.*;

public class CreateProduct implements Updatable {
    private String first_name, last_name;
    private float ave_grade, coefficient;
    int scholarship, iduser, idstudent;

    @Override
    public void getData(String data) {
        String[] dataFromClient=data.split("@@@");
        int count=0;
        idstudent = Integer.parseInt(dataFromClient[count++]);
        iduser = Integer.parseInt(dataFromClient[count++]);
        first_name=dataFromClient[count++];
        last_name=dataFromClient[count++];
        ave_grade=Float.parseFloat(dataFromClient[count++]);
        scholarship=Integer.parseInt(dataFromClient[count++]);
        coefficient=Float.parseFloat( dataFromClient[count++]);
    }

    @Override
    public boolean executeUpdate(Connection conn) {
        int count=1;
        try{
            String sql1 = "INSERT INTO " + DB_NAME + "." + SCHOLARSHIP_SCHEMA + " (" +
                    SCHOLARSHIP_SCHOLARSHIP + ","+
                    SCHOLARSHIP_COEFFICIENT + ")"+
                    " VALUES (?,?)";
            try(PreparedStatement prepStmt=conn.prepareStatement(sql1)){
                prepStmt.setInt(count++, scholarship);
                prepStmt.setFloat(count++, coefficient);
                prepStmt.executeUpdate();
            }
            count = 1;
            String sql2="INSERT INTO "+DB_NAME+"."+STUDENT_SCHEMA+" ("+
                    STUDENT_FIRSTNAME+","+
                    STUDENT_LASTNAME+","+
                    STUDENT_AVEGRADE+","+
                    STUDENT_MARK_ID+","+
                    STUDENT_SCHOLARSHIP_ID+","+
                    STUDENT_USER_ID+")"+
                    " VALUES (?,?,?,?,?,?)";
            try(PreparedStatement prepStmt=conn.prepareStatement(sql2)){
                prepStmt.setString(count++, first_name);
                prepStmt.setString(count++, last_name);
                prepStmt.setFloat(count++, ave_grade);
                prepStmt.setInt(count++, idstudent);
                prepStmt.setInt(count++, idstudent);
                prepStmt.setInt(count++, idstudent);
                return prepStmt.executeUpdate() > 0;
            }
        }catch(SQLException e){
        }
        return false;
    }
}
