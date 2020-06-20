/*
 *
 *  Inventory is a part of primapi.
 *  Copyright (c) Niout - All rights reserved
 *
 *   @author Niout
 *   Created the 16/06/2020 21:13
 *   Last modified: 16/06/2020 21:13
 *
 */

package fr.pulsedev.api.utils.Inventory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class InventoryManager {

    public static void createInventory(Inventory inventory){
        File folder = new File(inventory.getPlugin().getDataFolder().getPath() + File.separator + "inventories");
        if(!folder.exists()){
            folder.mkdir();
        }

        File file = new File(folder.getPath() + File.separator + inventory.getName() + ".json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter writer = new FileWriter(file)) {
            gson.toJson(inventory,writer);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static Inventory getInventory(Plugin plugin, String name){
        try(FileReader reader = new FileReader(plugin.getDataFolder().getPath() + File.separator + "inventories" + File.separator + name+ ".json")){
            Gson gson = new Gson();
            return gson.fromJson(reader, Inventory.class);
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return null;
    }

    public static org.bukkit.inventory.Inventory getBukkitInventory(Inventory inventory){
        org.bukkit.inventory.Inventory bukkitInventory = Bukkit.createInventory(null, inventory.getSize(), inventory.getName());

        for(Integer key : inventory.getItemStacks().keySet()){
            bukkitInventory.setItem(key, inventory.itemStackFromJson(inventory.getItemStacks().get(key)));
        }

        return bukkitInventory;
    }
}
