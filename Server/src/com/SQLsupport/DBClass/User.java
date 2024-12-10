package com.SQLsupport.DBClass;

import java.io.Serializable;

public class User implements Serializable {
    int id;
    String login;
    String password;
    int role;
    String firstName;
    String lastName;
    String phone;

    public User(String login, String password, int role, String firstName, String lastName, String phone) {
        this.id=-1;
        this.login = login;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public User(User user){
        this.id=user.id;
        this.login = user.login;
        this.password = user.password;
        this.role = user.role;
        this.lastName = user.lastName;
        this.firstName = user.firstName;
        this.phone = user.phone;
    }

    public User(int id, String login, String password, int role, String firstName,
                String lastName, String phone) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
    }

    public void print(){
        System.out.println(id);
        System.out.println(login);
        System.out.println(password);
        System.out.println(role);
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(phone);
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
