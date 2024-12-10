package com.server;

import com.SQLsupport.DBClass.*;
import com.SQLsupport.SelectableProduct;
import com.SQLsupport.strategies.*;
import com.SQLsupport.DBConnection;
import com.SQLsupport.Updatable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;

public class ThreadForServer implements Runnable{
    private Socket client;
    private ObjectInputStream input_stream;
    private ObjectOutputStream output_stream;
    private static int allClientCount = 0;
    private int currentClient;
    private DBConnection dbConnection;

    public ThreadForServer(ServerSocket serverSocket, DBConnection dbConnection){

        try {
            client=serverSocket.accept();
            allClientCount++;
            currentClient=allClientCount;
            System.out.println("client №" +currentClient+" connected");
            input_stream = new ObjectInputStream(client.getInputStream());
            output_stream = new ObjectOutputStream(client.getOutputStream());
            this.dbConnection=dbConnection;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Release() throws IOException, NullPointerException {
        input_stream.close();
        output_stream.close();
        client.close();
    }

    private void closeThread(){
        try {
            System.out.println("client №" + currentClient + " disconnected");
            allClientCount--;
            this.Release();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while(true){
            try{
                if(!client.isConnected()||client.isClosed()) {
                    System.out.println("looooooooooooooooooooooool");
                }
                String clientChoice=(String)input_stream.readObject();
                String dataFromClient=(String)input_stream.readObject();

                Updatable sqlUpdate=null;
                switch (clientChoice) {
                    case "registration" -> sqlUpdate = new AddUser();
                    case "add review" -> sqlUpdate=new AddReview();
                    case "add faq" -> sqlUpdate=new AddFaq();
                    case "add mark to manufacturer" -> sqlUpdate=new AddMark();
                    case "delete one purchase" -> sqlUpdate=new DeleteOnePurchase();
                    case "edit user" -> sqlUpdate=new EditUser();
                    case "delete one rebate"->sqlUpdate=new DeleteOneRebate();
                    case "edit role user"->sqlUpdate=new EditRoleUser();
                    case "create product"->sqlUpdate=new CreateProduct();
                    case "delete one product"->sqlUpdate=new DeleteOneProduct();
                    case "create manufacturer"->sqlUpdate=new AddManufacturer();
                    case "delete one manufacturer"->sqlUpdate=new DeleteOneManufacturer();
                    case "exit" -> {
                        closeThread();
                        return;
                    }
                }
                if(sqlUpdate!=null){
                    sqlUpdate.getData(dataFromClient);
                    boolean res = sqlUpdate.executeUpdate(dbConnection.getMyConnection());
                    output_stream.writeObject(res);
                }

                //all selects of products
                SelectableProduct sqlSelectProduct=null;
                switch (clientChoice) {
                    case "select all products" -> sqlSelectProduct = new SelectAllProducts();
                    case "select one product" -> sqlSelectProduct = new SelectOneProduct();
                    case "select by manufacturer" -> sqlSelectProduct = new SelectProductsByManufacturer();
                }
                if(sqlSelectProduct!=null){
                    sqlSelectProduct.getData(dataFromClient);
                    Vector<Product> product = sqlSelectProduct.executeSelect(dbConnection.getMyConnection());
                    output_stream.writeObject(product);
                }


                //check all select requests
                switch (clientChoice) {
                    case "signIn" -> {
                        var sqlSelect = new SelectUser();
                        sqlSelect.getData(dataFromClient);
                        Vector<User> user = sqlSelect.executeSelect(dbConnection.getMyConnection());
                        output_stream.writeObject(user);
                    }
                    case "select all manufacturer" -> {
                        var sqlSelect1 = new SelectAllManufacturers();
                        Vector<Product> manufacturers = sqlSelect1.executeSelect(dbConnection.getMyConnection());
                        output_stream.writeObject(manufacturers);
                    }
                    case "select all marks" -> {
                        var sqlSelect1 = new SelectAllMarks();
                        sqlSelect1.getData(dataFromClient);
                        Vector<Manufacturer> manufacturers = sqlSelect1.executeSelect(dbConnection.getMyConnection());
                        output_stream.writeObject(manufacturers);
                    }
                    case "select all reviews" ->{
                        var sqlSelect2=new SelectReviewsForProduct();
                        sqlSelect2.getData(dataFromClient);
                        Vector<Review> reviews = sqlSelect2.executeSelect(dbConnection.getMyConnection());
                        output_stream.writeObject(reviews);
                    }
                    case "select all purchases"->{
                        var sqlSelect3=new SelectAllPurchases();
                        sqlSelect3.getData(dataFromClient);
                        Vector<Purchase> purchases = sqlSelect3.executeSelect(dbConnection.getMyConnection());
                        output_stream.writeObject(purchases);
                    }
                    case "print basket"->{
                        var sqlSelect3=new PrintBasket();
                        String filePath = sqlSelect3.execute(dbConnection.getMyConnection());
                        output_stream.writeObject(filePath);
                    }
                    case "print marks"->{
                        var sqlSelect3=new PrintMarks();
                        String filePath = sqlSelect3.execute(dbConnection.getMyConnection());
                        output_stream.writeObject(filePath);
                    }
                    case "select data for pie chart"->{
                        var sqlSelect3=new SelectDataForPieChart();
                        Vector<InformationForPieChart> informationForPieCharts = sqlSelect3.execute(dbConnection.getMyConnection());
                        output_stream.writeObject(informationForPieCharts);
                    }
                    case "select all faq"->{
                        var sqlSelect3=new SelectAllFAQ();
                        Vector<Faq> faqs = sqlSelect3.executeSelect(dbConnection.getMyConnection());
                        output_stream.writeObject(faqs);
                    }
                    case "select all rebates"->{
                        var sqlSelect3=new SelectAllRebates();
                        sqlSelect3.getData(dataFromClient);
                        Vector<Rebate> rebates = sqlSelect3.executeSelect(dbConnection.getMyConnection());
                        output_stream.writeObject(rebates);
                    }
                    case "select all users"->{
                        var sqlSelect3=new SelectAllUsers();
                        sqlSelect3.getData(dataFromClient);
                        Vector<User> users = sqlSelect3.executeSelect(dbConnection.getMyConnection());
                        output_stream.writeObject(users);
                    }
                }
            }
            catch ( SocketException e) {
                System.out.println("client №"+currentClient+" break connection");
                closeThread();
                return;
            }
            catch (IOException | ClassNotFoundException  e){
                e.printStackTrace();
            }

        }
    }
}
