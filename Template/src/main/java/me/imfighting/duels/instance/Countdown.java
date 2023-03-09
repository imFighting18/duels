package me.imfighting.duels.instance;

import me.imfighting.duels.DuelsPlugin;
import me.imfighting.duels.GameState;
import me.imfighting.duels.managers.GameManager;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    private DuelsPlugin minigames;
    private Arena arena;
    private int countdownSeconds;

    public Countdown(DuelsPlugin minigames, Arena arena) {
        this.minigames = minigames;
        this.arena = arena;
        this.countdownSeconds = 5;
    }

    public void start() {
        arena.setState(GameState.COUNTDOWN);
        runTaskTimer(minigames, 0, 20);
    }

    @Override
    public void run() {
        if (countdownSeconds == 0) {
            cancel();
            arena.start();
            return;
        }

        arena.sendTitle("§a" + countdownSeconds + " segundo" + (countdownSeconds == 1 ? "" : "s"), "irá começar.");


        countdownSeconds--;

    }
}
