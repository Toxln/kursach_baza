package com.SQLsupport.strategies;

import com.SQLsupport.DBClass.Manufacturer;
import com.SQLsupport.Updatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.SQLsupport.Constants.*;

public class AddMark implements Updatable {
    private int mark;
    private String nameOfManufacturer;
    @Override
    public void getData(String data) {
        String[] dataFromClient=data.split("@@@");
        mark=Integer.parseInt(dataFromClient[1]);
        nameOfManufacturer=dataFromClient[0];
    }

    @Override
    public boolean executeUpdate(Connection conn) {
        int count=1;
        ResultSet res=null;
        Manufacturer manufacturer=null;

        try{
            String sql1="SELECT * FROM "+DB_NAME+"."+ STUDENT_SCHEMA
                    +" WHERE "+ STUDENT_AVEGRADE +" = ?";
            try(PreparedStatement prepStmt=conn.prepareStatement(sql1)){
                prepStmt.setString(1,nameOfManufacturer);
                res = prepStmt.executeQuery();
                if(res.next()){
                    int id = res.getInt(count++);
                    Integer maths = res.getInt(count++);
                    Integer programming= res.getInt(count++);
                    Integer unity= res.getInt(count++);
                    Integer course_work= res.getInt(count++);
                    int number_of_ratings= res.getInt(count++);
                    manufacturer= new Manufacturer(id,maths,programming,unity,course_work);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
