package me.imfighting.duels.commands;

import me.imfighting.duels.DuelsPlugin;
import me.imfighting.duels.managers.LocationsManagers;
import me.imfighting.duels.util.ConfigUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class SetLobbyCommand implements CommandExecutor {

    final ConfigUtil config = DuelsPlugin.getPlugin().getConfig();
    final ConfigurationSection section = config.getConfigurationSection("Messages");

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("§cOnly players");
            return false;
        }
        Player player = (Player) commandSender;
        if (!player.hasPermission("duels.setlobby")) {
            player.sendMessage(section.getString("no-permission").replace('&', '§'));
            return false;
        }

        //setlobby arg

        if (strings.length < 1) {
            player.sendMessage(section.getString("sintaxe-setlobby").replace('&', '§'));
            return false;
        }

        if (strings.length == 1) {
            if (strings[0].equalsIgnoreCase("lobby")) {
                LocationsManagers.setLobby(player);
                player.sendMessage(section.getString("sucess-setlobby").replace('&', '§'));
            } else if (strings[0].equalsIgnoreCase("soup")) {
                LocationsManagers.setLobbyMinigame(player, "soup");
                player.sendMessage(section.getString("sucess-setlobby").replace('&', '§'));
            } else if (strings[0].equalsIgnoreCase("gladiator")) {
                LocationsManagers.setLobbyMinigame(player, "gladiator");
                player.sendMessage(section.getString("sucess-setlobby").replace('&', '§'));
            }
        }
        return false;
    }
}
