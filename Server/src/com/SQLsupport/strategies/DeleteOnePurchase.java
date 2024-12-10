package com.SQLsupport.strategies;

import com.SQLsupport.Updatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.SQLsupport.Constants.*;

public class DeleteOnePurchase implements Updatable {

    private int id_purchase;

    @Override
    public void getData(String data) {
        id_purchase=Integer.parseInt(data);
    }

    @Override
    public boolean executeUpdate(Connection conn) {
        int count=1;
        try{
            String sql1="DELETE FROM "+DB_NAME+"."+ SCHOLARSHIP_SCHEMA +
                    " WHERE "+ SCHOLARSHIP_ID +" =?";
            try(PreparedStatement prepStmt=conn.prepareStatement(sql1)){
                prepStmt.setInt(count++, id_purchase);
                return prepStmt.executeUpdate() > 0;
            }
        }catch(SQLException e){

        }
        return false;
    }
}
