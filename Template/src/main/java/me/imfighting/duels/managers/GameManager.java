package me.imfighting.duels.managers;

import me.imfighting.duels.DuelsPlugin;
import me.imfighting.duels.GameState;
import me.imfighting.duels.MinigameType;
import me.imfighting.duels.instance.Arena;
import me.imfighting.duels.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class GameManager {


    public static void sendGame(Player player, String minigame, int id) {
        if (minigame == "soup") {
            player.getInventory().clear();
            Arena arena = DuelsPlugin.getPlugin().getArenaManager().getArena(id, MinigameType.SOUP);
            if (arena.getState() == GameState.RECRUITING) {
                arena.addPlayer(player);
                arena.setMinigameType(MinigameType.SOUP);

            } else {
                player.sendMessage("§cVocê não pode entrar nesta arena agora.");
            }
        }
    }
}
