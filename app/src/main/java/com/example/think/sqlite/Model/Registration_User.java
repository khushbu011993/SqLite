package com.example.think.sqlite.Model;

public class Registration_User {

    String Id;
    String Name;
    String Password;
    String Date;
    String Mobile_No;
    String Email_Address;

    public Registration_User(String id, String name, String password, String date, String mobile_No, String email_Address) {
        Id = id;
        Name = name;
        Password = password;
        Date = date;
        Mobile_No = mobile_No;
        Email_Address = email_Address;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getMobile_No() {
        return Mobile_No;
    }

    public void setMobile_No(String mobile_No) {
        Mobile_No = mobile_No;
    }

    public String getEmail_Address() {
        return Email_Address;
    }

    public void setEmail_Address(String email_Address) {
        Email_Address = email_Address;
    }
}