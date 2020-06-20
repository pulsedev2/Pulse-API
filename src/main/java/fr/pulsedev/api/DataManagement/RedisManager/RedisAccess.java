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

    public static void init(){
        new RedisAccess(new RedisCredentials("127.0.0.1", "", 6379), 0, 0, 0);
    }

    public static void close(){
        RedisAccess.INSTANCE.getRedissonClient().shutdown();
    }

    public RedissonClient initRedisson(RedisCredentials redisCredentials){
        final Config config = new Config();

        config.setCodec(new JsonJacksonCodec());
        config.setThreads(4);
        config.setNettyThreads(4);
        config.useSingleServer()
                .setAddress(redisCredentials.toRedisUrl())
                .setPassword(redisCredentials.getPassword())
                .setDatabase(3)
                .setClientName(redisCredentials.getClientName());

        return Redisson.create(config);
    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }
}
