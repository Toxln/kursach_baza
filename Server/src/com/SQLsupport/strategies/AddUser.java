package com.SQLsupport.strategies;

import com.SQLsupport.Updatable;

import java.sql.*;

import static com.SQLsupport.Constants.*;


public class AddUser implements Updatable {

    private String login, password, firstName, lastName, phone;
    int role;

    public AddUser(){}


    @Override
    public void getData(String data){
        String[] transformedData = data.split(" ");
        login=transformedData[0];
        password=transformedData[1];
        role=Integer.parseInt(transformedData[2]);
        firstName=transformedData[3];
        lastName=transformedData[4];
        phone=transformedData[5];
    }

    @Override
    public boolean executeUpdate(Connection conn) {
        int count=1;
        try{
            String sql1="INSERT INTO "+DB_NAME+"."+USER_SCHEMA+" ("+
                    USER_LOGIN+","+ USER_PASSWORD+","+
                    USER_ROLE+","+USER_FIRST_NAME+","+ USER_LAST_NAME+","+USER_PHONE+")"+" VALUES (?,?,?,?,?,?)";
            try(PreparedStatement prepStmt=conn.prepareStatement(sql1)){
                prepStmt.setString(count++, login);
                prepStmt.setString(count++, password);
                prepStmt.setInt(count++, role);
                prepStmt.setString(count++, firstName);
                prepStmt.setString(count++, lastName);
                prepStmt.setString(count++, phone);
                return prepStmt.executeUpdate() > 0;
            }
        }catch(SQLException e){

        }
        return false;
    }
}

