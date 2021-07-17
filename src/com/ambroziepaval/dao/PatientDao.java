package com.ambroziepaval.dao;

import com.ambroziepaval.model.Patient;
import com.ambroziepaval.model.User;
import com.ambroziepaval.model.UserType;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDao extends GenericDao {

    public Patient save(Patient patient) {
        String query = """
                insert into patient(last_name, first_name, birth_date, user_id)
                values (?, ?, ?, ?)
                """;

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, patient.getLastName());
            preparedStatement.setString(2, patient.getFirstName());
            preparedStatement.setDate(3, new java.sql.Date(patient.getBirthDate().getTime()));
            preparedStatement.setInt(4, patient.getUser().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return findByUserId(patient.getUser().getId());
    }

    public Patient findByUserId(int userId) {
        String query = """
                select id, last_name, first_name, birth_date, user_id, username, password, email, type
                from patient
                inner join user on user.id = patient.user_id
                where user.id = ?
                """;

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                Date birthDate = resultSet.getDate("birth_date");

                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                UserType type = UserType.valueOf(resultSet.getString("type"));
                User user = new User(userId, username, password, email, type);

                return new Patient(id, lastName, firstName, birthDate, user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public List<Patient> findByMedicUserId(int medicUserId) {
        String query = """
                select patient.id, patient.last_name, patient.first_name, patient.birth_date, patient.user_id
                from patient
                inner join patient_file on patient.id = patient_file.patient_id
                inner join medic on medic.id = patient_file.medic_id
                inner join user on user.id = medic.user_id
                where user.id = ?
                """;

        List<Patient> medicPatients = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, medicUserId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String lastName = resultSet.getString("patient.last_name");
                String firstName = resultSet.getString("patient.first_name");
                Date birthDate = resultSet.getDate("patient.birth_date");
                int userId = resultSet.getInt("patient.user_id");
                medicPatients.add(new Patient(id, lastName, firstName, birthDate, new User(userId)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return medicPatients;
    }

    private Patient getPatientFromResultSet(int userId, ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String lastName = resultSet.getString("last_name");
        String firstName = resultSet.getString("first_name");
        Date birthDate = resultSet.getDate("birth_date");

        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        UserType type = UserType.valueOf(resultSet.getString("type"));
        User user = new User(userId, username, password, email, type);

        return new Patient(id, lastName, firstName, birthDate, user);
    }
}
