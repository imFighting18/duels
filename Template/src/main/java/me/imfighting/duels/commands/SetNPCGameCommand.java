package me.imfighting.duels.commands;

import me.imfighting.duels.DuelsPlugin;
import me.imfighting.duels.database.SQLConnection;
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

                if (!player.getWorld().getName().equalsIgnoreCase("soup_lobby")) {
                    player.sendMessage("§cO nome do mundo precisa ser 'soup_lobby'.");
                    return false;
                }

                NPCs npc = DuelsPlugin.getPlugin().getNpcManager().newNPC(NPCOptions.builder()
                        .name("§bSopa 1v1")
                        .hideNametag(false)
                        .texture(sectionSkins.getString("Soup-Lobby.texture"))
                        .signature(sectionSkins.getString("Soup-Lobby.signature"))
                        .location(player.getLocation())
                        .build()
                );
                npc.showTo(player);
                setLocationNPC(player, "soup");
                player.sendMessage(section.getString("sucess-setnpcgame"));
            } else if (strings[0].equalsIgnoreCase("gladiator")) {

                if (!player.getWorld().getName().equalsIgnoreCase("gladiator_lobby")) {
                    player.sendMessage("§cO nome do mundo precisa ser 'gladiator_lobby'.");
                    return false;
                }

                NPCs npc = DuelsPlugin.getPlugin().getNpcManager().newNPC(NPCOptions.builder()
                        .name("§bGladiator 1v1")
                        .hideNametag(false)
                        .texture(sectionSkins.getString("Gladiator-Lobby.texture"))
                        .signature(sectionSkins.getString("Gladiator-Lobby.signature"))
                        .location(player.getLocation())
                        .build()
                );
                npc.showTo(player);
                setLocationNPC(player, "gladiator");
                player.sendMessage(section.getString("sucess-setnpcgame"));
            } else {
                player.sendMessage(section.getString("noexists-setnpcgame").replace('&', '§'));
            }
        }

        return false;
    }

    private void setLocationNPC(Player player, String minigame) {

        if (minigame == "soup") {
            SQLConnection.setLocationPlay(player, "soup");
        } else if (minigame == "gladiator") {
            SQLConnection.setLocationPlay(player, "gladiator");
        }
    }
}
