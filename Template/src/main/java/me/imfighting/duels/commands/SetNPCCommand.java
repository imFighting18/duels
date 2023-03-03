package me.imfighting.duels.commands;

import me.imfighting.duels.DuelsPlugin;
import me.imfighting.duels.managers.LocationsManagers;
import me.imfighting.duels.managers.NPCManager;
import me.imfighting.duels.npc.NPCOptions;
import me.imfighting.duels.npc.NPCs;
import me.imfighting.duels.util.ConfigUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class SetNPCCommand implements CommandExecutor {

    final ConfigUtil config = DuelsPlugin.getPlugin().getConfig();
    final ConfigUtil locations = DuelsPlugin.getPlugin().getLocations();
    final ConfigurationSection section = config.getConfigurationSection("Messages");
    final ConfigurationSection sectionSkins = config.getConfigurationSection("Skins");

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("§cOnly players");
            return false;
        }

        Player player = (Player) commandSender;

        if (!player.hasPermission("duels.setnpc")) {
            player.sendMessage(section.getString("no-permission").replace('&', '§'));
        }

        if (strings.length < 1) {
            player.sendMessage(section.getString("sintaxe-setnpc").replace('&', '§'));
            return false;
        }

        if (strings.length == 1) {
            if (strings[0].equalsIgnoreCase("soup")) {
                NPCs npc = DuelsPlugin.getPlugin().getNpcManager().newNPC(NPCOptions.builder()
                        .name("§bSopa")
                        .hideNametag(false)
                        .texture(sectionSkins.getString("Sopa.texture"))
                        .signature(sectionSkins.getString("Sopa.signature"))
                        .location(player.getLocation())
                        .build()
                );
                npc.showTo(player);
                setLocationNPC(player, "soup");
                player.sendMessage(section.getString("sucess-setnpc").replace('&', '§'));
            } else if (strings[0].equalsIgnoreCase("bridge")) {
                NPCs npc = DuelsPlugin.getPlugin().getNpcManager().newNPC(NPCOptions.builder()
                        .name("§bThe bridge")
                        .hideNametag(false)
                        .texture(sectionSkins.getString("Bridge.texture"))
                        .signature(sectionSkins.getString("Bridge.signature"))
                        .location(player.getLocation())
                        .build()
                );
                npc.showTo(player);
                setLocationNPC(player, "bridge");
                player.sendMessage(section.getString("sucess-setnpc").replace('&', '§'));
            } else if (strings[0].equalsIgnoreCase("gladiator")) {
                NPCs npc = DuelsPlugin.getPlugin().getNpcManager().newNPC(NPCOptions.builder()
                        .name("§bGladiator")
                        .hideNametag(false)
                        .texture(sectionSkins.getString("Gladiator.texture"))
                        .signature(sectionSkins.getString("Gladiator.signature"))
                        .location(player.getLocation())
                        .build()
                );
                npc.showTo(player);
                setLocationNPC(player, "gladiator");
                player.sendMessage(section.getString("sucess-setnpc").replace('&', '§'));
            } else if (strings[0].equalsIgnoreCase("gapple")) {
                NPCs npc = DuelsPlugin.getPlugin().getNpcManager().newNPC(NPCOptions.builder()
                        .name("§bGapple")
                        .hideNametag(false)
                        .texture(sectionSkins.getString("Gapple.texture"))
                        .signature(sectionSkins.getString("Gapple.signature"))
                        .location(player.getLocation())
                        .build()
                );
                npc.showTo(player);
                setLocationNPC(player, "gapple");
                player.sendMessage(section.getString("sucess-setnpc").replace('&', '§'));
            } else {
                player.sendMessage(section.getString("error-setnpc").replace('&', '§'));
            }
        }

        return false;
    }

    private void setLocationNPC(Player player, String minigame) {

        if (minigame == "soup") {
            locations.set("NPC.Soup.World", player.getLocation().getWorld().getName());
            locations.set("NPC.Soup.X", player.getLocation().getX());
            locations.set("NPC.Soup.Y", player.getLocation().getY());
            locations.set("NPC.Soup.Z", player.getLocation().getZ());
            locations.save();
        } else if (minigame == "bridge") {
            locations.set("NPC.Bridge.World", player.getLocation().getWorld().getName());
            locations.set("NPC.Bridge.X", player.getLocation().getX());
            locations.set("NPC.Bridge.Y", player.getLocation().getY());
            locations.set("NPC.Bridge.Z", player.getLocation().getZ());
            locations.save();
        } else if (minigame == "gladiator") {
            locations.set("NPC.Gladiator.World", player.getLocation().getWorld().getName());
            locations.set("NPC.Gladiator.X", player.getLocation().getX());
            locations.set("NPC.Gladiator.Y", player.getLocation().getY());
            locations.set("NPC.Gladiator.Z", player.getLocation().getZ());
            locations.save();
        } else if (minigame == "gapple") {
            locations.set("NPC.Gapple.World", player.getLocation().getWorld().getName());
            locations.set("NPC.Gapple.X", player.getLocation().getX());
            locations.set("NPC.Gapple.Y", player.getLocation().getY());
            locations.set("NPC.Gapple.Z", player.getLocation().getZ());
            locations.save();
        }
    }

}
