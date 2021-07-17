package com.ambroziepaval.ui;

import com.ambroziepaval.model.Medic;
import com.ambroziepaval.model.Patient;
import com.ambroziepaval.model.User;
import com.ambroziepaval.service.MedicService;
import com.ambroziepaval.service.PatientService;
import com.ambroziepaval.service.UserService;

import java.util.Date;
import java.util.List;

public class UI {

    private User loggedInUser;

    private final UserService userService;
    private final PatientService patientService;
    private final MedicService medicService;

    public UI() {
        userService = new UserService();
        patientService = new PatientService();
        medicService = new MedicService();
    }

    public void doingStuffOnUI() {

        // login as MEDIC
        String username = "drHouse";
        String password = "password";
        login(username, password);

        // get patient list
        System.out.println("Patients for the medic: " + loggedInUser.getUsername());
        List<Patient> medicPatients = patientService.getPatientsByMedicUserId(loggedInUser.getId());
        medicPatients.forEach(System.out::println);

        System.out.println();

        // login as PATIENT
        username = "johnny.doe";
        login(username, password);
        // create new appointment
        List<Medic> allMedics = medicService.getAllMedics();
        allMedics.forEach(System.out::println);
        // select appointment date
        Date appointmentDate = new Date();
        boolean created = patientService.makeAppointment(loggedInUser.getId(), allMedics.get(0).getId(), appointmentDate);
        if (created) {
            System.out.println("--> Appointment created on " + appointmentDate);
        } else {
            System.err.println("Could not create appointment.");
        }
    }

    public void createMedic() {
        System.out.println("Creating medic account..");
        Medic medic = userService.createMedic("username1", "password", "email@vls.com", "FirstName", "LastName", new Date(), "ORL");
        loggedInUser = medic.getUser();
        System.out.println("-> Logged in with user " + loggedInUser.getUsername() + " as a " + loggedInUser.getUserType());
    }

    public void createPatient() {
        System.out.println("Creating patient account..");
        Patient patient = userService.createPatient("username2", "password", "email@vls.com", "FirstName", "LastName", new Date());
        loggedInUser = patient.getUser();
        System.out.println("-> Logged in with user " + loggedInUser.getUsername() + " as a " + loggedInUser.getUserType());
    }

    public void login(String username, String password) {
        System.out.println("Logging in as " + username + " with password: " + password);
        loggedInUser = userService.login(username, password);
        if (loggedInUser != null) {
            System.out.println("-> Logged in with username " + loggedInUser.getUsername() + " as a " + loggedInUser.getUserType());
        } else {
            System.err.println("INVALID CREDENTIALS!");
        }
    }
}
