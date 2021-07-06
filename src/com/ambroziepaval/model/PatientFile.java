package com.ambroziepaval.model;

public class PatientFile {

    private int id;
    private Patient patient;
    private Medic medic;
    private String diagnostic;
    private String treatment;

    public PatientFile(int id, Patient patient, Medic medic, String diagnostic, String treatment) {
        this.id = id;
        this.patient = patient;
        this.medic = medic;
        this.diagnostic = diagnostic;
        this.treatment = treatment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    @Override
    public String toString() {
        return "PatientFile{" +
                "id=" + id +
                ", patient=" + patient +
                ", medic=" + medic +
                ", diagnostic='" + diagnostic + '\'' +
                ", treatment='" + treatment + '\'' +
                '}';
    }
}
