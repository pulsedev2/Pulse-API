package fr.pulsedev.api.DataManagement.SqlManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This file is a part of PulseAPI, located on fr.pulsedev.pulseapi.DataManagement.SqlManager
 * Copyright (c) Renard - All rights reserved
 *
 * @author Renard
 * Created the 19/06/2020 at 23:30.
 */
public class SQL {

    private Connection connection;
    private final String base;
    private final String host;
    private final String database;
    private final String user;
    private final String password;
    private final String port;

    public SQL(String base, String host, String port, String database, String user, String password) {
        this.base = base;
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public void connect() {
        if (!isConnected()) {
            try {
                connection = DriverManager.getConnection(base + host + ":" + port + "/" + database, user, password);
                System.out.println("DataBase Connected");
            } catch (SQLException e) {
                System.out.println("Connection Error");
                e.printStackTrace();
            }
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
                System.out.println("DataBase Disconnected");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void request(String request){
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return connection != null;
    }

    public Connection getConnection() {
        return connection;
    }
}

