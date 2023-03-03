package me.imfighting.duels.managers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import me.imfighting.duels.nms.NMSHelper;
import me.imfighting.duels.nms.ServerVersion;
import me.imfighting.duels.npc.*;
import net.minecraft.server.v1_8_R3.NPC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class NPCManager {
    private final JavaPlugin plugin;
    private final boolean useReflection;

    private final Set<NPCs> registeredNPCs = new HashSet<>();

    public NPCManager(JavaPlugin plugin, boolean useReflection) {
        this.plugin = plugin;
        this.useReflection = useReflection;

        ProtocolLibrary.getProtocolManager().addPacketListener(
                new PacketAdapter(plugin, PacketType.Play.Client.USE_ENTITY) {
                    @Override
                    public void onPacketReceiving(PacketEvent event) {
                        EnumWrappers.EntityUseAction useAction = event.getPacket().getEntityUseActions().read(0);
                        int entityId = event.getPacket().getIntegers().read(0);
                        handleEntityClick(event.getPlayer(), entityId, NPCClickAction.fromProtocolLibAction(useAction));
                    }
                }
        );
    }

    private final Cache<Player, NPCs> clickedNPCCache = CacheBuilder.newBuilder()
            .expireAfterWrite(1L, TimeUnit.SECONDS)
            .build();

    private void handleEntityClick(Player player, int entityId, NPCClickAction action) {
        registeredNPCs.stream()
                .filter(npc -> npc.getId() == entityId)
                .forEach(npc -> Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> {
                    NPCs previouslyClickedNPC = clickedNPCCache.getIfPresent(player);
                    if (previouslyClickedNPC != null && previouslyClickedNPC.equals(npc)) return; // If they've clicked this same NPC in the last 0.5 seconds ignore this click
                    clickedNPCCache.put(player, npc);

                    NPCInteractionEvent event = new NPCInteractionEvent(npc, player, action);
                    Bukkit.getPluginManager().callEvent(event);
                }, 2));
    }

    public NPCs newNPC(NPCOptions options) {
        ServerVersion serverVersion = NMSHelper.getInstance().getServerVersion();
        NPCs npc = null;

        if (useReflection) {
            serverVersion = ServerVersion.REFLECTED;
        }

        switch (serverVersion) {
            case REFLECTED:
                npc = new NPC_Reflection(plugin, options);
                break;
            case v1_8_R3:
                npc = new NPC_v1_8_R3(plugin, options);
                break;
        }

        if (npc == null) {
            throw new IllegalStateException("Invalid server version " + serverVersion + ". This plugin needs to be updated!");
        }

        registeredNPCs.add(npc);
        return npc;
    }

    public Optional<NPCs> findNPC(String name) {
        return registeredNPCs.stream()
                .filter(npc -> npc.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public void deleteNPC(NPCs npc) {
        npc.delete();
        registeredNPCs.remove(npc);
    }

    public void deleteAllNPCs() {
        // Copy the set to prevent concurrent modification exception
        Set<NPCs> npcsCopy = new HashSet<>(registeredNPCs);
        npcsCopy.forEach(this::deleteNPC);
    }

}
