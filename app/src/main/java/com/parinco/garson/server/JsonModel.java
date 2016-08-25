package com.parinco.garson.server;

/**
 * Created by saeed on 6/26/16.
 */
public class JsonModel {
    String name,password,contact,country;

    public JsonModel(String name, String password, String contact, String country) {
        this.name = name;
        this.password = password;
        this.contact = contact;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
