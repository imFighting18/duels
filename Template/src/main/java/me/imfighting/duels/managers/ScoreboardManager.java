package me.imfighting.duels.managers;

import me.imfighting.duels.DuelsPlugin;
import me.imfighting.duels.util.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;

public class ScoreboardManager {

    static final ConfigUtil config = DuelsPlugin.getPlugin().getConfig();
    static final List<String> lines = config.getStringList("scoreboard");

    public static void updateScoreboard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(lines.get(0));

        Score space1 = objective.getScore(lines.get(1));
        space1.setScore(9);

        Score soup = objective.getScore(lines.get(2));
        soup.setScore(8);

        Score winSoup = objective.getScore(lines.get(3));
        winSoup.setScore(7);

        Score streakSoup = objective.getScore(lines.get(4));
        streakSoup.setScore(6);

        Score gladiator = objective.getScore(lines.get(5));
        gladiator.setScore(5);

        Score winGladiator = objective.getScore(lines.get(6));
        winGladiator.setScore(4);

        Score streakGladiator = objective.getScore(lines.get(7));
        streakGladiator.setScore(3);

        Score space2 = objective.getScore(lines.get(8));
        space2.setScore(2);

        Score website = objective.getScore(lines.get(9));
        website.setScore(1);

        player.setScoreboard(scoreboard);

    }
}
