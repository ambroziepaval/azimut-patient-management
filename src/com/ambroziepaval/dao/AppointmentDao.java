package com.ambroziepaval.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentDao extends GenericDao {


    public void save(int patientId, int medicId, Date appointmentDate) {
        String query = """
                insert into appointment(patient_id, medic_id, appointment_date) values (?, ?, ?)
                """;

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, patientId);
            preparedStatement.setInt(2, medicId);
            preparedStatement.setTimestamp(3, new Timestamp(appointmentDate.getTime()));
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Date> getAppointmentDatesByMedicId(int medicId) {
        String query = """
                select appointment_date
                from appointment
                where medic_id = ?
                """;

        List<Date> appointmentDates = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, medicId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                appointmentDates.add(new Date(resultSet.getTimestamp("appointment_date").getTime()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return appointmentDates;
    }
}
