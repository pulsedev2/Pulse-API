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
import net.minecraft.server.v1_15_R1.EntityPlayer;
import net.minecraft.server.v1_15_R1.MinecraftServer;
import net.minecraft.server.v1_15_R1.PlayerInteractManager;
import net.minecraft.server.v1_15_R1.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_15_R1.CraftServer;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.UUID;

public class NPC extends EntityPlayer {

    private EntityPlayer npc;
    private String name = "Default Name";
    private UUID uuid = UUID.fromString("668902fb-25a6-440a-80f3-f626aa9430fc");
    private GameProfile gameProfile = new GameProfile(UUID.fromString("668902fb-25a6-440a-80f3-f626aa9430fc"), "Default Name");
    private MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
    private WorldServer worldServer = ((CraftWorld) Objects.requireNonNull(Bukkit.getServer().getWorld(((CraftServer) Bukkit.getServer()).getServer().getWorld()))).getHandle();
    private PlayerInteractManager playerInteractManager = new PlayerInteractManager(((CraftWorld) Objects.requireNonNull(Bukkit.getServer().getWorld(((CraftServer) Bukkit.getServer()).getServer().getWorld()))).getHandle());
    private String skin;
    private String signature;

    public NPC(MinecraftServer minecraftserver, WorldServer worldserver, GameProfile gameprofile, PlayerInteractManager playerInteractManager) {
        super(minecraftserver, worldserver, gameprofile, playerInteractManager);
        this.npc = new EntityPlayer(minecraftserver, worldserver, gameprofile, playerInteractManager);
    }

    public NPC(){
        this(((CraftServer) Bukkit.getServer()).getServer(), ((CraftWorld) Objects.requireNonNull(Bukkit.getServer().getWorld(((CraftServer) Bukkit.getServer()).getServer().getWorld()))).getHandle(), new GameProfile(UUID.fromString("668902fb-25a6-440a-80f3-f626aa9430fc"), "Default Name"), new PlayerInteractManager(((CraftWorld) Objects.requireNonNull(Bukkit.getServer().getWorld(((CraftServer) Bukkit.getServer()).getServer().getWorld()))).getHandle()));
    }

    public NPC(GameProfile gameProfile){
        this(((CraftServer) Bukkit.getServer()).getServer(), ((CraftWorld) Objects.requireNonNull(Bukkit.getServer().getWorld(((CraftServer) Bukkit.getServer()).getServer().getWorld()))).getHandle(), gameProfile, new PlayerInteractManager(((CraftWorld) Objects.requireNonNull(Bukkit.getServer().getWorld(((CraftServer) Bukkit.getServer()).getServer().getWorld()))).getHandle()));
        this.gameProfile = gameProfile;
        this.name = gameProfile.getName();
        this.uuid = gameProfile.getId();
    }

    public NPC(String name){
        this(((CraftServer) Bukkit.getServer()).getServer(), ((CraftWorld) Objects.requireNonNull(Bukkit.getServer().getWorld(((CraftServer) Bukkit.getServer()).getServer().getWorld()))).getHandle(), new GameProfile(UUID.fromString("668902fb-25a6-440a-80f3-f626aa9430fc"), name), new PlayerInteractManager(((CraftWorld) Objects.requireNonNull(Bukkit.getServer().getWorld(((CraftServer) Bukkit.getServer()).getServer().getWorld()))).getHandle()));
        this.name = name;
    }

    public NPC(UUID uuid){
        this(((CraftServer) Bukkit.getServer()).getServer(), ((CraftWorld) Objects.requireNonNull(Bukkit.getServer().getWorld(((CraftServer) Bukkit.getServer()).getServer().getWorld()))).getHandle(), new GameProfile(uuid, "Default Name"), new PlayerInteractManager(((CraftWorld) Objects.requireNonNull(Bukkit.getServer().getWorld(((CraftServer) Bukkit.getServer()).getServer().getWorld()))).getHandle()));
        this.uuid = uuid;
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

    public String getName(){
        return name;
    }

    public UUID getUUID(){
        return uuid;
    }

    public GameProfile getGameProfile(){
        return gameProfile;
    }

    public EntityPlayer getNpc() {
        return npc;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Nullable
    @Override
    public MinecraftServer getMinecraftServer() {
        return minecraftServer;
    }

    @Override
    public WorldServer getWorldServer() {
        return worldServer;
    }

    public PlayerInteractManager getPlayerInteractManager() {
        return playerInteractManager;
    }

    public String getSkin(){
        return skin;
    }

    public String getSignature(){
        return signature;
    }
}
