package me.imfighting.duels.commands;

import me.imfighting.duels.DuelsPlugin;
import me.imfighting.duels.util.ConfigUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class SetPosCommand implements CommandExecutor {

    final ConfigUtil pos = DuelsPlugin.getPlugin().getPositions();
    final ConfigUtil config = DuelsPlugin.getPlugin().getConfig();
    final ConfigurationSection section = config.getConfigurationSection("Messages");

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("§cOnly players");
            return false;
        }

        Player player = (Player) commandSender;

        // /setpos <pos1,pos2> <minigame> <arena>

        if (!player.hasPermission("duels.setpos")) {
            player.sendMessage(config.getString("no-permission").replace('&', '§'));
            return false;
        }

        if (strings.length < 3) {
            player.sendMessage(section.getString("sintaxe-setpos").replace('&', '§'));
            return false;
        }

        if (strings.length == 3) {
            if (strings[0].equalsIgnoreCase("pos1")) {
                if (strings[1].equalsIgnoreCase("soup")) {
                    int id;

                    try {
                        id = Integer.parseInt(strings[2]);
                        setConfigPositions(player, "Pos1", "Soup", id);
                        player.sendMessage(section.getString("sucess-setpos").replace('&', '§'));
                    } catch (NumberFormatException e) {
                        player.sendMessage("§cA arena precisa ser um número.");
                        e.printStackTrace();
                    }
                }
            } else if (strings[0].equalsIgnoreCase("pos2")) {
                if (strings[1].equalsIgnoreCase("soup")) {
                    int id;

                    try {
                        id = Integer.parseInt(strings[2]);
                        setConfigPositions(player, "Pos2", "Soup", id);
                        player.sendMessage(section.getString("sucess-setpos").replace('&', '§'));
                    } catch (NumberFormatException e) {
                        player.sendMessage("§cA arena precisa ser um número.");
                        e.printStackTrace();
                    }
                }
            }
        }

        return false;
    }

    private void setConfigPositions(Player player, String positions, String minigame, int id) {
        pos.set(minigame + "." + id + "."+positions+".World", player.getWorld().getName());
        pos.set(minigame + "." + id + "."+positions+".X", player.getLocation().getX());
        pos.set(minigame + "." + id + "."+positions+".Y", player.getLocation().getY());
        pos.set(minigame + "." + id + "."+positions+".Z", player.getLocation().getZ());
        pos.set(minigame + "." + id + "."+positions+".Yaw", player.getLocation().getYaw());
        pos.set(minigame + "." + id + "."+positions+".Pitch", player.getLocation().getPitch());
        pos.save();
    }

}
