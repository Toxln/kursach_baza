package com.SQLsupport.strategies;

import com.SQLsupport.DBClass.Review;
import com.SQLsupport.Requestable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static com.SQLsupport.Constants.*;

public class SelectReviewsForProduct implements Requestable {
    String productName;
    @Override
    public void getData(String data) {
        productName=data;
    }

    public Vector<Review> executeSelect(Connection conn) {
        int count=1;
        ResultSet res=null;
        Vector<Review> reviews=null;

        try{
            String sql1="SELECT " +
                    USER_SCHEMA+"." +USER_LOGIN+", "+
                    REVIEW_SCHEMA+"." +REVIEW_TEXT+
                    " FROM "+DB_NAME+"."+REVIEW_SCHEMA+
                    " INNER JOIN "+DB_NAME+"."+USER_SCHEMA+
                    " ON "+REVIEW_SCHEMA+"."+REVIEW_USER+"="+ USER_SCHEMA+"."+USER_ID+
                    " INNER JOIN "+DB_NAME+"."+ STUDENT_SCHEMA +
                    " ON "+REVIEW_SCHEMA+"."+ REVIEW_STUDENT +"="+ STUDENT_SCHEMA +"."+ STUDENT_ID +
                    " WHERE "+ STUDENT_SCHEMA +"."+ STUDENT_FIRSTNAME +"=?";
            try(PreparedStatement prepStmt=conn.prepareStatement(sql1)){
                prepStmt.setString(1, productName);
                res = prepStmt.executeQuery();
                reviews = new Vector<>();
                while(res.next()){
                    count=1;
                    String nameUser = res.getString(count++);
                    String reviewText= res.getString(count++);
                    reviews.add(new Review(nameUser,reviewText));
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return reviews;
    }
}
