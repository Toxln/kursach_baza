package com.SQLsupport.strategies;

import com.SQLsupport.DBClass.Product;
import com.SQLsupport.Requestable;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Vector;

public class PrintBasket implements Requestable {

    SelectAllProducts selectAllProducts=null;
    public PrintBasket(){
        selectAllProducts=new SelectAllProducts();
    }

    @Override
    public void getData(String data) {
    }

    public String execute(Connection conn){
        String path="file-scholarship.txt";
        Vector<Product> purchases = selectAllProducts.executeSelect(conn);
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
