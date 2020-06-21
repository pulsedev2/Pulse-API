package fr.pulsedev.api.utils.NPC;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_15_R1.MinecraftServer;
import net.minecraft.server.v1_15_R1.PlayerInteractManager;
import net.minecraft.server.v1_15_R1.WorldServer;

import java.util.UUID;

/**
 * This file is a part of Pulse-API, located on fr.pulsedev.api.utils.NPC
 * Copyright (c) Niout - All rights reserved
 *
 * @author Niout
 * Created the 21/06/2020 at 17:31.
 */
public class NPCBuilder {

    private NPC npc;
    private NPCBuilder npcBuilder;

    public NPCBuilder(){
        this.npc = new NPC();
    }

    public NPCBuilder setGameProfile(GameProfile gameProfile){
        npc.setGameProfile(gameProfile);
        return npcBuilder;
    }

    public NPCBuilder setName(String name){
        npc.setName(name);
        return npcBuilder;
    }

    public NPCBuilder setUuid(UUID uuid) {
        npc.setUuid(uuid);
        return npcBuilder;
    }

    public NPCBuilder setMinecraftServer(MinecraftServer minecraftServer) {
        npc.setMinecraftServer(minecraftServer);
        return npcBuilder;
    }

    public NPCBuilder setWorldServer(WorldServer worldServer) {
        npc.setWorldServer(worldServer);
        return npcBuilder;
    }

    public NPCBuilder setPlayerInteractManager(PlayerInteractManager playerInteractManager) {
        npc.setPlayerInteractManager(playerInteractManager);
        return npcBuilder;
    }

    public NPCBuilder setSkin(String skin, String signature){
        npc.setSkin(skin, signature);
        return npcBuilder;
    }
    public NPCBuilder setSkin(String skin){
        npc.setSkin(skin);
        return npcBuilder;
    }

    public NPCBuilder setSignature(String signature){
        npc.setSignature(signature);
        return npcBuilder;
    }

    public NPC build(){
        return npc;
    }

}
