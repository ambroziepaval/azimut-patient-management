package com.ambroziepaval.ui;

import com.ambroziepaval.model.Medic;
import com.ambroziepaval.model.Patient;
import com.ambroziepaval.model.User;
import com.ambroziepaval.service.UserService;

import java.util.Date;

public class UI {

    private Medic savedMedic;
    private Patient savedPatient;

    private UserService userService;

    public UI() {
        userService = new UserService();
    }

    public void doingStuffOnUI() {

        // login
        String username = "drHouse";
        String password = "password1";
        if (userService.correctCredentials(username, password)) {
            System.out.println("Logged in.");
        } else {
            System.err.println("Incorrect credentials");
        }

        User loggedInUser = userService.login(username, password);

    }

    public void createMedic() {
        savedMedic = userService.createMedic("username1", "password", "email@vls.com", "FirstName", "LastName", new Date(), "ORL");
    }

    public void createPatient() {
        savedPatient = userService.createPatient("username2", "password", "email@vls.com", "FirstName", "LastName", new Date());
    }
}
