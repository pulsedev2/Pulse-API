package fr.pulsedev.api.DataManagement.RedisManager;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;

/**
 * This file is a part of PulseAPI, located on fr.pulsedev.pulseapi.DataManagement.RedisManager
 * Copyright (c) Renard - All rights reserved
 *
 * @author Renard
 * Created the 19/06/2020 at 23:31.
 */

public class RedisAccess {

    public static  RedisAccess INSTANCE;
    private RedissonClient redissonClient;
    private int threadNumber;
    private int nettyThreadsNumber;
    private int dateBaseNumber;

    public RedisAccess(RedisCredentials redisCredentials, int threadNumber, int nettyThreadsNumber, int dateBaseNumber){
        INSTANCE = this;
        this.redissonClient = initRedisson(redisCredentials);
    }

    public RedisAccess(RedisCredentials redisCredentials, int threadNumber,int dateBaseNumber){
        this(redisCredentials, threadNumber, threadNumber, dateBaseNumber);
    }

    public RedisAccess(RedisCredentials redisCredentials, int threadNumber){
        this(redisCredentials, threadNumber, threadNumber, 0);
    }

    public RedisAccess(RedisCredentials redisCredentials){
        this(redisCredentials, 0, 0, 0);
    }


    public static void init(RedisCredentials redisCredentials, int threadNumber, int nettyThreadsNumber, int dateBaseNumber){
        new RedisAccess(redisCredentials, threadNumber, nettyThreadsNumber, dateBaseNumber);
    }
    public static void init(RedisCredentials redisCredentials, int threadNumber, int dateBaseNumber){
        new RedisAccess(redisCredentials, threadNumber, dateBaseNumber);
    }
    public static void init(RedisCredentials redisCredentials, int threadNumber){
        new RedisAccess(redisCredentials, threadNumber);
    }
    public static void init(RedisCredentials redisCredentials){
        new RedisAccess(redisCredentials);
    }


    public void setThreadNumber(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    public void setNettyThreadsNumber(int nettyThreadsNumber) {
        this.nettyThreadsNumber = nettyThreadsNumber;
    }

    public void setDateBaseNumber(int dateBaseNumber) {
        this.dateBaseNumber = dateBaseNumber;
    }

    public static void close(){
        RedisAccess.INSTANCE.getRedissonClient().shutdown();
    }

    public RedissonClient initRedisson(RedisCredentials redisCredentials){
        final Config config = new Config();

        config.setCodec(new JsonJacksonCodec());
        config.setThreads(getThreadNumber());
        config.setNettyThreads(getNettyThreadsNumber());
        config.useSingleServer()
                .setAddress(redisCredentials.toRedisUrl())
                .setPassword(redisCredentials.getPassword())
                .setDatabase(getDateBaseNumber())
                .setClientName(redisCredentials.getClientName());

        return Redisson.create(config);
    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

    public int getThreadNumber() {
        return threadNumber;
    }

    public int getNettyThreadsNumber() {
        return nettyThreadsNumber;
    }

    public int getDateBaseNumber() {
        return dateBaseNumber;
    }
}
