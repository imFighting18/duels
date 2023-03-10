package me.imfighting.duels.instance;

import me.imfighting.duels.DuelsPlugin;
import me.imfighting.duels.GameState;
import me.imfighting.duels.MinigameType;
import me.imfighting.duels.managers.ScoreboardManager;
import me.imfighting.duels.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class Game {

    private Arena arena;
    private HashMap<UUID, Integer> points;

    public Game(Arena arena) {
        this.arena = arena;
        points = new HashMap<>();
    }

    public void start() {
        arena.setState(GameState.LIVE);

        if (arena.getMinigameType() == MinigameType.SOUP) {

            arena.sendTitle("", "");

            Bukkit.getPlayer(arena.getPlayers().get(0)).teleport(
                    new Location(
                            Bukkit.getWorld(DuelsPlugin.getPlugin().getPositions().getString(
                                    "Soup." + arena.getId() + ".Pos1.World")),
                            DuelsPlugin.getPlugin().getPositions().getDouble(
                                    "Soup." + arena.getId() + ".Pos1.X"),
                            DuelsPlugin.getPlugin().getPositions().getDouble(
                                    "Soup." + arena.getId() + ".Pos1.Y"),
                            DuelsPlugin.getPlugin().getPositions().getDouble(
                                    "Soup." + arena.getId() + ".Pos1.Z"),
                            (float) DuelsPlugin.getPlugin().getPositions().getDouble(
                                    "Soup." + arena.getId() + ".Pos1.Yaw"),
                            (float) DuelsPlugin.getPlugin().getPositions().getDouble(
                                    "Soup." + arena.getId() + ".Pos1.Pitch")
                    )
            );
            Bukkit.getPlayer(arena.getPlayers().get(1)).teleport(
                    new Location(
                            Bukkit.getWorld(DuelsPlugin.getPlugin().getPositions().getString(
                                    "Soup." + arena.getId() + ".Pos2.World")),
                            DuelsPlugin.getPlugin().getPositions().getDouble(
                                    "Soup." + arena.getId() + ".Pos2.X"),
                            DuelsPlugin.getPlugin().getPositions().getDouble(
                                    "Soup." + arena.getId() + ".Pos2.Y"),
                            DuelsPlugin.getPlugin().getPositions().getDouble(
                                    "Soup." + arena.getId() + ".Pos2.Z"),
                            (float) DuelsPlugin.getPlugin().getPositions().getDouble(
                                    "Soup." + arena.getId() + ".Pos2.Yaw"),
                            (float) DuelsPlugin.getPlugin().getPositions().getDouble(
                                    "Soup." + arena.getId() + ".Pos2.Pitch")
                    )
            );

            final ItemBuilder builder = new ItemBuilder(Material.DIAMOND_SWORD)
                    .addEnchant(Enchantment.DAMAGE_ALL, 1);

            for (UUID uuid : arena.getPlayers()) {
                Bukkit.getPlayer(uuid).getInventory().clear();
                Bukkit.getPlayer(uuid).getInventory().setItem(0, builder.build());

                Bukkit.getPlayer(uuid).setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                ScoreboardManager.updateScoreboardGameSoup(Bukkit.getPlayer(uuid));

                for (int i = 1; i <= 8; i++) {
                    Bukkit.getPlayer(uuid).getInventory().setItem(i, new ItemStack(Material.MUSHROOM_SOUP));
                }

                Bukkit.getPlayer(uuid).getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
                Bukkit.getPlayer(uuid).getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                Bukkit.getPlayer(uuid).getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                Bukkit.getPlayer(uuid).getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));

            }
        }
    }
}
