package com.ambroziepaval.service;

import com.ambroziepaval.dao.PatientDao;
import com.ambroziepaval.model.Patient;

import java.util.List;

public class PatientService {

    private final PatientDao patientDao;

    public PatientService() {
        patientDao = new PatientDao();
    }

    public List<Patient> getPatientsByMedicUserId(int medicUserId) {
        return patientDao.findByMedicUserId(medicUserId);
    }

}
