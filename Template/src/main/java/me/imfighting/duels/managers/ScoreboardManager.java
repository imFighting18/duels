package me.imfighting.duels.managers;

import me.imfighting.duels.DuelsPlugin;
import me.imfighting.duels.MinigameType;
import me.imfighting.duels.database.SQLConnection;
import me.imfighting.duels.util.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.util.List;

public class ScoreboardManager {

    static final ConfigUtil config = DuelsPlugin.getPlugin().getConfig();
    static final List<String> lines = config.getStringList("scoreboard");

    public static void updateScoreboard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(lines.get(0));

        Score space1 = objective.getScore(lines.get(1)
                .replace("%wins_soup%", "" + SQLConnection.getWins(player, MinigameType.SOUP))
                .replace("%streak_soup%", "" + SQLConnection.getWinstreak(player, MinigameType.SOUP)));
        space1.setScore(9);

        Score soup = objective.getScore(lines.get(2)
                .replace("%wins_soup%", "" + SQLConnection.getWins(player, MinigameType.SOUP))
                .replace("%streak_soup%", "" + SQLConnection.getWinstreak(player, MinigameType.SOUP)));
        soup.setScore(8);

        Score winSoup = objective.getScore(lines.get(3)
                .replace("%wins_soup%", "" + SQLConnection.getWins(player, MinigameType.SOUP))
                .replace("%streak_soup%", "" + SQLConnection.getWinstreak(player, MinigameType.SOUP)));
        winSoup.setScore(7);

        Score streakSoup = objective.getScore(lines.get(4)
                .replace("%wins_soup%", "" + SQLConnection.getWins(player, MinigameType.SOUP))
                .replace("%streak_soup%", "" + SQLConnection.getWinstreak(player, MinigameType.SOUP)));
        streakSoup.setScore(6);

        Score gladiator = objective.getScore(lines.get(5)
                .replace("%wins_soup%", "" + SQLConnection.getWins(player, MinigameType.SOUP))
                .replace("%streak_soup%", "" + SQLConnection.getWinstreak(player, MinigameType.SOUP)));
        gladiator.setScore(5);

        Score winGladiator = objective.getScore(lines.get(6)
                .replace("%wins_soup%", "" + SQLConnection.getWins(player, MinigameType.SOUP))
                .replace("%streak_soup%", "" + SQLConnection.getWinstreak(player, MinigameType.SOUP)));
        winGladiator.setScore(4);

        Score streakGladiator = objective.getScore(lines.get(7)
                .replace("%wins_soup%", "" + SQLConnection.getWins(player, MinigameType.SOUP))
                .replace("%streak_soup%", "" + SQLConnection.getWinstreak(player, MinigameType.SOUP)));
        streakGladiator.setScore(3);

        Score space2 = objective.getScore(lines.get(8)
                .replace("%wins_soup%", "" + SQLConnection.getWins(player, MinigameType.SOUP))
                .replace("%streak_soup%", "" + SQLConnection.getWinstreak(player, MinigameType.SOUP)));
        space2.setScore(2);

        Score website = objective.getScore(lines.get(9)
                .replace("%wins_soup%", "" + SQLConnection.getWins(player, MinigameType.SOUP))
                .replace("%streak_soup%", "" + SQLConnection.getWinstreak(player, MinigameType.SOUP)));
        website.setScore(1);

