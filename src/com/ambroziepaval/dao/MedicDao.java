package com.ambroziepaval.dao;

import com.ambroziepaval.model.Medic;
import com.ambroziepaval.model.User;
import com.ambroziepaval.model.UserType;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicDao extends GenericDao {

    public Medic findByUserId(int userId) {
        String query = """
                select medic.id, last_name, first_name, birth_date, specialty, username, password, email, type
                from medic
                inner join user on user.id = medic.user_id
                where user.id = ?
                """;

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("medic.id");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                Date birthDate = resultSet.getDate("birth_date");
                String specialty = resultSet.getString("specialty");

                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                UserType type = UserType.valueOf(resultSet.getString("type"));
                User user = new User(userId, username, password, email, type);

                return new Medic(id, lastName, firstName, birthDate, specialty, user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public Medic save(Medic medic) {
        String query = """
                insert into medic(last_name, first_name, birth_date, specialty, user_id) 
                values (?, ?, ?, ?, ?);    
                """;

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, medic.getLastName());
            preparedStatement.setString(2, medic.getFirstName());
            preparedStatement.setDate(3, new java.sql.Date(medic.getBirthDate().getTime()));
            preparedStatement.setString(4, medic.getSpecialty());
            preparedStatement.setInt(5, medic.getUser().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return findByUserId(medic.getUser().getId());
    }
}
