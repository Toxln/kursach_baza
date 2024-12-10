package com.SQLsupport.strategies;

import com.SQLsupport.DBClass.Faq;
import com.SQLsupport.DBClass.Manufacturer;
import com.SQLsupport.Requestable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static com.SQLsupport.Constants.*;

public class SelectAllFAQ implements Requestable {
    @Override
    public void getData(String data) {

    }

    public Vector<Faq> executeSelect(Connection conn){
        int count;
        ResultSet res;
        Vector<Faq> faq=null;

        try{
            String sql1="SELECT "+FAQ_QUESTION+","+FAQ_ANSWER+" FROM "+DB_NAME+"."+FAQ_SCHEMA;
            try(PreparedStatement prepStmt=conn.prepareStatement(sql1)){
                res = prepStmt.executeQuery();
                faq = new Vector<>();
                while(res.next()){
                    count=1;
                    String question = res.getString(count++);
                    String answer= res.getString(count++);
                    faq.add(new Faq(question,answer));
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return faq;
    }
}
