/*
 *
 *  Inventory is a part of primapi.
 *  Copyright (c) Niout - All rights reserved
 *
 *   @author Niout
 *   Created the 17/06/2020 15:23
 *   Last modified: 17/06/2020 15:23
 *
 */

package fr.pulsedev.api.utils.Inventory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class Inventory {

    private int size;
    private String name;
    private transient Plugin plugin;
    private HashMap<Integer, String> itemStacks = new HashMap<>();

    public Inventory(int size, String name, Plugin plugin){
        this.size = size;
        this.name = name;
        this.plugin = plugin;
    }

    public void setItem(int slot, ItemStack itemStack){
        itemStacks.put(slot, itemStackToJson(itemStack));
    }

    public int getSize() {
        return size;
    }
    public String getName() {
        return name;
    }
    public Plugin getPlugin() {
        return plugin;
    }
    public HashMap<Integer, String> getItemStacks() {
        return itemStacks;
    }

    public ItemStack itemStackFromJson(String id){
        Gson gson = new Gson();
        return gson.fromJson(id, ItemStack.class);
    }

    public String itemStackToJson(ItemStack itemStack){
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("type", itemStack.getType().name());
        jsonObject.addProperty("amount", itemStack.getAmount());
        if(itemStack.getDurability() > 0) jsonObject.addProperty("durability", itemStack.getDurability());

        if(itemStack.hasItemMeta()){
            ItemMeta itemMeta = itemStack.getItemMeta();
            JsonObject jsonMeta = new JsonObject();

            if(itemMeta.hasCustomModelData()) jsonMeta.addProperty("custom-model-data",itemMeta.getCustomModelData());
            if(itemMeta.hasDisplayName()) jsonMeta.addProperty("displayname", itemMeta.getDisplayName());
            if(itemMeta.hasLocalizedName()) jsonMeta.addProperty("localized-name", itemMeta.getLocalizedName());
            if(itemMeta.isUnbreakable()) jsonMeta.addProperty("unbreakable", true);
            if(itemMeta.hasLore()){
                JsonArray jsonLore = new JsonArray();
                itemMeta.getLore().forEach(string -> jsonLore.add(new JsonPrimitive(string)));
                jsonMeta.add("lore", jsonLore);
            }

            if(itemMeta.hasEnchants()){
                JsonArray jsonEnchant = new JsonArray();
                itemMeta.getEnchants().forEach((enchant, integer) -> jsonEnchant.add(new JsonPrimitive(enchant.getName() + ":" + integer)));
                jsonMeta.add("enchants", jsonEnchant);
            }

            if(!itemMeta.getItemFlags().isEmpty()){
                JsonArray jsonFlags = new JsonArray();
                itemMeta.getItemFlags().stream().map(ItemFlag::name).forEach(str -> jsonFlags.add(new JsonPrimitive(str)));
                jsonMeta.add("flags", jsonFlags);
            }

            jsonObject.add("item-meta", jsonMeta);
        }
        return gson.toJson(jsonObject);
    }

}
