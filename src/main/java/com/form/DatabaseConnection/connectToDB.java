package com.form.DatabaseConnection;
import java.sql.*;

public class connectToDB {

    private static final String URL = "jdbc:mysql://127.0.0.1:3307/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // Load the MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Establish the connection
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}

