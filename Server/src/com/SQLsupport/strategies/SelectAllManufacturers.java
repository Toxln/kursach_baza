package com.SQLsupport.strategies;

import com.SQLsupport.DBClass.Product;
import com.SQLsupport.Requestable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static com.SQLsupport.Constants.*;

public class SelectAllManufacturers implements Requestable {

    @Override
    public void getData(String data) {

    }

    public Vector<Product> executeSelect(Connection conn) {
        int count = 1;
        ResultSet res = null;
        Vector<Product> manufacturers = null;

        try {
            String sql1 = "SELECT " +
                    STUDENT_SCHEMA + "." + STUDENT_ID + ", " +
                    STUDENT_SCHEMA + "." + STUDENT_FIRSTNAME + ", " +
                    STUDENT_SCHEMA + "." + STUDENT_LASTNAME + ", " +
                    STUDENT_SCHEMA + "." + STUDENT_AVEGRADE + ", " +
                    STUDENT_SCHEMA + "." + STUDENT_MARK_ID + ", " +
                    SCHOLARSHIP_SCHEMA + "." + SCHOLARSHIP_SCHOLARSHIP +
                    " FROM " + DB_NAME + "." + STUDENT_SCHEMA +
                    " INNER JOIN "+DB_NAME+"."+SCHOLARSHIP_SCHEMA+
                    " ON "+ STUDENT_SCHEMA +"."+STUDENT_ID+"="+ SCHOLARSHIP_SCHEMA+"."+SCHOLARSHIP_ID+
                    " INNER JOIN "+DB_NAME+"."+COEFFICIENT_SCHEMA+
                    " ON "+ STUDENT_SCHEMA +"."+STUDENT_ID+"="+COEFFICIENT_SCHEMA+"."+COEFFICIENT_ID+
                    " ORDER BY " + STUDENT_SCHEMA + "." + STUDENT_ID;
            try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
                res = prepStmt.executeQuery();
                manufacturers = new Vector<>();
                while (res.next()) {
                    count = 1;
                    int id = res.getInt(count++);
                    String first_name = res.getString(count++);
                    String last_name = res.getString(count++);
                    float ave_grade = res.getFloat(count++);
                    int idmark = res.getInt(count++);
                    int idscholarship = res.getInt(count++);
                    float iduser = 1;
                    manufacturers.add(new Product(id, first_name, last_name, ave_grade, idmark, idscholarship, iduser));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return manufacturers;
    }
}
