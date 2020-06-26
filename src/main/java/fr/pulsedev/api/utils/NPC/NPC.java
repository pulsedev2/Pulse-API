/*
 *
 *  NPC is a part of PulseAPI.
 *  Copyright (c) PulseDev - All rights reserved
 *
 *   @author Niout
 *   Created the 20/06/2020 23:41
 *   Last modified: 20/06/2020 23:39
 */

package fr.pulsedev.api.utils.NPC;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import fr.pulsedev.api.Interfaces.CustomPlugin;
import fr.pulsedev.api.Player.ApiPlayer;
import net.minecraft.server.v1_15_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_15_R1.CraftServer;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.UUID;

public class NPC extends EntityPlayer{

    private final EntityPlayer npc;
    private String name = "Default Name";
    private UUID uuid = UUID.fromString("668902fb-25a6-440a-80f3-f626aa9430fc");
    private GameProfile gameProfile = new GameProfile(UUID.fromString("668902fb-25a6-440a-80f3-f626aa9430fc"), "Default Name");
    private MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
    private WorldServer worldServer = ((CraftWorld) Objects.requireNonNull(Bukkit.getServer().getWorld(((CraftServer) Bukkit.getServer()).getServer().getWorld()))).getHandle();
    private PlayerInteractManager playerInteractManager = new PlayerInteractManager(((CraftWorld) Objects.requireNonNull(Bukkit.getServer().getWorld(((CraftServer) Bukkit.getServer()).getServer().getWorld()))).getHandle());
    private String skin;
    private String signature;
    private final CustomPlugin<?> plugin;

    public NPC(MinecraftServer minecraftserver, WorldServer worldserver, GameProfile gameprofile, PlayerInteractManager playerInteractManager, CustomPlugin<?> plugin) {
        super(minecraftserver, worldserver, gameprofile, playerInteractManager);
        this.npc = new EntityPlayer(minecraftserver, worldserver, gameprofile, playerInteractManager);
        this.plugin = plugin;
    }

    public NPC(CustomPlugin<?> plugin){
        this(((CraftServer) Bukkit.getServer()).getServer(), ((CraftWorld) Objects.requireNonNull(Bukkit.getServer().getWorld(((CraftServer) Bukkit.getServer()).getServer().getWorld()))).getHandle(), new GameProfile(UUID.fromString("668902fb-25a6-440a-80f3-f626aa9430fc"), "Default Name"), new PlayerInteractManager(((CraftWorld) Objects.requireNonNull(Bukkit.getServer().getWorld(((CraftServer) Bukkit.getServer()).getServer().getWorld()))).getHandle()), plugin);
    }

    public NPC(GameProfile gameProfile, CustomPlugin<?> plugin){
        this(((CraftServer) Bukkit.getServer()).getServer(), ((CraftWorld) Objects.requireNonNull(Bukkit.getServer().getWorld(((CraftServer) Bukkit.getServer()).getServer().getWorld()))).getHandle(), gameProfile, new PlayerInteractManager(((CraftWorld) Objects.requireNonNull(Bukkit.getServer().getWorld(((CraftServer) Bukkit.getServer()).getServer().getWorld()))).getHandle()), plugin);
        this.gameProfile = gameProfile;
        this.name = gameProfile.getName();
        this.uuid = gameProfile.getId();
    }

    public NPC(String name, CustomPlugin<?> plugin){
        this(((CraftServer) Bukkit.getServer()).getServer(), ((CraftWorld) Objects.requireNonNull(Bukkit.getServer().getWorld(((CraftServer) Bukkit.getServer()).getServer().getWorld()))).getHandle(), new GameProfile(UUID.fromString("668902fb-25a6-440a-80f3-f626aa9430fc"), name), new PlayerInteractManager(((CraftWorld) Objects.requireNonNull(Bukkit.getServer().getWorld(((CraftServer) Bukkit.getServer()).getServer().getWorld()))).getHandle()), plugin);
        this.name = name;
    }

    public NPC(UUID uuid, CustomPlugin<?> plugin){
        this(((CraftServer) Bukkit.getServer()).getServer(), ((CraftWorld) Objects.requireNonNull(Bukkit.getServer().getWorld(((CraftServer) Bukkit.getServer()).getServer().getWorld()))).getHandle(), new GameProfile(uuid, "Default Name"), new PlayerInteractManager(((CraftWorld) Objects.requireNonNull(Bukkit.getServer().getWorld(((CraftServer) Bukkit.getServer()).getServer().getWorld()))).getHandle()), plugin);
        this.uuid = uuid;
    }

    public void spawn(ApiPlayer player){
        PlayerConnection connection = player.getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, this));
        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(this));
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin.getInstance(), () -> connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, getNPC())), 10L);

    }

    public void setGameProfile(GameProfile gameProfile){
        this.gameProfile = gameProfile;
        this.name = gameProfile.getName();
        this.uuid = gameProfile.getId();
    }

    public void setName(String name){
        this.name = name;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setMinecraftServer(MinecraftServer minecraftServer) {
        this.minecraftServer = minecraftServer;
    }

    public void setWorldServer(WorldServer worldServer) {
        this.worldServer = worldServer;
    }

    public void setPlayerInteractManager(PlayerInteractManager playerInteractManager) {
        this.playerInteractManager = playerInteractManager;
    }

    public void setSkin(String skin, String signature){
        this.skin = skin;
        this.signature = signature;
        this.gameProfile.getProperties().put("textures", new Property("textures", skin, signature));
    }
    public void setSkin(String skin){
        this.skin = skin;
        this.gameProfile.getProperties().put("textures", new Property("textures", skin, getSignature()));
    }

    public void setSignature(String signature){
        this.signature = signature;
        this.gameProfile.getProperties().put("textures", new Property("textures", getSkin(), signature));
    }


    @Nullable
    @Override
    public MinecraftServer getMinecraftServer() { return minecraftServer; }

    @Override
    public WorldServer getWorldServer() { return worldServer; }

    public String getName(){ return name; }
    public UUID getUUID(){ return uuid; }
    public GameProfile getGameProfile(){ return gameProfile; }
    public EntityPlayer getNpc() { return npc; }
    public PlayerInteractManager getPlayerInteractManager() { return playerInteractManager; }
    public String getSkin(){ return skin; }
    public String getSignature(){ return signature; }
    public NPC getNPC(){ return this; }
    public Player getEntityPlayer(){ return this.getBukkitEntity().getPlayer(); }
}
