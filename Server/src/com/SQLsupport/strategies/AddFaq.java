package com.SQLsupport.strategies;

import com.SQLsupport.DBClass.Manufacturer;
import com.SQLsupport.Updatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.SQLsupport.Constants.*;

public class AddFaq implements Updatable {
    private int mark;
    private String questoin, answer;
    @Override
    public void getData(String data) {
        String[] dataFromClient=data.split("@@@");
        questoin = dataFromClient[0];
        answer = dataFromClient[1];
    }

    @Override
    public boolean executeUpdate(Connection conn) {
        int count=1;
        ResultSet res=null;
        Manufacturer manufacturer=null;

        try{
            String sql1="INSERT INTO " + DB_NAME + "." + FAQ_SCHEMA + "("+
                    FAQ_QUESTION + ","+
                    FAQ_ANSWER + ","+
                    FAQ_USER_ID + ")"+
                    "VALUES (?,?,?)";
            try(PreparedStatement prepStmt=conn.prepareStatement(sql1)){
                prepStmt.setString(1, questoin);
                prepStmt.setString(2, answer);
                prepStmt.setInt(3, 1);
                return prepStmt.executeUpdate() > 0;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
