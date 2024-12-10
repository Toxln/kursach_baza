package com.server;

import com.SQLsupport.DBConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.ResultSet;

public class OwnServer {

    private ServerSocket serverSocket;
    private static OwnServer oneServer=null;
    private DBConnection dbConnection=null;

    private OwnServer(){
        final int PORT = 2525;
        try {
            serverSocket = new ServerSocket(PORT);
            dbConnection=new DBConnection();
            dbConnection.init();
        } catch (IOException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static OwnServer createOwnServer(){
        if(oneServer==null){
            oneServer=new OwnServer();
        }
        return oneServer;
    }

    public void run() {
        while (true)
            new Thread(new ThreadForServer(serverSocket,dbConnection)).start();
    }

    public void close(){
        try{
            serverSocket.close();
            dbConnection.destroy();
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
