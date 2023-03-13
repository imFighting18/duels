package me.imfighting.duels.commands;

import me.imfighting.duels.DuelsPlugin;
import me.imfighting.duels.MinigameType;
import me.imfighting.duels.instance.Arena;
import me.imfighting.duels.listeners.LoadListeners;
import me.imfighting.duels.managers.LocationsManagers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("§cOnly players");
        }

        Player player = (Player) commandSender;
        Arena arenaSoup = DuelsPlugin.getPlugin().getArenaManager().getArena(player, MinigameType.SOUP);
        Arena arenaGladiator = DuelsPlugin.getPlugin().getArenaManager().getArena(player, MinigameType.GLADIATOR);
        Arena arenaSumo = DuelsPlugin.getPlugin().getArenaManager().getArena(player, MinigameType.SUMO);
        Arena arenaGapple = DuelsPlugin.getPlugin().getArenaManager().getArena(player, MinigameType.GAPPLE);

        if (arenaSoup != null) {
            LoadListeners.joinSoupLobby(player);
            arenaSoup.removePlayer(player);
        } else {
            player.teleport(LocationsManagers.getLobby(player));
        }

        if (arenaGladiator != null) {
            LoadListeners.joinGladiatorLobby(player);
            arenaGladiator.removePlayer(player);
        } else {
            player.teleport(LocationsManagers.getLobby(player));
        }




        return false;
    }
}
