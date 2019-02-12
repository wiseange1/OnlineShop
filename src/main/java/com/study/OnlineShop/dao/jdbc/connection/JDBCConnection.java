package com.study.OnlineShop.dao.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnection {
    private Properties properties;
   //private static final String JDBC_CONNECTION = "jdbc:sqlite:C:\\Users\\Tema\\IdeaProjects\\test.db";

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(properties.getProperty("db.url"));
        //return DriverManager.getConnection("JDBC_CONNECTION");
    }
}
