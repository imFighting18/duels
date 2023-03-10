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
    }

    public List<Arena> getArenasSoup() {
        return arenasSoup;
    }

    public Arena getArena(Player player) {
        for (Arena arena : arenasSoup) {
            if (arena.getPlayers().contains(player.getUniqueId())) {
                return arena;
            }
        }
        return null;
    }

    public Arena getArena(int id) {
        for (Arena arena : arenasSoup) {
            if (arena.getId() == id) {
                return arena;
            }
        }
        return null;
    }

}
