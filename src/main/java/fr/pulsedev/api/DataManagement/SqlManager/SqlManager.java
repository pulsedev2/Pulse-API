package fr.pulsedev.api.DataManagement.SqlManager;

import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This file is a part of PulseAPI, located on fr.pulsedev.pulseapi.DataManagement.SqlManager
 * Copyright (c) Renard - All rights reserved
 *
 * @author Renard
 * Created the 19/06/2020 at 23:31.
 */
public class SqlManager {

    SQL sql;

    public SqlManager(SQL sql) {
        this.sql = sql;
    }


    public String getString(String tableName, String valueName, String value, String columnName){
        try {
            String request = "SELECT * FROM " + tableName + " WHERE " + valueName + " = '" + value + "'";
            PreparedStatement preparedStatement = sql.getConnection().prepareStatement(request);
            ResultSet rs = preparedStatement.executeQuery();
            String objectReturned = "";
            while (rs.next()){
                objectReturned = rs.getString(columnName);
            }
            preparedStatement.close();
            return objectReturned;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getInt(String tableName, String valueName, String value, String columnName){
        try {
            String request = "SELECT * FROM " + tableName + " WHERE " + valueName + " = '" + value + "'";
            PreparedStatement preparedStatement = sql.getConnection().prepareStatement(request);
            ResultSet rs = preparedStatement.executeQuery();
            int objectReturned = 0;
            while (rs.next()){
                objectReturned = rs.getInt(columnName);
            }
            preparedStatement.close();
            return objectReturned;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(String table, Object[] valueNames, Object[] values){
        try {
            StringBuilder requestBuilder = new StringBuilder();
            requestBuilder.append("INSERT INTO ")
                    .append(table)
                    .append(" (");
            if(valueNames.length >= 2){
                for(Object valueName: valueNames){
                    requestBuilder.append(valueName);
                    requestBuilder.append(",");
                }
            }else {
                requestBuilder.append(valueNames[0]).append(")");
            }

            String request = requestBuilder.toString().substring(0, requestBuilder.toString().length() - 1) + ")";

            StringBuilder requestBuilder_ = new StringBuilder(request);
            requestBuilder_.append(" VALUES (");

            if(values.length >= 2){
                for(Object value: values){
                    requestBuilder_.append("'").append(value).append("'");
                    requestBuilder_.append(",");
                }
            }else {
                requestBuilder_.append("'").append(values[0]).append("')");
            }

            String request_ = requestBuilder_.toString().substring(0, requestBuilder_.toString().length() - 1) + ")";
            PreparedStatement preparedStatement = sql.getConnection().prepareStatement(request_);

            Bukkit.broadcastMessage(request_);

            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object get(String tableName, String valueName, String value, String columnName){
        try {
            String request = "SELECT * FROM " + tableName + " WHERE " + valueName + " = '" + value + "'";
            PreparedStatement preparedStatement = sql.getConnection().prepareStatement(request);
            ResultSet rs = preparedStatement.executeQuery();
            Object objectReturned = new Object();
            while (rs.next()){
                objectReturned = rs.getObject(columnName);
            }
            preparedStatement.close();
            return objectReturned;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
