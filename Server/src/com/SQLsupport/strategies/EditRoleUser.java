package com.SQLsupport.strategies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.SQLsupport.Updatable;
import static com.SQLsupport.Constants.*;

public class EditRoleUser implements Updatable {
    private int id, role;

    @Override
    public void getData(String data) {
        String[] dataFromClient=data.split(" ");
        id = Integer.parseInt(dataFromClient[0]);
        role = Integer.parseInt(dataFromClient[1]);
    }

    @Override
    public boolean executeUpdate(Connection conn) {
        String sql1="UPDATE "+DB_NAME+"."+USER_SCHEMA+
                " SET "+ USER_ROLE+"=? WHERE "+USER_ID+"=?;";
        try(PreparedStatement prepStmt=conn.prepareStatement(sql1)){
            prepStmt.setInt(1,role);
            prepStmt.setInt(2,id);
            return prepStmt.executeUpdate()>0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
