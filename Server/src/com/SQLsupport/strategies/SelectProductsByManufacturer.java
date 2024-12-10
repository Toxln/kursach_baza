package com.SQLsupport.strategies;

import com.SQLsupport.DBClass.Product;
import com.SQLsupport.SelectableProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static com.SQLsupport.Constants.*;

public class SelectProductsByManufacturer implements SelectableProduct {

    private String nameOfManufacturer;

    @Override
    public void getData(String data) {
        nameOfManufacturer=data;
    }

    @Override
    public Vector<Product> executeSelect(Connection conn) {
        int count=1;
        ResultSet res=null;
        Vector<Product> students=null;

        try{
            String sql1="SELECT " +
                    STUDENT_SCHEMA +"." + STUDENT_ID +", "+
                    STUDENT_SCHEMA +"." + STUDENT_FIRSTNAME +", "+
                    STUDENT_SCHEMA +"." + STUDENT_LASTNAME +", "+
                    STUDENT_SCHEMA +"." + STUDENT_AVEGRADE +", "+
                    STUDENT_SCHEMA +"." + STUDENT_MARK_ID +", "+
                    MARK_SCHEMA +"." + MARK_MATHS +
                    " FROM "+DB_NAME+"."+ STUDENT_SCHEMA +
                    " INNER JOIN "+DB_NAME+"."+ MARK_SCHEMA +
                    " ON "+ STUDENT_SCHEMA +"."+ STUDENT_SCHOLARSHIP_ID +"="+ MARK_SCHEMA +"."+ MARK_ID +
                    " AND "+ MARK_SCHEMA +"."+ MARK_MATHS +"=?"+
                    " ORDER BY "+ STUDENT_SCHEMA +"."+ STUDENT_ID;

            try(PreparedStatement prepStmt=conn.prepareStatement(sql1)){
                prepStmt.setString(1, nameOfManufacturer);
                res = prepStmt.executeQuery();
                students = new Vector<>();
                while(res.next()){
                    count=1;
                    int id = res.getInt(count++);
                    String first_name = res.getString(count++);
                    String last_name = res.getString(count++);
                    float ave_grade = res.getFloat(count++);
                    int idnark = res.getInt(count++);
                    int idscholarship = res.getInt(count++);
                    float iduser = res.getFloat(count++);
                    students.add(new Product(id, first_name, last_name, ave_grade, idnark, idscholarship, iduser));
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return students;
    }
}
