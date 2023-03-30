package com.ayseozcan.entity;

import java.util.Date;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Date joinedDate;

    public  Person(){

    }
    public Person(String firstName, String lastName, String email, Date joinedDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.joinedDate = joinedDate;
    }

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
