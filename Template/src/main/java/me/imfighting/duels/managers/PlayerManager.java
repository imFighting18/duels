package me.imfighting.duels.managers;

import me.imfighting.duels.DuelsPlugin;
import me.imfighting.duels.util.ConfigUtil;
import me.imfighting.duels.util.ItemBuilder;
import org.bukkit.GameMode;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class PlayerManager {

    static final ConfigUtil config = DuelsPlugin.getPlugin().getConfig();
    final static ConfigurationSection desafiar = config.getConfigurationSection("item-desafiar");
    final static ItemBuilder builder = new ItemBuilder(desafiar.getString("material"))
            .setName(desafiar.getString("name").replace('&', '§'))
            .setDurability((short) desafiar.getInt("data"));

    public static void updatePlayer(Player player) {
        player.getInventory().clear();
        player.getInventory().setItem(desafiar.getInt("slot"), builder.build());
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.SURVIVAL);

        player.teleport(LocationsManagers.getLobby(player));
        ScoreboardManager.updateScoreboard(player);

    }

}
