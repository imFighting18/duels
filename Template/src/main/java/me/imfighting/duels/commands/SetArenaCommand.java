package me.imfighting.duels.commands;

import me.imfighting.duels.DuelsPlugin;
import me.imfighting.duels.util.ConfigUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class SetArenaCommand implements CommandExecutor {

    final ConfigUtil arenas = DuelsPlugin.getPlugin().getArenas();
    final ConfigUtil config = DuelsPlugin.getPlugin().getConfig();
    final ConfigurationSection section = config.getConfigurationSection("Messages");

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players");
            return false;
        }

        Player player = (Player) commandSender;

        if (!player.hasPermission("duels.setarena")) {
            player.sendMessage(section.getString("no-permission").replace('&', '§'));
            return false;
        }

        if (strings.length < 2) {
            player.sendMessage(section.getString("sintaxe-setarena").replace('&', '§'));
            return false;
        }

        if (strings.length == 2) {
            if (strings[0].equalsIgnoreCase("soup")) {
                int id;

                try {
                    id = Integer.parseInt(strings[1]);
                    setConfigArena(player, "Soup", id);
                    player.sendMessage(section.getString("sucess-setarena").replace('&', '§'));
                } catch (NumberFormatException e) {
                    player.sendMessage("§cA arena precisa ser um número.");
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private void setConfigArena(Player player, String minigame, int id) {
        arenas.set(minigame + "." + id + ".world", player.getWorld().getName());
        arenas.set(minigame + "." + id + ".x", player.getLocation().getX());
        arenas.set(minigame + "." + id + ".y", player.getLocation().getY());
        arenas.set(minigame + "." + id + ".z", player.getLocation().getZ());
        arenas.set(minigame + "." + id + ".yaw", player.getLocation().getYaw());
        arenas.set(minigame + "." + id + ".pitch", player.getLocation().getPitch());
        arenas.save();
    }
}
