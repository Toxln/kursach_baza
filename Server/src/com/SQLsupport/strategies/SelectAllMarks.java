package com.SQLsupport.strategies;

import com.SQLsupport.DBClass.Manufacturer;
import com.SQLsupport.DBClass.Product;
import com.SQLsupport.Requestable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static com.SQLsupport.Constants.*;

public class SelectAllMarks implements Requestable {

    @Override
    public void getData(String data) {

    }

    public Vector<Manufacturer> executeSelect(Connection conn) {
        int count = 1;
        ResultSet res = null;
        Vector<Manufacturer> manufacturers = null;

        try {
            String sql1 = "SELECT " +
                    STUDENT_SCHEMA + "." + STUDENT_ID + ", " +
                    STUDENT_SCHEMA + "." + STUDENT_FIRSTNAME + ", " +
                    STUDENT_SCHEMA + "." + STUDENT_LASTNAME + ", " +
                    MARK_SCHEMA + "." + MARK_MATHS + ", " +
                    MARK_SCHEMA + "." + MARK_PROGRAMMING + ", " +
                    MARK_SCHEMA + "." + MARK_UNITY + ", " +
                    MARK_SCHEMA + "." + MARK_COURSEWORK +
                    " FROM " + DB_NAME + "." + STUDENT_SCHEMA +
                    " INNER JOIN "+DB_NAME+"."+MARK_SCHEMA+
                    " ON "+ STUDENT_SCHEMA +"."+STUDENT_ID+"="+ MARK_SCHEMA+"."+MARK_ID+
                    " ORDER BY " + STUDENT_SCHEMA + "." + STUDENT_ID;
            try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
                res = prepStmt.executeQuery();
                manufacturers = new Vector<>();
                while (res.next()) {
                    count = 1;
                    int id = res.getInt(count++);
                    String first_name = res.getString(count++);
                    String last_name = res.getString(count++);
                    int maths = res.getInt(count++);
                    int programming = res.getInt(count++);
                    int unity = res.getInt(count++);
                    int course_work = res.getInt(count++);
                    manufacturers.add(new Manufacturer(id, first_name, last_name, maths, programming, unity, course_work));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return manufacturers;
    }
}
