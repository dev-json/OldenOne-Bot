package de.jxson.database;

import java.sql.*;
public class Database {

    private final DataSource dataSource;
    private Connection connection;
    private boolean isValidConnection;

    public Database(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean isValid() {
        try (Connection connection = getConnection()) {
            if (connection.isValid(6000))
                isValidConnection = false;
            isValidConnection = true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return isValidConnection;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed())
                connection = DriverManager.getConnection(dataSource.getConnectionString(), dataSource.username(), dataSource.password());
            return connection;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return connection;
    }



}
