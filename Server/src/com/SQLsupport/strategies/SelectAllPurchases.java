package com.SQLsupport.strategies;

import com.SQLsupport.DBClass.Purchase;
import com.SQLsupport.Requestable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static com.SQLsupport.Constants.*;
import static com.SQLsupport.Constants.STUDENT_FIRSTNAME;

public class SelectAllPurchases implements Requestable {
    private int user_id;

    @Override
    public void getData(String data) {
        user_id=Integer.parseInt(data);
    }

    public int getId(){
        return user_id;
    }

    public Vector<Purchase> executeSelect(Connection conn) {
        int count=1;
        ResultSet res=null;
        Vector<Purchase> purchases=null;

        try{
            String sql1="SELECT " +
                    SCHOLARSHIP_SCHEMA +"." + SCHOLARSHIP_ID +", "+
                    STUDENT_SCHEMA +"." + STUDENT_FIRSTNAME +", "+
                    STUDENT_SCHEMA +"." + STUDENT_LASTNAME +", "+
                    STUDENT_SCHEMA +"." + STUDENT_AVEGRADE +", "+
                    MARK_SCHEMA +"." + MARK_MATHS +
                    " FROM "+DB_NAME+"."+ SCHOLARSHIP_SCHEMA +
                    " INNER JOIN "+DB_NAME+"."+USER_SCHEMA+
                    " ON "+ SCHOLARSHIP_SCHEMA +"."+SCHOLARSHIP_SCHOLARSHIP+"="+ USER_SCHEMA+"."+USER_ID+
                    " INNER JOIN "+DB_NAME+"."+ STUDENT_SCHEMA +
                    " ON "+ SCHOLARSHIP_SCHEMA +"."+SCHOLARSHIP_SCHOLARSHIP+"="+ STUDENT_SCHEMA +"."+ STUDENT_ID +
                    " INNER JOIN "+DB_NAME+"."+ MARK_SCHEMA +
                    " ON "+ STUDENT_SCHEMA +"."+ STUDENT_SCHOLARSHIP_ID +"="+ MARK_SCHEMA +"."+ MARK_ID +
                    " WHERE "+ SCHOLARSHIP_SCHEMA +"."+SCHOLARSHIP_SCHOLARSHIP+"=? ORDER BY "+ SCHOLARSHIP_ID;
            try(PreparedStatement prepStmt=conn.prepareStatement(sql1)){
                prepStmt.setInt(1, user_id);
                res = prepStmt.executeQuery();
                purchases = new Vector<>();
                while(res.next()){
                    count=1;
                    int id_purchase=res.getInt(count++);
                    float scholarship = res.getFloat(count++);
                    float coefficient= res.getFloat(count++);

                    purchases.add(new Purchase(id_purchase, scholarship, coefficient));
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return purchases;
    }
}
