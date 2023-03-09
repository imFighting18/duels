package me.imfighting.duels.commands;

import me.imfighting.duels.DuelsPlugin;
import me.imfighting.duels.util.ConfigUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class SetArenaCommand implements CommandExecutor {

    final ConfigUtil config = DuelsPlugin.getPlugin().getConfig();
    final ConfigurationSection section = config.getConfigurationSection("Messages");

    final ConfigUtil arenas = DuelsPlugin.getPlugin().getArenas();


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("§cOnly players");
        }

        Player player = (Player) commandSender;

        if (!player.hasPermission("duels.setarena")) {
            player.sendMessage(section.getString("no-permission").replace('&', '§'));
            return false;
        }

        if (strings.length < 1) {
            player.sendMessage(section.getString("sintaxe-setarena").replace('&', '§'));
            return false;
        }

        if (strings.length == 1) {
            if (strings[0].equalsIgnoreCase("soup")) {

                if (!player.getWorld().getName().contains("soup_")) {
                    player.sendMessage("O mundo precisa começar com 'soup_'.");
                    return false;
                }

                if (!arenas.contains("game-numbers")) {
                    arenas.set("game-numbers", 0);
                    arenas.set("arenas." + arenas.getInt("game-numbers") + ".World", player.getWorld().getName());
                    arenas.set("arenas." + arenas.getInt("game-numbers") + ".X", player.getLocation().getX());
                    arenas.set("arenas." + arenas.getInt("game-numbers") + ".Y", player.getLocation().getY());
                    arenas.set("arenas." + arenas.getInt("game-numbers") + ".Z", player.getLocation().getZ());
                    arenas.save();
                    player.sendMessage(section.getString("sucess-setarena").replace('&', '§'));
                } else {
                    arenas.set("game-numbers", arenas.getInt("game-numbers") + 1);
                    arenas.set("arenas." + arenas.getInt("game-numbers") + ".World", player.getWorld().getName());
                    arenas.set("arenas." + arenas.getInt("game-numbers") + ".X", player.getLocation().getX());
                    arenas.set("arenas." + arenas.getInt("game-numbers") + ".Y", player.getLocation().getY());
                    arenas.set("arenas." + arenas.getInt("game-numbers") + ".Z", player.getLocation().getZ());
                    arenas.save();
                    player.sendMessage(section.getString("sucess-setarena").replace('&', '§'));
                }
            }
        }
        return false;
    }
}
