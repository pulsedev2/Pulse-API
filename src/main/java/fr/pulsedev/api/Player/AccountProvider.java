/*
 *
 *  AccountProvider is a part of primapi.
 *  Copyright (c) Niout - All rights reserved
 *
 *   @author Niout
 *   Created the 16/06/2020 19:21
 *   Last modified: 16/06/2020 19:21
 *
 */

package fr.pulsedev.api.Player;

import fr.pulsedev.api.DataManagement.RedisManager.RedisAccess;
import fr.pulsedev.api.DataManagement.SqlManager.SqlManager;
import fr.pulsedev.api.Interfaces.CustomPlugin;
import fr.pulsedev.api.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

public class AccountProvider {

    private RedisAccess redisAccess;
    private Player player;
    private String REDIS_KEY =  "account:";
    private CustomPlugin customPlugin;

    public AccountProvider(Player player, CustomPlugin customPlugin){
        this.redisAccess = RedisAccess.INSTANCE;
        this.player = player;
        this.customPlugin = customPlugin;
    }

    public ApiPlayer getAccount(){
        ApiPlayer apiPlayer = getPlayerFromDataBase();
        if(getPlayerFromRedis() != null){
            apiPlayer = getPlayerFromRedis();
        }

        return apiPlayer;
    }

    private ApiPlayer getPlayerFromRedis(){
        final RedissonClient redissonClient = redisAccess.getRedissonClient();
        final String key = REDIS_KEY + player.getName();
        final RBucket<ApiPlayer> accountRBucket = redissonClient.getBucket(key);

        return accountRBucket.get();
    }

    private ApiPlayer getPlayerFromDataBase(){
        final SqlManager sqlManager = new SqlManager(Main.INSTANCE.sql);

        return new ApiPlayer(Bukkit.getPlayer(sqlManager.getString("player", "uuid", player.getUniqueId().toString(), "name")), customPlugin);
    }


}

