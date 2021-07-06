package com.ambroziepaval.service;

import com.ambroziepaval.dao.MedicDao;
import com.ambroziepaval.dao.PatientDao;
import com.ambroziepaval.dao.UserDao;
import com.ambroziepaval.model.Medic;
import com.ambroziepaval.model.Patient;
import com.ambroziepaval.model.User;
import com.ambroziepaval.model.UserType;

import java.util.Date;

public class UserService {

    private UserDao userDao;
    private MedicDao medicDao;
    private PatientDao patientDao;

    public UserService() {
        userDao = new UserDao();
        medicDao = new MedicDao();
    }

    public boolean correctCredentials(String username, String password) {
        return userDao.correctCredentials(username, password);
    }

    public Medic createMedic(String username, String password, String email, String firstName, String lastName, Date birthDate, String specialty) {

        User user = new User(username, email, password, UserType.MEDIC);
        User savedUser = userDao.save(user);

        Medic medic = new Medic(lastName, firstName, birthDate, specialty, savedUser);
        medicDao.save(medic);
        return medicDao.findByUserId(savedUser.getId());
    }

    public Patient createPatient(String username, String password, String email, String firstName, String lastName, Date birthDate) {

        User user = new User(username, email, password, UserType.PATIENT);
        User savedUser = userDao.save(user);

        Patient patient = new Patient(lastName, firstName, birthDate, savedUser);
        patientDao.save(patient);
        return patientDao.findByUserId(savedUser.getId());
    }

    public User login(String username, String password) {
        // TODO
        return null;
    }
}
