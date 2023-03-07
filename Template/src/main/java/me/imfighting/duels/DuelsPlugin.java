package me.imfighting.duels;

import me.imfighting.duels.commands.SetArenaCommand;
import me.imfighting.duels.commands.SetLobbyCommand;
import me.imfighting.duels.commands.SetNPCCommand;
import me.imfighting.duels.commands.SetNPCGameCommand;
import me.imfighting.duels.database.SQLConnection;
import me.imfighting.duels.listeners.LoadListeners;
import me.imfighting.duels.managers.NPCManager;
import me.imfighting.duels.npc.NPCOptions;
import me.imfighting.duels.npc.NPCs;
import me.imfighting.duels.util.ConfigUtil;
import me.saiintbrisson.minecraft.ViewFrame;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.nio.channels.Channel;

public final class DuelsPlugin extends JavaPlugin {

    private static DuelsPlugin plugin;
    private ConfigUtil config;
    private ConfigUtil locations;
    private ConfigUtil arenas;

    private ViewFrame view;
    private NPCManager npcManager;

    private final boolean USE_REFLECTION = false;

    @Override
    public void onLoad() {
        config = new ConfigUtil(this, "config.yml");
        locations = new ConfigUtil(this, "locations.yml");
        arenas = new ConfigUtil(this, "arenas.yml");
    }

    @Override
    public void onEnable() {
        plugin = this;

        this.npcManager = new NPCManager(this, USE_REFLECTION);

        SQLConnection.openConnection();

        Bukkit.getPluginManager().registerEvents(new LoadListeners(), this);

        if(!Bukkit.getPluginManager().isPluginEnabled("ProtocolLib")){
            getLogger().warning("Dependencia ProtocolLib nao encontrada.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        getCommand("setlobby").setExecutor(new SetLobbyCommand());
        getCommand("setnpc").setExecutor(new SetNPCCommand());
        getCommand("setnpcgame").setExecutor(new SetNPCGameCommand());
        getCommand("setarena").setExecutor(new SetArenaCommand());

        removeMobs();
        alwaysDay();


    }

    @Override
    public void onDisable() {
        npcManager.deleteAllNPCs();
        SQLConnection.closeConnection();
    }

    private void removeMobs() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    for (Entity entity : world.getLivingEntities()) {
                        if (!(entity instanceof Player)) {
                            entity.remove();
                        }
                    }
                }
            }
        }.runTaskTimer(this, 0, 1L);
    }

    private void alwaysDay() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    world.setTime(0);
                }
            }
        }.runTaskTimer(this, 0, 1L);
    }

    public static DuelsPlugin getPlugin() {
        return plugin;
    }

    @Override
    public ConfigUtil getConfig() {
        return config;
    }

    public ConfigUtil getLocations() {
        return locations;
    }

    public ConfigUtil getArenas() {
        return arenas;
    }

    public NPCManager getNpcManager() {
        return npcManager;
    }
}
