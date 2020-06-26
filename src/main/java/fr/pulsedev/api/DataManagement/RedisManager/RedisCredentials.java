package fr.pulsedev.api.DataManagement.RedisManager;

/**
 * This file is a part of PulseAPI, located on fr.pulsedev.pulseapi.DataManagement.RedisManager
 * Copyright (c) Renard - All rights reserved
 *
 * @author Renard
 * Created the 19/06/2020 at 23:32.
 */
public class RedisCredentials {

    private final String ip;
    private final String password;
    private final int port;
    private final String clientName;

    public RedisCredentials(String ip, String password, int port, String clientName){
        this.ip = ip;
        this.password = password;
        this.port = port;
        this.clientName = clientName;
    }

    public String getIp() {
        return ip;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public String getClientName() {
        return clientName;
    }

    public String toRedisUrl(){
        return "redis://" + ip + " " + port;
    }

}
