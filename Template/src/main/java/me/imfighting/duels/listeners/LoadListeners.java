package me.imfighting.duels.listeners;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import me.imfighting.duels.DuelsPlugin;
import me.imfighting.duels.managers.LocationsManagers;
import me.imfighting.duels.managers.NPCManager;
import me.imfighting.duels.managers.PlayerManager;
import me.imfighting.duels.npc.NPCClickAction;
import me.imfighting.duels.npc.NPCInteractionEvent;
import me.imfighting.duels.npc.NPCOptions;
import me.imfighting.duels.npc.NPCs;
import me.imfighting.duels.util.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class LoadListeners implements Listener {

    final ConfigUtil config = DuelsPlugin.getPlugin().getConfig();
    final ConfigUtil locations = DuelsPlugin.getPlugin().getLocations();
    final ConfigurationSection section = config.getConfigurationSection("item-desafiar");
    final ConfigurationSection sectionLocations = locations.getConfigurationSection("NPC");
    final ConfigurationSection sectionSkins = config.getConfigurationSection("Skins");

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);

        Player player = e.getPlayer();

        PlayerManager.updatePlayer(player);

        if (sectionLocations.contains("Soup")) {
            NPCs npc = DuelsPlugin.getPlugin().getNpcManager().newNPC(NPCOptions.builder()
                    .name("§bSopa")
                    .hideNametag(false)
                    .texture(sectionSkins.getString("Sopa.texture"))
                    .signature(sectionSkins.getString("Sopa.signature"))
                    .location(new Location(
                            Bukkit.getWorld(sectionLocations.getString("Soup.World")),
                            sectionLocations.getDouble("Soup.X"),
                            sectionLocations.getDouble("Soup.Y"),
                            sectionLocations.getDouble("Soup.Z")
                    ))
                    .build()
            );
            npc.showTo(player);
        }

        if (sectionLocations.contains("Bridge")) {
            NPCs npc = DuelsPlugin.getPlugin().getNpcManager().newNPC(NPCOptions.builder()
                    .name("§bThe bridge")
                    .hideNametag(false)
                    .texture(sectionSkins.getString("Bridge.texture"))
                    .signature(sectionSkins.getString("Bridge.signature"))
                    .location(new Location(
                            Bukkit.getWorld(sectionLocations.getString("Bridge.World")),
                            sectionLocations.getDouble("Bridge.X"),
                            sectionLocations.getDouble("Bridge.Y"),
                            sectionLocations.getDouble("Bridge.Z")
                    ))
                    .build()
            );
            npc.showTo(player);
        }

        if (sectionLocations.contains("Gladiator")) {
            NPCs npc = DuelsPlugin.getPlugin().getNpcManager().newNPC(NPCOptions.builder()
                    .name("§bGladiator")
                    .hideNametag(false)
                    .texture(sectionSkins.getString("Gladiator.texture"))
                    .signature(sectionSkins.getString("Gladiator.signature"))
                    .location(new Location(
                            Bukkit.getWorld(sectionLocations.getString("Gladiator.World")),
                            sectionLocations.getDouble("Gladiator.X"),
                            sectionLocations.getDouble("Gladiator.Y"),
                            sectionLocations.getDouble("Gladiator.Z")
                    ))
                    .build()
            );
            npc.showTo(player);
        }

        if (sectionLocations.contains("Gapple")) {
            NPCs npc = DuelsPlugin.getPlugin().getNpcManager().newNPC(NPCOptions.builder()
                    .name("§bGapple")
                    .hideNametag(false)
                    .texture(sectionSkins.getString("Gapple.texture"))
                    .signature(sectionSkins.getString("Gapple.signature"))
                    .location(new Location(
                            Bukkit.getWorld(sectionLocations.getString("Gapple.World")),
                            sectionLocations.getDouble("Gapple.X"),
                            sectionLocations.getDouble("Gapple.Y"),
                            sectionLocations.getDouble("Gapple.Z")
                    ))
                    .build()
            );
            npc.showTo(player);
        }


    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e) {
        ItemStack itemDrop = e.getItemDrop().getItemStack();
        if (itemDrop.getItemMeta().getDisplayName().equals(section.getString("name")
                .replace('&', '§'))) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = e.getEntity();
            PlayerManager.updatePlayer(player);
            e.setDeathMessage(null);
        }
    }

    @EventHandler
    private void onNPCClick(NPCInteractionEvent event) {
        NPCs clicked = event.getClicked();

        if (clicked.getName().equalsIgnoreCase("§bSopa")) {
            if (event.getClickAction() == NPCClickAction.ATTACK) return;
            event.getPlayer().sendMessage("Teste");
        } else {
            event.getPlayer().sendMessage("<" + clicked.getName() + "> Sorry, I don't think you're looking for me.");
        }
    }

}
