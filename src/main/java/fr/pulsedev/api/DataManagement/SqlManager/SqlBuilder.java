package fr.pulsedev.api.DataManagement.SqlManager;

/**
 * This file is a part of PulseAPI, located on fr.pulsedev.pulseapi.DataManagement.SqlManager
 * Copyright (c) Renard - All rights reserved
 *
 * @author Renard
 * Created the 19/06/2020 at 23:31.
 */
public class SqlBuilder {

    private String request = "CREATE TABLE IF NOT EXISTS ";
    private String tableName;

    public SqlBuilder(String tableName){
        this.request += tableName;
        this.tableName = tableName;
        this.request += " (";
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public SqlBuilder addColumn(String columnName, String type, String defaultValue, String... optionalOptions){
        SqlBuilder sqlBuilder = new SqlBuilder(this.tableName);
        StringBuilder newRequest = new StringBuilder(this.request);
        if(newRequest.toString().endsWith(")")) newRequest = new StringBuilder(newRequest.toString().substring(0, newRequest.toString().length() - 1) + ",");
        newRequest.append(columnName).append(" ").append(type).append(" ").append(defaultValue);
        for(String optionalOption: optionalOptions){
            newRequest.append(" ").append(optionalOption);
        }
        newRequest.append(")");
        sqlBuilder.setRequest(newRequest.toString());
        return sqlBuilder;
    }

    public void init(SQL sql){
        sql.request(this.getRequest());
    }

}
