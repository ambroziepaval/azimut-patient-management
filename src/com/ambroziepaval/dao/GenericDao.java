package com.ambroziepaval.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class GenericDao {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/pacient_management_db";
    private static final String DB_USER = "pm_db_user";
    private static final String DB_USER_PASS = "pm_db_user_pass";

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL, DB_USER, DB_USER_PASS);
    }
}
