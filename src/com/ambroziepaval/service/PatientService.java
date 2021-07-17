package com.ambroziepaval.service;

import com.ambroziepaval.dao.AppointmentDao;
import com.ambroziepaval.dao.PatientDao;
import com.ambroziepaval.model.Patient;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PatientService {

    private final PatientDao patientDao;
    private final AppointmentDao appointmentDao;

    public PatientService() {
        patientDao = new PatientDao();
        appointmentDao = new AppointmentDao();
    }

    public List<Patient> getPatientsByMedicUserId(int medicUserId) {
        return patientDao.findByMedicUserId(medicUserId);
    }

    public boolean makeAppointment(int patientUserId, int medicId, Date appointmentDate) {

        Patient patient = patientDao.findByUserId(patientUserId);
        List<Date> appointmentDates = appointmentDao.getAppointmentDatesByMedicId(medicId);

        if (existsDateConflict(appointmentDates, appointmentDate)) {
            return false;
        }
        appointmentDao.save(patient.getId(), medicId, appointmentDate);
        return true;
    }

    /**
     * Check if there is a conflict between the existing dates and the new date with a 1h delta.
     */
    private boolean existsDateConflict(List<Date> existingDates, Date newDate) {

        Calendar calendar = Calendar.getInstance();

        for (Date existingDate : existingDates) {

            calendar.setTime(existingDate);
            calendar.add(Calendar.HOUR_OF_DAY, 1);
            Date existingDatePlus1h = calendar.getTime();
            if (!existingDatePlus1h.before(newDate)) {
                return true;
            }

            calendar.setTime(newDate);
            calendar.add(Calendar.HOUR_OF_DAY, 1);
            Date newDatePlus1h = calendar.getTime();
            if (!newDatePlus1h.after(existingDate)) {
                return true;
            }

        }
        return false;
    }
}
