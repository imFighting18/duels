package me.imfighting.duels.instance;

import me.imfighting.duels.DuelsPlugin;
import me.imfighting.duels.GameState;
import me.imfighting.duels.MinigameType;
import me.imfighting.duels.listeners.LoadListeners;
import me.imfighting.duels.managers.PlayerManager;
import me.imfighting.duels.managers.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.management.ObjectName;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

    private DuelsPlugin duelsPlugin;

    private int id;
    private Location spawn;

    private GameState state;
    private MinigameType minigameType;
    private List<UUID> players;
    private Countdown countdown;
    private Game game;

    public Arena(DuelsPlugin duelsPlugin, int id, Location spawn) {
        this.duelsPlugin = duelsPlugin;
        this.id = id;
        this.spawn = spawn;

        this.state = GameState.RECRUITING;
        this.minigameType = MinigameType.NONE;
        this.players = new ArrayList<>();
        this.countdown = new Countdown(duelsPlugin, this);
        this.game = new Game(this);
    }

    public void start() {
        game.start();
    }

    public void reset(boolean kickPlayers) {

        if (kickPlayers) {
            for (UUID uuid : players) {
                PlayerManager.updatePlayerOnReset(Bukkit.getPlayer(uuid));
            }
            players.clear();

        }

        sendTitle("", "");
        state = GameState.RECRUITING;
        minigameType = MinigameType.NONE;
        countdown.cancel();
        countdown = new Countdown(duelsPlugin, this);
        game = new Game(this);

    }


    public void sendMessage(String message) {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).sendMessage(message);
        }
    }

    public void sendTitle(String title, String subtitle) {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).sendTitle(title, subtitle);
        }
    }


    public void addPlayer(Player player) {
        players.add(player.getUniqueId());
        player.teleport(spawn);
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        ScoreboardManager.updateScoreboardWaitingSoup(player);
        sendMessage("§7" + player.getName() + " §eentrou no jogo. §a(" + getPlayers().size() + "/2)");

        for (Player online : Bukkit.getOnlinePlayers()) {
            if (!players.contains(online)) {
                player.hidePlayer(online);
                online.hidePlayer(player);
                Bukkit.getPlayer(players.get(0)).showPlayer(Bukkit.getPlayer(players.get(1)));
                Bukkit.getPlayer(players.get(1)).showPlayer(Bukkit.getPlayer(players.get(0)));
            }
        }

        if (state.equals(GameState.RECRUITING) && players.size() == 2) {
            countdown.start();
        }

    }

    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
        LoadListeners.joinSoupLobby(player);
        player.sendTitle("", "");

        if (state == GameState.COUNTDOWN && players.size() < 2) {
            sendMessage("§cJogadores insuficientes para começar a partida.");

            for (UUID uuid : getPlayers()) {
                Bukkit.getPlayer(uuid).setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                ScoreboardManager.updateScoreboardWaitingSoup(Bukkit.getPlayer(uuid));
            }
            reset(false);
            return;
        }

        if (state == GameState.LIVE && players.size() < 2) {
            sendMessage("§cO jogo acabou por ter poucos jogadores.");
            reset(true);
        }
    }

    public int getId() {
        return id;
    }

    public GameState getState() {
        return state;
    }

    public List<UUID> getPlayers() {
        return players;
    }

    public Game getGame() {
        return game;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public MinigameType getMinigameType() {
        return minigameType;
    }

    public void setMinigameType(MinigameType minigameType) {
        this.minigameType = minigameType;
    }
}
