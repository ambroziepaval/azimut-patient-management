package com.ambroziepaval.dao;

import com.ambroziepaval.model.Medic;
import com.ambroziepaval.model.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicDao extends GenericDao {

    public Medic findByUserId(int userId) {
        String query = """
                select id, last_name, first_name, birth_date, specialty, user_id
                from medic
                where user_id = ?
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
                String specialty = resultSet.getString("specialty");
                int dbUserId = resultSet.getInt("user_id");
                return new Medic(id, lastName, firstName, new java.util.Date(birthDate.getTime()), specialty, new User(dbUserId));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public void save(Medic medic) {
        String query = """
                insert into medic(last_name, first_name, birth_date, specialty, user_id) 
                values (?, ?, ?, ?, ?);    
                """;

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, medic.getLastName());
            preparedStatement.setString(2, medic.getFirstName());
            preparedStatement.setDate(3, new java.sql.Date(medic.getBirthDate().getTime()));
            preparedStatement.setString(4, medic.getSpecialty());
            preparedStatement.setInt(5, medic.getUser().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
