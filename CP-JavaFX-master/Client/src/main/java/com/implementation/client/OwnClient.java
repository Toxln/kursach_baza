package com.implementation.client;



import com.SQLsupport.DBClass.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class OwnClient {
    private Socket client;
    private ObjectOutputStream output_stream;
    private ObjectInputStream input_stream;
    private Scanner scan;
    private static OwnClient ownClient=null;
    private Product selectableProductForReview;
    private User user=null;
    private boolean isRussianLanguage;
    private boolean isDarkTheme;
    private Vector<Rebate> clientsRebates;

    public boolean isRussianLanguage() {
        return isRussianLanguage;
    }

    public boolean isDarkTheme() {
        return isDarkTheme;
    }

    private OwnClient(String host, int port) {
        try {
            client = new Socket(host, port);
            output_stream = new ObjectOutputStream(client.getOutputStream());
            input_stream = new ObjectInputStream(client.getInputStream());
            scan = new Scanner(System.in);
            selectableProductForReview=null;
            isDarkTheme=true;
            isRussianLanguage=true;
            clientsRebates=new Vector<>();
            System.out.println("connection established...");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Product getSelectableProductForReview() {
        return selectableProductForReview;
    }

    public void setSelectableProductForReview(Product selectableProductForReview) {
        this.selectableProductForReview = selectableProductForReview;
    }

    public void switchLanguage(){
        isRussianLanguage=!isRussianLanguage;
    }

    public void switchTheme(){
        isDarkTheme=!isDarkTheme;
    }


    public static OwnClient getInstance(){
        if(ownClient==null){
            ownClient=new OwnClient("127.0.0.1",2525);
        }
        return ownClient;
    }

    public void setUserProfile(User us){
        user=new User(us);
    }

    public User getUserProfile(){
        return user;
    }

    public Vector<Rebate> getClientsRebates() {
        return clientsRebates;
    }

    public void sendDataToServer(String data){
        try {
            output_stream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean receiveResult(){
        try {
            return (boolean)input_stream.readObject();
        }catch (IOException |ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }

    public Vector<InformationForPieChart> receiveDataForPieChart(){
        try {
            return (Vector<InformationForPieChart>)input_stream.readObject();
        }catch (IOException |ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public String receiveFilePath(){
        try {
            return (String) input_stream.readObject();
        }catch (IOException |ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public Vector<User> receiveUsers(){
        try {
            return (Vector<User>) input_stream.readObject();
        }catch (IOException |ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public Vector<Manufacturer> receiveManufacturers(){
        try {
            return (Vector<Manufacturer>) input_stream.readObject();
        }catch (IOException |ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public void close(){
        try {
            output_stream.close();
            input_stream.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Vector<Product> receiveProducts() {
        try {
            return (Vector<Product>) input_stream.readObject();
        }catch (IOException |ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public Vector<Review> receiveReviews() {
        try {
            return (Vector<Review>) input_stream.readObject();
        }catch (IOException |ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public Vector<Purchase> receivePurchases() {
        try {
            return (Vector<Purchase>) input_stream.readObject();
        }catch (IOException |ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public Vector<Faq> receiveFAQ() {
        try {
            return (Vector<Faq>) input_stream.readObject();
        }catch (IOException |ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public Vector<Rebate> receiveRebates() {
        try {
            return (Vector<Rebate>) input_stream.readObject();
        }catch (IOException |ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}

