package com.ambroziepaval.model;

import java.util.Date;

public class Appointment {

    private int id;
    private Medic medic;
    private Patient patient;
    private Date date;
    private boolean completed;

    public Appointment(int id, Medic medic, Patient patient, Date date, boolean completed) {
        this.id = id;
        this.medic = medic;
        this.patient = patient;
        this.date = date;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", medic=" + medic +
                ", patient=" + patient +
                ", date=" + date +
                ", completed=" + completed +
                '}';
    }
}
