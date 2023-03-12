package me.imfighting.duels.managers;

import me.imfighting.duels.DuelsPlugin;
import me.imfighting.duels.util.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class LocationsManagers {

    static final ConfigUtil config = DuelsPlugin.getPlugin().getLocations();
    static final ConfigurationSection section = config.getConfigurationSection("Lobby");
    static final ConfigurationSection sectionNPC = config.getConfigurationSection("NPC");

    public static void setLobby(Player player) {
        DuelsPlugin.getPlugin().getLocations().set("Lobby.World", player.getLocation().getWorld().getName());
        DuelsPlugin.getPlugin().getLocations().set("Lobby.X", player.getLocation().getX());
        DuelsPlugin.getPlugin().getLocations().set("Lobby.Y", player.getLocation().getY());
        DuelsPlugin.getPlugin().getLocations().set("Lobby.Z", player.getLocation().getZ());
        DuelsPlugin.getPlugin().getLocations().set("Lobby.Yaw", player.getLocation().getYaw());
        DuelsPlugin.getPlugin().getLocations().set("Lobby.Pitch", player.getLocation().getPitch());
        DuelsPlugin.getPlugin().getLocations().save();
    }

    public static void setLobbyMinigame(Player player, String minigame) {
        if (minigame == "soup") {
            DuelsPlugin.getPlugin().getLocations().set("Soup.World", player.getLocation().getWorld().getName());
            DuelsPlugin.getPlugin().getLocations().set("Soup.X", player.getLocation().getX());
            DuelsPlugin.getPlugin().getLocations().set("Soup.Y", player.getLocation().getY());
            DuelsPlugin.getPlugin().getLocations().set("Soup.Z", player.getLocation().getZ());
            DuelsPlugin.getPlugin().getLocations().set("Soup.Yaw", player.getLocation().getYaw());
            DuelsPlugin.getPlugin().getLocations().set("Soup.Pitch", player.getLocation().getPitch());
            DuelsPlugin.getPlugin().getLocations().save();
        } else if (minigame == "gladiator") {
            DuelsPlugin.getPlugin().getLocations().set("Gladiator.World", player.getLocation().getWorld().getName());
            DuelsPlugin.getPlugin().getLocations().set("Gladiator.X", player.getLocation().getX());
            DuelsPlugin.getPlugin().getLocations().set("Gladiator.Y", player.getLocation().getY());
            DuelsPlugin.getPlugin().getLocations().set("Gladiator.Z", player.getLocation().getZ());
            DuelsPlugin.getPlugin().getLocations().set("Gladiator.Yaw", player.getLocation().getYaw());
            DuelsPlugin.getPlugin().getLocations().set("Gladiator.Pitch", player.getLocation().getPitch());
            DuelsPlugin.getPlugin().getLocations().save();
        }
    }

    public static Location getLobby(Player player) {
        return new Location(Bukkit.getWorld(section.getString("World")),
                section.getDouble("X"),
                section.getDouble("Y"),
                section.getDouble("Z"),
                (float) section.getDouble("Yaw"),
                (float) section.getDouble("Pitch"));
    }

    public static Location getLobbyMinigame(Player player, String minigame) {
        if (minigame == "soup") {
            return new Location(Bukkit.getWorld(config.getString("Soup.World")),
                    config.getDouble("Soup.X"),
                    config.getDouble("Soup.Y"),
                    config.getDouble("Soup.Z"),
                    (float) config.getDouble("Soup.Yaw"),
                    (float) config.getDouble("Soup.Pitch"));
        } else if (minigame == "gladiator") {
            return new Location(Bukkit.getWorld(config.getString("Gladiator.World")),
                    config.getDouble("Gladiator.X"),
                    config.getDouble("Gladiator.Y"),
                    config.getDouble("Gladiator.Z"),
                    (float) config.getDouble("Gladiator.Yaw"),
                    (float) config.getDouble("Gladiator.Pitch"));
        }

        return null;
    }

    public static void setNPC(Player player, String minigame) {
        if (minigame == "soup") {
            DuelsPlugin.getPlugin().getLocations().set("NPC.Soup.World", player.getLocation().getWorld().getName());
            DuelsPlugin.getPlugin().getLocations().set("NPC.Soup.X", player.getLocation().getX());
            DuelsPlugin.getPlugin().getLocations().set("NPC.Soup.Y", player.getLocation().getY());
            DuelsPlugin.getPlugin().getLocations().set("NPC.Soup.Z", player.getLocation().getZ());
            DuelsPlugin.getPlugin().getLocations().set("NPC.Soup.Yaw", player.getLocation().getYaw());
            DuelsPlugin.getPlugin().getLocations().set("NPC.Soup.Pitch", player.getLocation().getPitch());
            DuelsPlugin.getPlugin().getLocations().save();
        } else if (minigame == "sumo") {
            DuelsPlugin.getPlugin().getLocations().set("NPC.Sumo.World", player.getLocation().getWorld().getName());
            DuelsPlugin.getPlugin().getLocations().set("NPC.Sumo.X", player.getLocation().getX());
            DuelsPlugin.getPlugin().getLocations().set("NPC.Sumo.Y", player.getLocation().getY());
            DuelsPlugin.getPlugin().getLocations().set("NPC.Sumo.Z", player.getLocation().getZ());
            DuelsPlugin.getPlugin().getLocations().set("NPC.Sumo.Yaw", player.getLocation().getYaw());
            DuelsPlugin.getPlugin().getLocations().set("NPC.Sumo.Pitch", player.getLocation().getPitch());
            DuelsPlugin.getPlugin().getLocations().save();
        } else if (minigame == "gladiator") {
            DuelsPlugin.getPlugin().getLocations().set("NPC.Gladiator.World", player.getLocation().getWorld().getName());
            DuelsPlugin.getPlugin().getLocations().set("NPC.Gladiator.X", player.getLocation().getX());
            DuelsPlugin.getPlugin().getLocations().set("NPC.Gladiator.Y", player.getLocation().getY());
            DuelsPlugin.getPlugin().getLocations().set("NPC.Gladiator.Z", player.getLocation().getZ());
            DuelsPlugin.getPlugin().getLocations().set("NPC.Gladiator.Yaw", player.getLocation().getYaw());
            DuelsPlugin.getPlugin().getLocations().set("NPC.Gladiator.Pitch", player.getLocation().getPitch());
            DuelsPlugin.getPlugin().getLocations().save();
        } else if (minigame == "gapple") {
            DuelsPlugin.getPlugin().getLocations().set("NPC.Gapple.World", player.getLocation().getWorld().getName());
            DuelsPlugin.getPlugin().getLocations().set("NPC.Gapple.X", player.getLocation().getX());
            DuelsPlugin.getPlugin().getLocations().set("NPC.Gapple.Y", player.getLocation().getY());
            DuelsPlugin.getPlugin().getLocations().set("NPC.Gapple.Z", player.getLocation().getZ());
            DuelsPlugin.getPlugin().getLocations().set("NPC.Gapple.Yaw", player.getLocation().getYaw());
            DuelsPlugin.getPlugin().getLocations().set("NPC.Gapple.Pitch", player.getLocation().getPitch());
            DuelsPlugin.getPlugin().getLocations().save();
        }
    }

    public static Location getNPC(Player player, String minigame) {
        if (minigame == "soup") {
            return new Location(Bukkit.getWorld(sectionNPC.getString("Soup.World")),
                    sectionNPC.getDouble("Soup.X"),
                    sectionNPC.getDouble("Soup.Y"),
                    sectionNPC.getDouble("Soup.Z"),
                    (float) sectionNPC.getDouble("Soup.Yaw"),
                    (float) sectionNPC.getDouble("Soup.Pitch"));
        } else if (minigame == "sumo") {
            return new Location(Bukkit.getWorld(sectionNPC.getString("Sumo.World")),
                    sectionNPC.getDouble("Sumo.X"),
                    sectionNPC.getDouble("Sumo.Y"),
                    sectionNPC.getDouble("Sumo.Z"),
                    (float) sectionNPC.getDouble("Sumo.Yaw"),
                    (float) sectionNPC.getDouble("Sumo.Pitch"));
        } else if (minigame == "gladiator") {
            return new Location(Bukkit.getWorld(sectionNPC.getString("Gladiator.World")),
                    sectionNPC.getDouble("Gladiator.X"),
                    sectionNPC.getDouble("Gladiator.Y"),
                    sectionNPC.getDouble("Gladiator.Z"),
                    (float) sectionNPC.getDouble("Gladiator.Yaw"),
                    (float) sectionNPC.getDouble("Gladiator.Pitch"));
        } else if (minigame == "gapple") {
            return new Location(Bukkit.getWorld(sectionNPC.getString("Gapple.World")),
                    sectionNPC.getDouble("Gapple.X"),
                    sectionNPC.getDouble("Gapple.Y"),
                    sectionNPC.getDouble("Gapple.Z"),
                    (float) sectionNPC.getDouble("Gapple.Yaw"),
                    (float) sectionNPC.getDouble("Gapple.Pitch"));
        }
        return null;
    }
}
