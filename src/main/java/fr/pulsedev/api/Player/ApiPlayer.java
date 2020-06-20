/*
 *
 *  ApiPlayer is a part of primapi.
 *  Copyright (c) Niout - All rights reserved
 *
 *   @author Niout
 *   Created the 15/06/2020 17:01
 *   Last modified: 15/06/2020 16:15
 *
 */

package fr.pulsedev.api.Player;

import fr.pulsedev.api.DataManagement.SqlManager.SqlManager;
import fr.pulsedev.api.Interfaces.CustomPlugin;
import fr.pulsedev.api.Main;
import fr.pulsedev.api.utils.Inventory.Inventory;
import fr.pulsedev.api.utils.Inventory.InventoryManager;
import fr.pulsedev.api.utils.Lang.MessageConfiguration;
import org.bukkit.craftbukkit.v1_15_R1.CraftServer;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Objects;

public class ApiPlayer extends CraftPlayer {

    private Player player;
    private final SqlManager sqlManager = new SqlManager(Main.INSTANCE.sql);
    private CustomPlugin plugin;

    public ApiPlayer(Player player, CustomPlugin plugin){
        super((CraftServer) player.getServer(), ((CraftPlayer)player).getHandle());

        this.plugin = plugin;
        this.player = player;
    }

    public boolean isAlreadyIntoDataBase(){
        return !sqlManager.getString("player", "uuid", player.getUniqueId().toString(), "name").equals("");
    }

    public void createDataBasePlayer(){
        sqlManager.insert("player", new Object[]{"name","uuid","location"}, new Object[]{player.getName(), player.getUniqueId(), plugin.getInstance().getConfig().getString("default-player-location")});
    }

    public String getLocationUnlocalizedName(){
        return sqlManager.getString("player", "uuid", player.getUniqueId().toString(), "location");
    }

    public void openInventory(Inventory inventory){
        player.openInventory(InventoryManager.getBukkitInventory(Objects.requireNonNull(InventoryManager.getInventory(inventory.getPlugin(), inventory.getName()))));
    }

    public void sendMessage(MessageConfiguration messageConfiguration){
        String messageToSend = plugin.getLocate().getString(this.getLocationUnlocalizedName(), messageConfiguration.getId());
        for(String key : messageConfiguration.getListOfReplacement().keySet()){
            messageToSend = messageToSend.replace(key, messageConfiguration.getListOfReplacement().get(key));
        }

        player.sendMessage(messageToSend);
    }

}
