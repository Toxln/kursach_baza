package com.SQLsupport.strategies;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.SQLsupport.Updatable;

import static com.SQLsupport.Constants.*;

public class AddManufacturer implements Updatable {
    int programming, maths, unity;
    int course_work, idmark;


    @Override
    public void getData(String data) {
        int i=0;
        String[] dataFromClient=data.split("@@@");
        idmark = Integer.parseInt(dataFromClient[i++]);
        maths=Integer.parseInt(dataFromClient[i++]);
        programming=Integer.parseInt(dataFromClient[i++]);
        unity=Integer.parseInt(dataFromClient[i++]);
        course_work=Integer.parseInt(dataFromClient[i++]);

    }

    @Override
    public boolean executeUpdate(Connection conn) {
        int count=1;
        try{
            String sql1="INSERT INTO "+DB_NAME+"."+MARK_SCHEMA+" ("+
                    MARK_ID + ","+
                    MARK_MATHS+","+
                    MARK_PROGRAMMING+","+
                    MARK_UNITY+","+
                    MARK_COURSEWORK+")"+
                    " VALUES (?,?,?,?,?);";
            try(PreparedStatement prepStmt=conn.prepareStatement(sql1)){
                prepStmt.setInt(count++, idmark);
                prepStmt.setInt(count++, maths);
                prepStmt.setInt(count++, programming);
                prepStmt.setInt(count++, unity);
                prepStmt.setInt(count++, course_work);
                return prepStmt.executeUpdate() > 0;
            }
        }catch(SQLException e){

        }
        return false;
    }
}
