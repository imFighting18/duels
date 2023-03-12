package me.imfighting.duels.managers;

import me.imfighting.duels.DuelsPlugin;
import me.imfighting.duels.MinigameType;
import me.imfighting.duels.instance.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {

    private List<Arena> arenasSoup = new ArrayList<>();
    private List<Arena> arenasGladiator = new ArrayList<>();
    private List<Arena> arenasSumo = new ArrayList<>();
    private List<Arena> arenasGapple = new ArrayList<>();

    public ArenaManager(DuelsPlugin minigames) {
        FileConfiguration config = minigames.getArenas();
        for (String str : config.getConfigurationSection("Soup.").getKeys(false)) {
            arenasSoup.add(
                    new Arena(
                            minigames,
                            Integer.parseInt(str),
                            new Location(
                                    Bukkit.getWorld(config.getString("Soup." + str + ".world")),
                                    config.getDouble("Soup." + str + ".x"),
                                    config.getDouble("Soup." + str + ".y"),
                                    config.getDouble("Soup." + str + ".z"),
                                    (float) config.getDouble("Soup." + str + ".yaw"),
                                    (float) config.getDouble("Soup." + str + ".pitch")
                            )));
        }

        for (String str : config.getConfigurationSection("Gladiator.").getKeys(false)) {
            arenasGladiator.add(
                    new Arena(
                            minigames,
                            Integer.parseInt(str),
                            new Location(
                                    Bukkit.getWorld(config.getString("Gladiator." + str + ".world")),
                                    config.getDouble("Gladiator." + str + ".x"),
                                    config.getDouble("Gladiator." + str + ".y"),
                                    config.getDouble("Gladiator." + str + ".z"),
                                    (float) config.getDouble("Gladiator." + str + ".yaw"),
                                    (float) config.getDouble("Gladiator." + str + ".pitch")
                            )));
        }

        for (String str : config.getConfigurationSection("Sumo.").getKeys(false)) {
            arenasSumo.add(
                    new Arena(
                            minigames,
                            Integer.parseInt(str),
                            new Location(
                                    Bukkit.getWorld(config.getString("Sumo." + str + ".world")),
                                    config.getDouble("Sumo." + str + ".x"),
                                    config.getDouble("Sumo." + str + ".y"),
                                    config.getDouble("Sumo." + str + ".z"),
                                    (float) config.getDouble("Sumo." + str + ".yaw"),
                                    (float) config.getDouble("Sumo." + str + ".pitch")
                            )));
        }

        for (String str : config.getConfigurationSection("Gapple.").getKeys(false)) {
            arenasGapple.add(
                    new Arena(
                            minigames,
                            Integer.parseInt(str),
                            new Location(
                                    Bukkit.getWorld(config.getString("Gapple." + str + ".world")),
                                    config.getDouble("Gapple." + str + ".x"),
                                    config.getDouble("Gapple." + str + ".y"),
                                    config.getDouble("Gapple." + str + ".z"),
                                    (float) config.getDouble("Gapple." + str + ".yaw"),
                                    (float) config.getDouble("Gapple." + str + ".pitch")
                            )));

        }
    }

    public List<Arena> getArenasSoup() {
        return arenasSoup;
    }

    public List<Arena> getArenasGladiator() {
        return arenasGladiator;
    }

    public List<Arena> getArenasSumo() {
        return arenasSumo;
    }

    public List<Arena> getArenasGapple() {
        return arenasGapple;
    }

    public Arena getArena(Player player, MinigameType minigameType) {
        if (minigameType == MinigameType.SOUP) {
            for (Arena arena : arenasSoup) {
                if (arena.getPlayers().contains(player.getUniqueId())) {
                    return arena;
                }
            }
        } else if (minigameType == MinigameType.GLADIATOR) {
            for (Arena arena : arenasGladiator) {
                if (arena.getPlayers().contains(player.getUniqueId())) {
                    return arena;
                }
            }
        } else if (minigameType == MinigameType.SUMO) {
            for (Arena arena : arenasSumo) {
                if (arena.getPlayers().contains(player.getUniqueId())) {
                    return arena;
                }
            }
        } else if (minigameType == MinigameType.GAPPLE) {
            for (Arena arena : arenasGapple) {
                if (arena.getPlayers().contains(player.getUniqueId())) {
                    return arena;
                }
            }
        }

        return null;
    }

    public Arena getArena(int id, MinigameType minigameType) {

        if (minigameType == MinigameType.SOUP) {
            for (Arena arena : arenasSoup) {
                if (arena.getId() == id) {
                    return arena;
                }
            }
        } else if (minigameType == MinigameType.GLADIATOR) {
            for (Arena arena : arenasGladiator) {
                if (arena.getId() == id) {
                    return arena;
                }
            }
        } else if (minigameType == MinigameType.SUMO) {
            for (Arena arena : arenasSumo) {
                if (arena.getId() == id) {
                    return arena;
                }
            }
        } else if (minigameType == MinigameType.GAPPLE) {
            for (Arena arena : arenasGapple) {
                if (arena.getId() == id) {
                    return arena;
                }
            }
        }
        return null;
    }

}
