package com.ambroziepaval.dao;

import com.ambroziepaval.model.User;
import com.ambroziepaval.model.UserType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends GenericDao {

    public User findByUsername(String username) {
        String query = """
                select id, username, password, email, type
                from user
                where username = ?
                """;

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                UserType type = UserType.valueOf(resultSet.getString("type"));
                return new User(id, username, password, email, type);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public User save(User user) {
        String query = """
                insert into user(username, password, email, type)
                value (?, ?, ?, ?);
                """;

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUserType().name());
            preparedStatement.executeUpdate();
            return findByUsername(user.getUsername());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean correctCredentials(String username, String password) {
        String query = """
                select exists(
                	select * from user
                    where username = ?
                    and password = ?
                )
                """;

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }
}
