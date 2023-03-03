package me.imfighting.duels;

import me.imfighting.duels.commands.SetLobbyCommand;
import me.imfighting.duels.commands.SetNPCCommand;
import me.imfighting.duels.listeners.LoadListeners;
import me.imfighting.duels.managers.NPCManager;
import me.imfighting.duels.util.ConfigUtil;
import me.saiintbrisson.minecraft.ViewFrame;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.channels.Channel;

public final class DuelsPlugin extends JavaPlugin {

    private static DuelsPlugin plugin;
    private ConfigUtil config;
    private ConfigUtil locations;
    private ViewFrame view;
    private NPCManager npcManager;

    private final boolean USE_REFLECTION = false;

    @Override
    public void onLoad() {
        config = new ConfigUtil(this, "config.yml");
        locations = new ConfigUtil(this, "locations.yml");
    }

    @Override
    public void onEnable() {
        plugin = this;

        this.npcManager = new NPCManager(this, USE_REFLECTION);

        Bukkit.getPluginManager().registerEvents(new LoadListeners(), this);

        getCommand("setlobby").setExecutor(new SetLobbyCommand());
        getCommand("setnpc").setExecutor(new SetNPCCommand());


    }

    @Override
    public void onDisable() {
        npcManager.deleteAllNPCs();

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

    public NPCManager getNpcManager() {
        return npcManager;
    }
}
