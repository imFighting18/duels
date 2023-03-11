package me.imfighting.duels.listeners;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import me.imfighting.duels.DuelsPlugin;
import me.imfighting.duels.GameState;
import me.imfighting.duels.MinigameType;
import me.imfighting.duels.database.SQLConnection;
import me.imfighting.duels.instance.Arena;
import me.imfighting.duels.managers.*;
import me.imfighting.duels.npc.NPCClickAction;
import me.imfighting.duels.npc.NPCInteractionEvent;
import me.imfighting.duels.npc.NPCOptions;
import me.imfighting.duels.npc.NPCs;
import me.imfighting.duels.util.ActionBar;
import me.imfighting.duels.util.ConfigUtil;
import me.imfighting.duels.util.ItemBuilder;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.NPC;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoadListeners implements Listener {

    final ConfigUtil config = DuelsPlugin.getPlugin().getConfig();
    static final ConfigUtil locations = DuelsPlugin.getPlugin().getLocations();
    final ConfigurationSection section = config.getConfigurationSection("item-desafiar");
    static final ConfigurationSection sectionLobbySoup = locations.getConfigurationSection("Soup");

    static final ConfigurationSection sectionLobbyGladiator = locations.getConfigurationSection("Gladiator");

    final ConfigurationSection sectionSkins = config.getConfigurationSection("Skins");

    final ConfigurationSection desafiar = config.getConfigurationSection("item-desafiar");

    final ItemBuilder builder = new ItemBuilder(desafiar.getString("material"))
            .setName(desafiar.getString("name").replace('&', '§'))
            .setDurability((short) desafiar.getInt("data"));

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);

        Player player = e.getPlayer();

        PlayerManager.updatePlayer(player);

        if (!SQLConnection.containsPlayer(player, MinigameType.SOUP)) {
            SQLConnection.createPlayer(player, MinigameType.SOUP);
        }

        if (!SQLConnection.containsPlayer(player, MinigameType.GLADIATOR)) {
            SQLConnection.createPlayer(player, MinigameType.GLADIATOR);
        }

        if (!SQLConnection.containsPlayer(player, MinigameType.BRIDGE)) {
            SQLConnection.createPlayer(player, MinigameType.BRIDGE);
        }

        if (!SQLConnection.containsPlayer(player, MinigameType.GAPPLE)) {
            SQLConnection.createPlayer(player, MinigameType.GAPPLE);
        }

        for (Player online : Bukkit.getOnlinePlayers()) {
            if (!DuelsPlugin.getPlugin().getArenaManager().getArena(online, MinigameType.SOUP).getPlayers().contains(online)) {
                player.hidePlayer(online);
                online.hidePlayer(player);
            }
        }

        for (Player online : Bukkit.getOnlinePlayers()) {
            if (!DuelsPlugin.getPlugin().getArenaManager().getArena(online, MinigameType.GLADIATOR).getPlayers().contains(online)) {
                player.hidePlayer(online);
                online.hidePlayer(player);
            }
        }

        for (Player online : Bukkit.getOnlinePlayers()) {
            if (!DuelsPlugin.getPlugin().getArenaManager().getArena(online, MinigameType.BRIDGE).getPlayers().contains(online)) {
                player.hidePlayer(online);
                online.hidePlayer(player);
            }
        }

        for (Player online : Bukkit.getOnlinePlayers()) {
            if (!DuelsPlugin.getPlugin().getArenaManager().getArena(online, MinigameType.GAPPLE).getPlayers().contains(online)) {
                player.hidePlayer(online);
                online.hidePlayer(player);
            }
        }


    }

    @EventHandler
    public void onWorldLoad(PlayerChangedWorldEvent event) {

        Player player = event.getPlayer();

        if (SQLConnection.containsNPCPlay("soup") &&
                player.getWorld().getName().equalsIgnoreCase(SQLConnection.getLocationNPCPlayWorld("soup"))) {
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

        if (SQLConnection.containsNPCPlay("gladiator") &&
                player.getWorld().getName().equalsIgnoreCase(SQLConnection.getLocationNPCPlayWorld("gladiator"))) {
            NPCs npc = DuelsPlugin.getPlugin().getNpcManager().newNPC(NPCOptions.builder()
                    .name("§bGladiator 1v1")
                    .hideNametag(false)
                    .texture(sectionSkins.getString("Gladiator-Lobby.texture"))
                    .signature(sectionSkins.getString("Gladiator-Lobby.signature"))
                    .location(new Location(
                            Bukkit.getWorld(SQLConnection.getLocationNPCPlayWorld("gladiator")),
                            SQLConnection.getLocationNPCPlay("gladiator", "x"),
                            SQLConnection.getLocationNPCPlay("gladiator", "y"),
                            SQLConnection.getLocationNPCPlay("gladiator", "z")
                    ))
                    .build()
            );
            npc.showTo(player);
        }

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

        if (SQLConnection.containsNPC("bridge")) {
            NPCs npc = DuelsPlugin.getPlugin().getNpcManager().newNPC(NPCOptions.builder()
                    .name("§bThe bridge")
                    .hideNametag(false)
                    .texture(sectionSkins.getString("Bridge.texture"))
                    .signature(sectionSkins.getString("Bridge.signature"))
                    .location(new Location(
                            Bukkit.getWorld(SQLConnection.getLocationNPCWorld("bridge")),
                            SQLConnection.getLocationNPC("bridge", "x"),
                            SQLConnection.getLocationNPC("bridge", "y"),
                            SQLConnection.getLocationNPC("bridge", "z")
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
                    .name("§a§lSopa 1v1")
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

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);

        Arena arenaSoup = DuelsPlugin.getPlugin().getArenaManager().getArena(e.getPlayer(), MinigameType.SOUP);
        if (arenaSoup != null) {
            arenaSoup.removePlayer(e.getPlayer());
        }

        Arena arenaGladiator = DuelsPlugin.getPlugin().getArenaManager().getArena(e.getPlayer(), MinigameType.GLADIATOR);
        if (arenaGladiator != null) {
            arenaGladiator.removePlayer(e.getPlayer());
        }

        Arena arenaBridge = DuelsPlugin.getPlugin().getArenaManager().getArena(e.getPlayer(), MinigameType.BRIDGE);
        if (arenaBridge != null) {
            arenaBridge.removePlayer(e.getPlayer());
        }

        Arena arenaGapple = DuelsPlugin.getPlugin().getArenaManager().getArena(e.getPlayer(), MinigameType.GAPPLE);
        if (arenaGapple != null) {
            arenaGapple.removePlayer(e.getPlayer());
        }

    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e) {

        ItemStack item = e.getItemDrop().getItemStack();
        if (item.getType() == Material.BOWL || item.getType() == Material.MUSHROOM_SOUP) {

            new BukkitRunnable() {
                @Override
                public void run() {
                    e.getItemDrop().remove();
                }
            }.runTaskLater(DuelsPlugin.getPlugin(), 20);

        }

        ItemStack itemDrop = e.getItemDrop().getItemStack();
        if (itemDrop.getItemMeta().getDisplayName().equals(section.getString("name")
                .replace('&', '§'))) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if ((event.getEntity() instanceof Player)) {


            List<ItemStack> drops = event.getDrops();
            for (ItemStack drop : drops) {
                drop.setType(Material.AIR);
            }
            drops.clear();


            Player player = event.getEntity();
            new BukkitRunnable() {
                @Override
                public void run() {
                    DuelsPlugin.getPlugin().getArenaManager().getArena(player, MinigameType.SOUP).reset(true);
                }
            }.runTaskLater(DuelsPlugin.getPlugin(), 1L);

            new BukkitRunnable() {
                @Override
                public void run() {
                    DuelsPlugin.getPlugin().getArenaManager().getArena(player, MinigameType.GLADIATOR).reset(true);
                }
            }.runTaskLater(DuelsPlugin.getPlugin(), 1L);

            new BukkitRunnable() {
                @Override
                public void run() {
                    DuelsPlugin.getPlugin().getArenaManager().getArena(player, MinigameType.BRIDGE).reset(true);
                }
            }.runTaskLater(DuelsPlugin.getPlugin(), 1L);

            new BukkitRunnable() {
                @Override
                public void run() {
                    DuelsPlugin.getPlugin().getArenaManager().getArena(player, MinigameType.GAPPLE).reset(true);
                }
            }.runTaskLater(DuelsPlugin.getPlugin(), 1L);

            SQLConnection.addWins(player.getKiller(), MinigameType.SOUP);
            SQLConnection.addLosses(player, MinigameType.SOUP);

            SQLConnection.addWinstreak(player.getKiller(), MinigameType.SOUP);
            if (SQLConnection.getWinstreak(player, MinigameType.SOUP) > 0) {
                SQLConnection.removeWinstreak(player, MinigameType.SOUP);
            }
        }


        event.setDeathMessage(null);

    }

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.END_PORTAL) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerSoup(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();
        ItemStack item = e.getItem();

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if (item.getType() == Material.MUSHROOM_SOUP) {
                if (player.getHealth() < 20) {
                    player.setHealth(player.getHealth() + 3.5);
                    new ActionBar("§c❤ §a+3.5", player);
                    player.getInventory().getItemInHand().setType(Material.BOWL);
                }
            }
        }

    }


    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        Player player = (Player) e.getEntity();

        e.setCancelled(true);

        if ((DuelsPlugin.getPlugin().getArenaManager().getArena(player, MinigameType.SOUP) != null) &&
                DuelsPlugin.getPlugin().getArenaManager().getArena(player, MinigameType.SOUP).getState() == GameState.LIVE) {
            e.setCancelled(false);
        }

        if ((DuelsPlugin.getPlugin().getArenaManager().getArena(player, MinigameType.GLADIATOR) != null) &&
                DuelsPlugin.getPlugin().getArenaManager().getArena(player, MinigameType.GLADIATOR).getState() == GameState.LIVE) {
            e.setCancelled(false);
        }

        if ((DuelsPlugin.getPlugin().getArenaManager().getArena(player, MinigameType.BRIDGE) != null) &&
                DuelsPlugin.getPlugin().getArenaManager().getArena(player, MinigameType.BRIDGE).getState() == GameState.LIVE) {
            e.setCancelled(false);
        }

        if ((DuelsPlugin.getPlugin().getArenaManager().getArena(player, MinigameType.GAPPLE) != null) &&
                DuelsPlugin.getPlugin().getArenaManager().getArena(player, MinigameType.GAPPLE).getState() == GameState.LIVE) {
            e.setCancelled(false);
        }


    }


    @EventHandler
    public void onPlayerFoodLevelChange(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        e.setCancelled(true);
    }


    @EventHandler
    private void onNPCClick(NPCInteractionEvent event) {
        NPCs clicked = event.getClicked();
        Player player = event.getPlayer();

        if (clicked.getName().equalsIgnoreCase("§bSopa")) {
            if (event.getClickAction() == NPCClickAction.ATTACK) return;
            joinSoupLobby(player);
        } else if (clicked.getName().equalsIgnoreCase("§a§lSopa 1v1")) {
            if (DuelsPlugin.getPlugin().getArenaManager().getArena(0, MinigameType.SOUP).getPlayers().size() == 1) {
                GameManager.sendGame(player, "soup", 0);
            } else if (DuelsPlugin.getPlugin().getArenaManager().getArena(1, MinigameType.SOUP).getPlayers().size() == 1) {
                GameManager.sendGame(player, "soup", 1);
            } else if (DuelsPlugin.getPlugin().getArenaManager().getArena(2, MinigameType.SOUP).getPlayers().size() == 1) {
                GameManager.sendGame(player, "soup", 2);
            } else if (DuelsPlugin.getPlugin().getArenaManager().getArena(3, MinigameType.SOUP).getPlayers().size() == 1) {
                GameManager.sendGame(player, "soup", 3);
            } else if (DuelsPlugin.getPlugin().getArenaManager().getArena(4, MinigameType.SOUP).getPlayers().size() == 1) {
                GameManager.sendGame(player, "soup", 4);
            } else if (DuelsPlugin.getPlugin().getArenaManager().getArena(5, MinigameType.SOUP).getPlayers().size() == 1) {
                GameManager.sendGame(player, "soup", 5);
            } else if (DuelsPlugin.getPlugin().getArenaManager().getArena(6, MinigameType.SOUP).getPlayers().size() == 1) {
                GameManager.sendGame(player, "soup", 6);
            } else if (DuelsPlugin.getPlugin().getArenaManager().getArena(7, MinigameType.SOUP).getPlayers().size() == 1) {
                GameManager.sendGame(player, "soup", 7);
            } else if (DuelsPlugin.getPlugin().getArenaManager().getArena(8, MinigameType.SOUP).getPlayers().size() == 1) {
                GameManager.sendGame(player, "soup", 8);
            } else if (DuelsPlugin.getPlugin().getArenaManager().getArena(9, MinigameType.SOUP).getPlayers().size() == 1) {
                GameManager.sendGame(player, "soup", 9);
            } else {
                Random random = new Random();
                GameManager.sendGame(player, "soup", random.nextInt(11));
            }
        }

        if (clicked.getName().equalsIgnoreCase("§bGladiator")) {
            if (event.getClickAction() == NPCClickAction.ATTACK) return;
            joinGladiatorLobby(player);
        } else if (clicked.getName().equalsIgnoreCase("§bGladiator 1v1")) {
            if (DuelsPlugin.getPlugin().getArenaManager().getArena(0, MinigameType.GLADIATOR).getPlayers().size() == 1) {
                GameManager.sendGame(player, "soup", 0);
            } else if (DuelsPlugin.getPlugin().getArenaManager().getArena(1, MinigameType.GLADIATOR).getPlayers().size() == 1) {
                GameManager.sendGame(player, "soup", 1);
            } else if (DuelsPlugin.getPlugin().getArenaManager().getArena(2, MinigameType.GLADIATOR).getPlayers().size() == 1) {
                GameManager.sendGame(player, "soup", 2);
            } else if (DuelsPlugin.getPlugin().getArenaManager().getArena(3, MinigameType.GLADIATOR).getPlayers().size() == 1) {
                GameManager.sendGame(player, "soup", 3);
            } else if (DuelsPlugin.getPlugin().getArenaManager().getArena(4, MinigameType.GLADIATOR).getPlayers().size() == 1) {
                GameManager.sendGame(player, "soup", 4);
            } else if (DuelsPlugin.getPlugin().getArenaManager().getArena(5, MinigameType.GLADIATOR).getPlayers().size() == 1) {
                GameManager.sendGame(player, "soup", 5);
            } else if (DuelsPlugin.getPlugin().getArenaManager().getArena(6, MinigameType.GLADIATOR).getPlayers().size() == 1) {
                GameManager.sendGame(player, "soup", 6);
            } else if (DuelsPlugin.getPlugin().getArenaManager().getArena(7, MinigameType.GLADIATOR).getPlayers().size() == 1) {
                GameManager.sendGame(player, "soup", 7);
            } else if (DuelsPlugin.getPlugin().getArenaManager().getArena(8, MinigameType.GLADIATOR).getPlayers().size() == 1) {
                GameManager.sendGame(player, "soup", 8);
            } else if (DuelsPlugin.getPlugin().getArenaManager().getArena(9, MinigameType.GLADIATOR).getPlayers().size() == 1) {
                GameManager.sendGame(player, "soup", 9);
            } else {
                Random random = new Random();
                GameManager.sendGame(player, "soup", random.nextInt(11));
            }
        }

    }

    public static void joinSoupLobby(Player player) {
        player.teleport(new Location(Bukkit.getWorld(sectionLobbySoup.getString("World")),
                sectionLobbySoup.getDouble("X"),
                sectionLobbySoup.getDouble("Y"),
                sectionLobbySoup.getDouble("Z"),
                (float) sectionLobbySoup.getDouble("Yaw"),
                (float) sectionLobbySoup.getDouble("Pitch")));
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        ScoreboardManager.updateScoreboardLobbySoup(player);
    }

    public static void joinGladiatorLobby(Player player) {
        player.teleport(new Location(Bukkit.getWorld(sectionLobbyGladiator.getString("World")),
                sectionLobbyGladiator.getDouble("X"),
                sectionLobbyGladiator.getDouble("Y"),
                sectionLobbyGladiator.getDouble("Z"),
                (float) sectionLobbyGladiator.getDouble("Yaw"),
                (float) sectionLobbyGladiator.getDouble("Pitch")));
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        ScoreboardManager.updateScoreboardLobbyGladiator(player);
    }
}
