package de.jxson.database;

public class DataSource {
    private String username, password, hostname, database;
    private int port;

    public DataSource(String username, String password, String hostname, String database) {
        this.username = username;
        this.password = password;
        this.hostname = hostname;
        this.database = database;
        this.port = 3306;
    }

    public DataSource(String username, String password, String hostname, String database, int port) {
        this.username = username;
        this.password = password;
        this.hostname = hostname;
        this.database = database;
        this.port = port;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public String hostname() {
        return hostname;
    }

    public String database() {
        return database;
    }

    public int port() {
        return port;
    }

    public String getConnectionString() {
        return "jdbc:mariadb://" + hostname() + ":" + port() + "/" + database();
    }
}