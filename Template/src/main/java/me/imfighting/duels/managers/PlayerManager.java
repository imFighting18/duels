package me.imfighting.duels.managers;

import me.imfighting.duels.DuelsPlugin;
import me.imfighting.duels.MinigameType;
import me.imfighting.duels.database.SQLConnection;
import me.imfighting.duels.listeners.LoadListeners;
import me.imfighting.duels.npc.NPCOptions;
import me.imfighting.duels.npc.NPCs;
import me.imfighting.duels.util.ConfigUtil;
import me.imfighting.duels.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerManager {

    static final ConfigUtil config = DuelsPlugin.getPlugin().getConfig();
    final static ConfigurationSection desafiar = config.getConfigurationSection("item-desafiar");

    static final ConfigurationSection sectionSkins = config.getConfigurationSection("Skins");

    final static ItemBuilder builder = new ItemBuilder(desafiar.getString("material"))
            .setName(desafiar.getString("name").replace('&', '§'))
            .setDurability((short) desafiar.getInt("data"));

    public static void updatePlayer(Player player) {
        player.getInventory().clear();

        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);

        player.getInventory().setItem(desafiar.getInt("slot"), builder.build());
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.SURVIVAL);

        if (SQLConnection.containsNPC("soup")) {
            NPCs npc = DuelsPlugin.getPlugin().getNpcManager().newNPC(NPCOptions.builder()
                    .name("§bSopa")
                    .hideNametag(false)
                    .texture(sectionSkins.getString("Sopa.texture"))
                    .signature(sectionSkins.getString("Sopa.signature"))
                    .location(new Location(
                            Bukkit.getWorld(SQLConnection.getLocationNPCWorld("soup")),
                            SQLConnection.getLocationNPC("soup", "x"),
                            SQLConnection.getLocationNPC("soup", "y"),
                            SQLConnection.getLocationNPC("soup", "z")
                    ))
                    .build()
            );
            npc.showTo(player);
        }

        if (SQLConnection.containsNPC("sumo")) {
            NPCs npc = DuelsPlugin.getPlugin().getNpcManager().newNPC(NPCOptions.builder()
                    .name("§bSumo")
                    .hideNametag(false)
                    .texture(sectionSkins.getString("Sumo.texture"))
                    .signature(sectionSkins.getString("Sumo.signature"))
                    .location(new Location(
                            Bukkit.getWorld(SQLConnection.getLocationNPCWorld("sumo")),
                            SQLConnection.getLocationNPC("sumo", "x"),
                            SQLConnection.getLocationNPC("sumo", "y"),
                            SQLConnection.getLocationNPC("sumo", "z")
                    ))
                    .build()
            );
            npc.showTo(player);
        }

        if (SQLConnection.containsNPC("gladiator")) {
            NPCs npc = DuelsPlugin.getPlugin().getNpcManager().newNPC(NPCOptions.builder()
                    .name("§bGladiator")
                    .hideNametag(false)
                    .texture(sectionSkins.getString("Gladiator.texture"))
                    .signature(sectionSkins.getString("Gladiator.signature"))
                    .location(new Location(
                            Bukkit.getWorld(SQLConnection.getLocationNPCWorld("gladiator")),
                            SQLConnection.getLocationNPC("gladiator", "x"),
                            SQLConnection.getLocationNPC("gladiator", "y"),
                            SQLConnection.getLocationNPC("gladiator", "z")
                    ))
                    .build()
            );
            npc.showTo(player);
        }

        if (SQLConnection.containsNPC("gapple")) {
            NPCs npc = DuelsPlugin.getPlugin().getNpcManager().newNPC(NPCOptions.builder()
                    .name("§bGapple")
                    .hideNametag(false)
                    .texture(sectionSkins.getString("Gapple.texture"))
                    .signature(sectionSkins.getString("Gapple.signature"))
                    .location(new Location(
                            Bukkit.getWorld(SQLConnection.getLocationNPCWorld("gapple")),
                            SQLConnection.getLocationNPC("gapple", "x"),
                            SQLConnection.getLocationNPC("gapple", "y"),
                            SQLConnection.getLocationNPC("gapple", "z")
                    ))
                    .build()
            );
            npc.showTo(player);
        }

        if (SQLConnection.containsNPCPlay("soup")) {
            NPCs npc = DuelsPlugin.getPlugin().getNpcManager().newNPC(NPCOptions.builder()
                    .name("§bSopa 1v1")
                    .hideNametag(false)
                    .texture(sectionSkins.getString("Soup-Lobby.texture"))
                    .signature(sectionSkins.getString("Soup-Lobby.signature"))
                    .location(new Location(
                            Bukkit.getWorld(SQLConnection.getLocationNPCPlayWorld("soup")),
                            SQLConnection.getLocationNPCPlay("soup", "x"),
                            SQLConnection.getLocationNPCPlay("soup", "y"),
                            SQLConnection.getLocationNPCPlay("soup", "z")
                    ))
                    .build()
            );
            npc.showTo(player);
        }

        player.teleport(LocationsManagers.getLobby(player));
        ScoreboardManager.updateScoreboard(player);
    }

    public static void updatePlayerOnReset(Player player) {
        player.getInventory().clear();

        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);

        player.getInventory().setItem(desafiar.getInt("slot"), builder.build());
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.SURVIVAL);

        if (DuelsPlugin.getPlugin().getArenaManager().getArena(player, MinigameType.SOUP).getMinigameType() == MinigameType.SOUP) {
            if (SQLConnection.containsNPCPlay("soup")) {
                NPCs npc = DuelsPlugin.getPlugin().getNpcManager().newNPC(NPCOptions.builder()
                        .name("§bSopa 1v1")
                        .hideNametag(false)
                        .texture(sectionSkins.getString("Soup-Lobby.texture"))
                        .signature(sectionSkins.getString("Soup-Lobby.signature"))
                        .location(new Location(
                                Bukkit.getWorld(SQLConnection.getLocationNPCPlayWorld("soup")),
                                SQLConnection.getLocationNPCPlay("soup", "x"),
                                SQLConnection.getLocationNPCPlay("soup", "y"),
                                SQLConnection.getLocationNPCPlay("soup", "z")
                        ))
                        .build()
                );
                npc.showTo(player);
            }

            LoadListeners.joinSoupLobby(player);
            ScoreboardManager.updateScoreboardLobbySoup(player);

        }
    }
}
