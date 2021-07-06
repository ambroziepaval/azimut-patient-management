package com.ambroziepaval.model;

import java.util.Date;

public class Medic {

    private int id;
    private String lastName;
    private String firstName;
    private Date birthDate;
    private String specialty;
    private User user;

    public Medic(String lastName, String firstName, Date birthDate, String specialty, User user) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.specialty = specialty;
        this.user = user;
    }

    public Medic(int id, String lastName, String firstName, Date birthDate, String specialty, User user) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.specialty = specialty;
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Medic{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                ", specialty='" + specialty + '\'' +
                ", user=" + user +
                '}';
    }
}
