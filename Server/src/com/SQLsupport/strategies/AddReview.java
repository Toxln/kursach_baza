package com.SQLsupport.strategies;

import com.SQLsupport.Updatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.SQLsupport.Constants.*;

public class AddReview implements Updatable {
    private int id_product,id_user;
    private String review_text;

    @Override
    public void getData(String data) {
        String[] dataFromClient = data.split("@@@");
        id_user=Integer.parseInt(dataFromClient[0]);
        id_product=Integer.parseInt(dataFromClient[1]);
        review_text=dataFromClient[2];
    }

    @Override
    public boolean executeUpdate(Connection conn) {
        int count=1;
        try{
            String sql1="INSERT INTO "+DB_NAME+"."+REVIEW_SCHEMA+" ("+
                    REVIEW_USER+","+ REVIEW_STUDENT +","+
                    REVIEW_TEXT+")"+" VALUE (?,?,?)";
            try(PreparedStatement prepStmt=conn.prepareStatement(sql1)){
                prepStmt.setInt(count++, id_user);
                prepStmt.setInt(count++, id_product);
                prepStmt.setString(count++, review_text);
                return prepStmt.executeUpdate() > 0;
            }
        }catch(SQLException e){

        }
        return false;
    }
}
