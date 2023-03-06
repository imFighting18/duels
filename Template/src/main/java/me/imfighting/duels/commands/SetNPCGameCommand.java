package me.imfighting.duels.commands;

import me.imfighting.duels.DuelsPlugin;
import me.imfighting.duels.npc.NPCOptions;
import me.imfighting.duels.npc.NPCs;
import me.imfighting.duels.util.ConfigUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class SetNPCGameCommand implements CommandExecutor {

    final ConfigUtil config = DuelsPlugin.getPlugin().getConfig();
    final ConfigurationSection section = config.getConfigurationSection("Messages");
    final ConfigurationSection sectionSkins = config.getConfigurationSection("Skins");

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("§cOnly players");
            return false;
        }

        Player player = (Player) commandSender;

        if (!player.hasPermission("duels.setnpcgame")) {
            player.sendMessage(section.getString("no-permission").replace('&', '§'));
            return false;
        }

        // /setnpcgame soup

        if (strings.length < 1) {
            player.sendMessage(section.getString("sintaxe-setnpcgame").replace('&', '§'));
            return false;
        } else if (strings.length == 1) {
            if (strings[0].equalsIgnoreCase("soup")) {
                NPCs npc = DuelsPlugin.getPlugin().getNpcManager().newNPC(NPCOptions.builder()
                        .name("§b§lJogar")
                        .hideNametag(false)
                        .texture(sectionSkins.getString("Soup-Lobby.texture"))
                        .signature(sectionSkins.getString("Soup-Lobby.signature"))
                        .location(player.getLocation())
                        .build()
                );
                npc.showTo(player);
            }
        }

        return false;
    }
}
