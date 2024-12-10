package com.SQLsupport.strategies;

import com.SQLsupport.DBClass.Manufacturer;
import com.SQLsupport.DBClass.Product;
import com.SQLsupport.Requestable;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;

public class PrintMarks implements Requestable {

    SelectAllMarks selectAllMarks=null;
    public PrintMarks(){
        selectAllMarks=new SelectAllMarks();
    }

    @Override
    public void getData(String data) {
    }

    public String execute(Connection conn){
        String path="file-marks.bin";
        Vector<Manufacturer> purchases = selectAllMarks.executeSelect(conn);
        try(FileWriter writer = new FileWriter(path, false))
        {
            for (var purchase:purchases)
                purchase.print(writer);
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        return path;
    }
}
