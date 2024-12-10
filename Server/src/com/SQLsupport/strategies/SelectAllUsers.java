package com.SQLsupport.strategies;

import com.SQLsupport.DBClass.User;
import com.SQLsupport.Requestable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static com.SQLsupport.Constants.*;

public class SelectAllUsers implements Requestable {

    int excludeRole;
    @Override
    public void getData(String data) {
        excludeRole=Integer.parseInt(data);
    }

    public Vector<User> executeSelect(Connection conn) {
        int count=1;
        ResultSet res=null;
        Vector<User> users=null;

        try{
            String sql1="SELECT * FROM "+DB_NAME+"."+USER_SCHEMA+" WHERE "+USER_ROLE +" != ?";
            try(PreparedStatement prepStmt=conn.prepareStatement(sql1)){
                prepStmt.setInt(1,excludeRole);
                res = prepStmt.executeQuery();
                users = new Vector<>();
                while (res.next()){
                    count=1;
                    int id = res.getInt(count++);
                    String login = res.getString(count++);
                    String password= res.getString(count++);
                    int role = res.getInt(count++);
                    String firstName= res.getString(count++);
                    String lastName= res.getString(count++);
                    String phone= res.getString(count++);
                    users.add(new User(id,login,password,role,firstName,lastName,phone)) ;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return users;
    }
}