        player.setScoreboard(scoreboard);

    }

    public static void updateScoreboardLobbySoup(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§b§lSOPA");

        Score space1 = objective.getScore("§a");
        space1.setScore(8);

        Score wins = objective.getScore("  §fVitórias: §7" + SQLConnection.getWins(player, MinigameType.SOUP));
        wins.setScore(7);

        Score losses = objective.getScore("  §fDerrotas: §7" + SQLConnection.getLosses(player, MinigameType.SOUP));
        losses.setScore(6);

        Score space2 = objective.getScore("§b");
        space2.setScore(5);

        Score winstreak = objective.getScore("  §fWinstreak: §a" + SQLConnection.getWinstreak(player, MinigameType.SOUP));
        winstreak.setScore(4);

        Score ranking = objective.getScore("  §fRanking: §7" + SQLConnection.getRanking(player, MinigameType.SOUP));
        ranking.setScore(3);

        Score space3 = objective.getScore("§c");
        space3.setScore(2);

        Score website = objective.getScore(lines.get(9));
        website.setScore(1);

        player.setScoreboard(scoreboard);
    }

    public static void updateScoreboardWaitingSoup(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§b§lDUELS");

        Score space3 = objective.getScore("§f");
        space3.setScore(10);

        Score modo =
                objective.getScore("  §fModo: §aSopa 1v1");
        modo.setScore(9);

        Score players =
                objective.getScore("  §fJogadores: §a" +
                        DuelsPlugin.getPlugin().getArenaManager().getArena(player).getPlayers().size()
                        + "/2");
        players.setScore(8);

        Score space1 = objective.getScore("§a");
        space1.setScore(7);

        Score waiting = objective.getScore("  §fAguardando...");
        waiting.setScore(6);

        Score space2 = objective.getScore("§b");
        space2.setScore(5);

        Score ranking = objective.getScore("  §fRanking: §a" + SQLConnection.getRanking(player, MinigameType.SOUP));
        ranking.setScore(4);

        Score winstreak = objective.getScore("  §fWinstreak: §7" + SQLConnection.getWinstreak(player, MinigameType.SOUP));
        winstreak.setScore(3);

        Score space4 = objective.getScore("§c");
        space4.setScore(2);

        Score website = objective.getScore(lines.get(9));
        website.setScore(1);

        player.setScoreboard(scoreboard);
    }

    public static void updateScoreboardStartingSoup(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§b§lDUELS");

        Score space3 = objective.getScore("§f");
        space3.setScore(10);

        Score modo = objective.getScore("  §fModo: §aSopa 1v1");
        modo.setScore(9);

        Score players =
                objective.getScore("  §fJogadores: §a" +
                        DuelsPlugin.getPlugin().getArenaManager().getArena(player).getPlayers().size()
                        + "/2");
        players.setScore(8);

        Score space1 = objective.getScore("§a");
        space1.setScore(7);

        Team starting = scoreboard.registerNewTeam("starting");
        starting.addEntry("§e");
        starting.setPrefix("  §fInicia em: ");
        starting.setSuffix("§a...");
        objective.getScore("§e").setScore(6);

        Score space2 = objective.getScore("§b");
        space2.setScore(5);

        Score ranking = objective.getScore("  §fRanking: §a" + SQLConnection.getRanking(player, MinigameType.SOUP));
        ranking.setScore(4);

        Score winstreak = objective.getScore("  §fWinstreak: §7" + SQLConnection.getWinstreak(player, MinigameType.SOUP));
        winstreak.setScore(3);

        Score space4 = objective.getScore("§c");
        space4.setScore(2);

        Score website = objective.getScore(lines.get(9));
        website.setScore(1);

        player.setScoreboard(scoreboard);
    }

    public static void updateScoreboardGameSoup(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§b§lDUELS");

        Score space3 = objective.getScore("§f");
        space3.setScore(11);

        Score modo =
                objective.getScore("  §fModo: §aSopa 1v1");
        modo.setScore(10);

        Team time = scoreboard.registerNewTeam("time");
        time.addEntry("§1");
        time.setPrefix("  §fTempo: ");
        time.setSuffix("§a...");
        objective.getScore("§1").setScore(9);

        Score space1 = objective.getScore("§a");
        space1.setScore(8);

        if (DuelsPlugin.getPlugin().getArenaManager().getArena(player).getPlayers().get(0) != player.getUniqueId()) {
            Team adv = scoreboard.registerNewTeam("adversario");
            adv.addEntry("§2");
            adv.setPrefix("  §c" + Bukkit.getPlayer(DuelsPlugin.getPlugin().getArenaManager().getArena(player).getPlayers().get(0)).getName() + ":");

            CraftPlayer craftPlayer =
                    (CraftPlayer) Bukkit.getPlayer(DuelsPlugin.getPlugin().getArenaManager().getArena(player).getPlayers().get(0));
            int ping = craftPlayer.getHandle().ping;

            new BukkitRunnable() {
                @Override
                public void run() {
                    adv.setSuffix( "§7" + ping + "ms");
                }
            }.runTaskTimer(DuelsPlugin.getPlugin(), 0, 1L);

            objective.getScore("§2").setScore(7);

            Team playerScore = scoreboard.registerNewTeam("player");
            playerScore.addEntry("§3");
            playerScore.setPrefix("  §9" + player.getName() + ":");

            CraftPlayer craftPlayerPl = (CraftPlayer) player;
            int pingPl = craftPlayerPl.getHandle().ping;

            new BukkitRunnable() {
                @Override
                public void run() {
                    playerScore.setSuffix(" §7" + pingPl + "ms");
                }
            }.runTaskTimer(DuelsPlugin.getPlugin(), 0, 1L);

            objective.getScore("§3").setScore(6);

        }

        if (DuelsPlugin.getPlugin().getArenaManager().getArena(player).getPlayers().get(1) != player.getUniqueId()) {
            Team adv = scoreboard.registerNewTeam("adversario");
            adv.addEntry("§2");
            adv.setPrefix("   §c" + Bukkit.getPlayer(DuelsPlugin.getPlugin().getArenaManager().getArena(player).getPlayers().get(1)).getName() + ":");

            CraftPlayer craftPlayer =
                    (CraftPlayer) Bukkit.getPlayer(DuelsPlugin.getPlugin().getArenaManager().getArena(player).getPlayers().get(1));
            int ping = craftPlayer.getHandle().ping;

            new BukkitRunnable() {
                @Override
                public void run() {
                    adv.setSuffix(" §7" + ping + "ms");
                }
            }.runTaskTimer(DuelsPlugin.getPlugin(), 0, 1L);
            objective.getScore("§2").setScore(7);

            Team playerScore = scoreboard.registerNewTeam("player");
            playerScore.addEntry("§3");
            playerScore.setPrefix("  §9" + player.getName() + ":");

            CraftPlayer craftPlayerPl = (CraftPlayer) player;
            int pingPl = craftPlayerPl.getHandle().ping;

            new BukkitRunnable() {
                @Override
                public void run() {
                    playerScore.setSuffix(" §7" + pingPl + "ms");
                }
            }.runTaskTimer(DuelsPlugin.getPlugin(), 0, 1L);

            objective.getScore("§3").setScore(6);

        }

        Score space2 = objective.getScore("§b");
        space2.setScore(5);

        Score ranking = objective.getScore("  §fRanking: §a" + SQLConnection.getRanking(player, MinigameType.SOUP));
        ranking.setScore(4);

        Score winstreak = objective.getScore("  §fWinstreak: §7" + SQLConnection.getWinstreak(player, MinigameType.SOUP));
        winstreak.setScore(3);

        Score space4 = objective.getScore("§c");
        space4.setScore(2);

        Score website = objective.getScore(lines.get(9));
        website.setScore(1);

        player.setScoreboard(scoreboard);
    }

}
