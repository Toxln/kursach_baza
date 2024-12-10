package com.SQLsupport;

import java.sql.*;

public class DBConnection {
    private Connection connection;

    public DBConnection(){

    }

    public void init() throws InstantiationException, IllegalAccessException {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost/db", "root", "1111");
            System.out.println("Connection to database is succesfull!");
        }
        catch (SQLException | ClassNotFoundException e)
        {
            System.out.println("oooops");
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public Connection getMyConnection()
    {
        return connection;
    }

    public void destroy()
    {
        if(connection !=null)
        {
            try
            {
                connection.close();
            }
            catch(Exception e){}
        }
    }

}


