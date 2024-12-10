package com.SQLsupport.strategies;

import com.SQLsupport.Updatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.SQLsupport.Constants.*;

public class DeleteOneRebate implements Updatable {
    int id_rebate;

    @Override
    public void getData(String data) {
        id_rebate=Integer.parseInt(data);
    }

    @Override
    public boolean executeUpdate(Connection conn) {
        int count=1;
        try{
            String sql1="DELETE FROM "+DB_NAME+"."+ COEFFICIENT_SCHEMA +
                    " WHERE "+ COEFFICIENT_ID +" =?";
            try(PreparedStatement prepStmt=conn.prepareStatement(sql1)){
                prepStmt.setInt(count++, id_rebate);
                return prepStmt.executeUpdate() > 0;
            }
        }catch(SQLException e){

        }
        return false;
    }
}
