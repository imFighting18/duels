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

        if (strings.length < 2) {
            player.sendMessage(section.getString("sintaxe-setarena").replace('&', '§'));
            return false;
        }

        // /setarena pos1 soup

        if (strings.length == 2) {
            if (strings[1].equalsIgnoreCase("soup")) {

                if (strings[0].equalsIgnoreCase("pos1")) {
                    if (!arenas.contains("games-numbers")) {
                        arenas.set("games-numbers", 1);
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-1" + ".World",
                                player.getWorld().getName());
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-1" + ".X",
                                player.getLocation().getX());
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-1" + ".Y",
                                player.getLocation().getY());
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-1" + ".Z",
                                player.getLocation().getZ());
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-1" + ".Yaw",
                                player.getLocation().getYaw());
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-1" + ".Pitch",
                                player.getLocation().getPitch());
                        arenas.save();
                    } else {
                        arenas.set("games-numbers", arenas.getInt("games-numbers") + 1);
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-1" + ".World",
                                player.getWorld().getName());
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-1" + ".X",
                                player.getLocation().getX());
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-1" + ".Y",
                                player.getLocation().getY());
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-1" + ".Z",
                                player.getLocation().getZ());
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-1" + ".Yaw",
                                player.getLocation().getYaw());
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-1" + ".Pitch",
                                player.getLocation().getPitch());
                        arenas.save();
                    }
                    player.sendMessage(section.getString("sucess-setarena").replace('&', '§'));
                } else if (strings[0].equalsIgnoreCase("pos2")) {
                    if (!arenas.contains("games-numbers")) {
                        arenas.set("games-numbers", 1);
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-2" + ".World",
                                player.getWorld().getName());
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-2" + ".X",
                                player.getLocation().getX());
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-2" + ".Y",
                                player.getLocation().getY());
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-2" + ".Z",
                                player.getLocation().getZ());
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-2" + ".Yaw",
                                player.getLocation().getYaw());
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-2" + ".Pitch",
                                player.getLocation().getPitch());
                        arenas.save();
                    } else {
                        arenas.set("games-numbers", arenas.getInt("games-numbers") + 1);
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-2" + ".World",
                                player.getWorld().getName());
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-2" + ".X",
                                player.getLocation().getX());
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-2" + ".Y",
                                player.getLocation().getY());
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-2" + ".Z",
                                player.getLocation().getZ());
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-2" + ".Yaw",
                                player.getLocation().getYaw());
                        arenas.set("Soup-" + arenas.getInt("games-numbers") + "-2" + ".Pitch",
                                player.getLocation().getPitch());
                        arenas.save();
                    }
                    player.sendMessage(section.getString("sucess-setarena").replace('&', '§'));
                }
            }
        }
        return false;
    }
}
