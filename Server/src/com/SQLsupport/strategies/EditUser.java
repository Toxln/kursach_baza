package com.SQLsupport.strategies;

import com.SQLsupport.Updatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.SQLsupport.Constants.*;

public class EditUser implements Updatable {
    private String login, password, firstName, lastName, phone;
    private int id;

    @Override
    public void getData(String data) {
        int count=0;
        String[] dataFromClient=data.split("@@@");
        id=Integer.parseInt(dataFromClient[count++]);
        login=dataFromClient[count++];
        password=dataFromClient[count++];
        firstName=dataFromClient[count++];
        lastName=dataFromClient[count++];
        phone=dataFromClient[count++];
    }

    @Override
    public boolean executeUpdate(Connection conn) {
        int count=1;
        String sql1="UPDATE "+DB_NAME+"."+USER_SCHEMA+
            " SET "+
                USER_LOGIN+"=?, "+
                USER_PASSWORD+"=?, "+
                USER_FIRST_NAME+"=?, "+
                USER_LAST_NAME+"=?, "+
                USER_PHONE+"=? "+
            " WHERE "+USER_ID+"=?;";
        try(PreparedStatement prepStmt=conn.prepareStatement(sql1)){
            prepStmt.setString(count++,login);
            prepStmt.setString(count++,password);
            prepStmt.setString(count++,firstName);
            prepStmt.setString(count++,lastName);
            prepStmt.setString(count++,phone);
            prepStmt.setInt(count++,id);
            return prepStmt.executeUpdate()>0;
    } catch (SQLException e){

        }
        return false;
    }
}
