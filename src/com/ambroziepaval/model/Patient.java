package com.ambroziepaval.model;

import java.util.Date;

public class Patient {

    private int id;
    private String lastName;
    private String firstName;
    private Date birthDate;
    private User user;

    public Patient(String lastName, String firstName, Date birthDate, User user) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.user = user;
    }

    public Patient(int id, String lastName, String firstName, Date birthDate, User user) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                ", user=" + user +
                '}';
    }
}
