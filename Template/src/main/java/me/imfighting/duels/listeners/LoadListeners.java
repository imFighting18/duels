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

public class LoadListeners implements Listener {

    final ConfigUtil config = DuelsPlugin.getPlugin().getConfig();
    static final ConfigUtil locations = DuelsPlugin.getPlugin().getLocations();
    final ConfigurationSection section = config.getConfigurationSection("item-desafiar");
    static final ConfigurationSection sectionLobbySoup = locations.getConfigurationSection("Soup");
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

        for (Player online : Bukkit.getOnlinePlayers()) {
            if (!DuelsPlugin.getPlugin().getArenaManager().getArena(online).getPlayers().contains(online)) {
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

        if (SQLConnection.containsNPCPlay("gladiator") &&
                player.getWorld().getName().equalsIgnoreCase(SQLConnection.getLocationNPCPlayWorld("soup"))) {
            NPCs npc = DuelsPlugin.getPlugin().getNpcManager().newNPC(NPCOptions.builder()
                    .name("§a§lGladiator 1v1")
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

        Arena arena = DuelsPlugin.getPlugin().getArenaManager().getArena(e.getPlayer());
        if (arena != null) {
            arena.removePlayer(e.getPlayer());
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
                    DuelsPlugin.getPlugin().getArenaManager().getArena(player).reset(true);
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

        if ((DuelsPlugin.getPlugin().getArenaManager().getArena(player) != null) &&
                DuelsPlugin.getPlugin().getArenaManager().getArena(player).getState() == GameState.LIVE) {
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
            GameManager.sendGame(player, "soup", 0);
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
}
