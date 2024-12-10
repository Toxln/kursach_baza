package com.SQLsupport.strategies;

import com.SQLsupport.DBClass.InformationForPieChart;
import com.SQLsupport.Requestable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static com.SQLsupport.Constants.*;

public class SelectDataForPieChart implements Requestable {
    @Override
    public void getData(String data) {

    }

    public Vector<InformationForPieChart> execute(Connection conn){

        ResultSet res;
        Vector<InformationForPieChart> informationForPieCharts=new Vector<>();

        int max=getManufCount(conn);
        int currentNumber=1;
        Float avegrade;
        int count=1, amount = 1;

        String sql1="SELECT " + STUDENT_AVEGRADE +
                " FROM "+DB_NAME+"."+ STUDENT_SCHEMA;

            try(PreparedStatement prepStmt=conn.prepareStatement(sql1)){
                res = prepStmt.executeQuery();
                while (res.next()){
                    avegrade=res.getFloat(1);
                    informationForPieCharts.add(new InformationForPieChart(avegrade,amount));
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }


        return informationForPieCharts;
    }

    public int getManufCount(Connection conn){
        ResultSet res;
        String sql1="SELECT COUNT(*) FROM "+DB_NAME+"."+ STUDENT_SCHEMA;
        try(PreparedStatement prepStmt=conn.prepareStatement(sql1)){
            res = prepStmt.executeQuery();
            if(res.next()) return res.getInt(1);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }
}
