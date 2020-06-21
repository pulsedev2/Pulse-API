package fr.pulsedev.api;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import fr.pulsedev.api.DataManagement.SqlManager.SQL;
import fr.pulsedev.api.Interfaces.CustomPlugin;
import fr.pulsedev.api.utils.Lang.Locate;
import fr.pulsedev.api.utils.NPC.NPC;
import fr.pulsedev.api.utils.NPC.NPCBuilder;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.apache.commons.codec.binary.Base64;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class Main{

    public SQL sql = new SQL("jdbc:mysql://", "sql-7.verygames.net", "3306", "db713240", "db713240", "5gpgdtpc5");
    public static Main INSTANCE;
    public org.bukkit.plugin.PluginManager PM;
    private Locate locate;

    public SQL getSql() {
        return sql;
    }

    public static Main getINSTANCE() {
        return INSTANCE;
    }

    public static ItemStack getSkull(String url) {
        ItemStack head = new ItemStack(Material.SKELETON_SKULL, 1);
        if(url.isEmpty())return head;
        url = "http://textures.minecraft.net/texture/" + url;

        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
        head.setItemMeta(headMeta);
        return head;
    }
}
