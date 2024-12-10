package com.SQLsupport.strategies;

import com.SQLsupport.Requestable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.SQLsupport.DBClass.User;

import static com.SQLsupport.Constants.*;

public class SelectUser implements Requestable {

    private String login,password;

    @Override
    public void getData(String data) {
        String[] dataFromClient=data.split(" ");
        login=dataFromClient[0];
        password=dataFromClient[1];
    }

    public Vector<User> executeSelect(Connection conn) {
        int count=1;
        ResultSet res=null;
        Vector<User> user=null;

        try{
            String sql1="SELECT * FROM "+DB_NAME+"."+USER_SCHEMA+" WHERE "+USER_LOGIN +" = ? and "+USER_PASSWORD+" = ?";
            try(PreparedStatement prepStmt=conn.prepareStatement(sql1)){
                prepStmt.setString(count++, login);
                prepStmt.setString(count++, password);
                res = prepStmt.executeQuery();
                if(res.next()){
                    count=1;
                    int id = res.getInt(count++);
                    String login = res.getString(count++);
                    String password= res.getString(count++);
                    int role = res.getInt(count++);
                    String firstName= res.getString(count++);
                    String lastName= res.getString(count++);
                    String phone= res.getString(count++);
                    user = new Vector<>();
                    user.add(new User(id,login,password,role,firstName,lastName,phone)) ;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return user;
    }
}
