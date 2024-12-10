package com.SQLsupport.strategies;

import com.SQLsupport.DBClass.Rebate;
import com.SQLsupport.Requestable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static com.SQLsupport.Constants.*;

public class SelectAllRebates implements Requestable {
    private int id_user;
    @Override
    public void getData(String data) {
        id_user=Integer.parseInt(data);
    }

    public Vector<Rebate> executeSelect(Connection conn) {
        int count = 1;
        ResultSet res = null;
        Vector<Rebate> rebates = null;
        String sql1="";

        try {
            sql1 = "SELECT " +
                    COEFFICIENT_SCHEMA + "." + COEFFICIENT_ID + ", " +
                    MARK_SCHEMA + "." + MARK_MATHS + ", " +
                    COEFFICIENT_SCHEMA + "." + COEFFICIENT_COEFFICIENT +
                    " FROM " + DB_NAME + "." + COEFFICIENT_SCHEMA +
                    " INNER JOIN " + DB_NAME + "." + USER_SCHEMA +
                    " ON " + COEFFICIENT_SCHEMA + "." + COEFFICIENT_USER + "=" + USER_SCHEMA + "." + USER_ID +
                    " INNER JOIN " + DB_NAME + "." + MARK_SCHEMA +
                    " ON " + COEFFICIENT_SCHEMA + "." + COEFFICIENT_MARK_ID + "=" + MARK_SCHEMA + "." + MARK_ID +
                    " WHERE " + COEFFICIENT_SCHEMA + "." + COEFFICIENT_USER + "=? ORDER BY " + COEFFICIENT_ID;
            try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
                prepStmt.setInt(1, id_user);
                res = prepStmt.executeQuery();
                rebates = new Vector<>();
                while (res.next()) {
                    count = 1;
                    int idcoefficient = res.getInt(count++);
                    float coefficient = res.getFloat(count++);
                    int iduser = res.getInt(count++);
                    int idmark = res.getInt(count++);
                    rebates.add(new Rebate(idcoefficient, coefficient, iduser, idmark));
                }
            }
        } catch (SQLException e) {
            System.out.println(sql1);
            e.printStackTrace();
        }
        return rebates;
    }
}
