package com.example.sqlitethi60.model;

public class Students {
    private int id;
    private String name,number,email;

    public Students(int id, String name, String number, String email) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.email = email;
    }

    public Students(String name, String number, String email) {
        this.name = name;
        this.number = number;
        this.email = email;
    }

    public Students() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ID: "+ getId() + " \n"+ "Name: "+ getName() + " \n"+"Number: "+ getNumber() + " \n"
                + "Email: "+ getEmail();
    }
}
