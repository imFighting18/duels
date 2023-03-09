package me.imfighting.duels.instance;

import me.imfighting.duels.GameState;
import me.imfighting.duels.MinigameType;
import me.imfighting.duels.util.ItemBuilder;
import org.bukkit.Bukkit;
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
            final ItemBuilder builder = new ItemBuilder(Material.DIAMOND_SWORD)
                    .addEnchant(Enchantment.DAMAGE_ALL, 1);

            for (UUID uuid : arena.getPlayers()) {
                Bukkit.getPlayer(uuid).getInventory().clear();
                Bukkit.getPlayer(uuid).getInventory().setItem(0, builder.build());
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
