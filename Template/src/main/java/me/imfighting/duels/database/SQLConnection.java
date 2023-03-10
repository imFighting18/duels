package me.imfighting.duels.database;

import me.imfighting.duels.DuelsPlugin;
import me.imfighting.duels.MinigameType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.sql.*;

public class SQLConnection {

    static final DuelsPlugin plugin = DuelsPlugin.getPlugin();
    static Connection con = null;

    public static void openConnection() {
        File file = new File(plugin.getDataFolder(), "database.db");
        String url = "jdbc:sqlite:" + file;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(url);
            createTable();
            Bukkit.getConsoleSender().sendMessage("§aConexão SQLite feita.");
        } catch (Exception e) {
            e.printStackTrace();
            plugin.getPluginLoader().disablePlugin(plugin);
        }
    }
    
    public static void createTable() {
        PreparedStatement stm = null;
        PreparedStatement stm2 = null;
        PreparedStatement stm3 = null;
        try {
            stm = con.prepareStatement("CREATE TABLE IF NOT EXISTS `npc_locations` (`minigame` TEXT, `world` TEXT, " +
                    "`X` float, `Y` " +
                    "float, `Z` float)");
            stm.execute();
            stm.close();

            stm2 = con.prepareStatement("CREATE TABLE IF NOT EXISTS `npc_locations_play` (`minigame` TEXT, `world` " +
                    "TEXT, " +
                    "`X` float, `Y` " +
                    "float, `Z` float)");
            stm2.execute();
            stm2.close();

            stm3 = con.prepareStatement("CREATE TABLE IF NOT EXISTS `players_soup` (`uuid` TEXT, `wins` " +
                    "TEXT, " +
                    "`losses` TEXT, `xp` float, `winstreak` " +
                    "TEXT, `ranking` TEXT)");
            stm3.execute();
            stm3.close();

            Bukkit.getConsoleSender().sendMessage("§aTabela de NPCs Jogar criada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§cErro ao criar a tabela.");
            plugin.getPluginLoader().disablePlugin(plugin);
        }
    }

    public static boolean containsPlayer(Player player, MinigameType minigameType) {
        PreparedStatement stm = null;

        if (minigameType == MinigameType.SOUP) {
            try {
                stm = con.prepareStatement("SELECT * FROM `players_soup` WHERE `uuid` = ?");
                stm.setString(1, player.getUniqueId().toString());
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void createPlayer(Player player, MinigameType minigameType) {
        PreparedStatement stm = null;

        if (minigameType == MinigameType.SOUP) {
            try {
                stm = con.prepareStatement("INSERT INTO `players_soup`(" +
                        "`uuid`, " +
                        "`wins`, " +
                        "`losses`, " +
                        "`xp`, " +
                        "`winstreak`, " +
                        "`ranking`" +
                        ") " +
                        "VALUES (" +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?" +
                        ")");
                stm.setString(1, player.getUniqueId().toString());
                stm.setInt(2, 0);
                stm.setInt(3, 0);
                stm.setDouble(4, 0);
                stm.setInt(5, 0);
                stm.setString(6, "Soldier I");
                stm.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static int getWins(Player player, MinigameType minigameType) {
        PreparedStatement stm = null;

        int win = 0;

        if (minigameType == MinigameType.SOUP) {
            try {
                stm = con.prepareStatement("SELECT * FROM `players_soup` WHERE `uuid` = ?");
                stm.setString(1, player.getUniqueId().toString());
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    win = rs.getInt("wins");
                }
            } catch (SQLException e) {
                return 0;
            }
        }
        return win;
    }

    public static void addWins(Player player, MinigameType minigameType) {
        PreparedStatement stm = null;

        if (minigameType == MinigameType.SOUP) {
            try {
                stm = con.prepareStatement("UPDATE `players_soup` SET wins = ? WHERE uuid = ?");
                stm.setInt(1, getWins(player, MinigameType.SOUP) + 1);
                stm.setString(2, player.getUniqueId().toString());
                stm.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getLosses(Player player, MinigameType minigameType) {
        PreparedStatement stm = null;

        int losses = 0;

        if (minigameType == MinigameType.SOUP) {
            try {
                stm = con.prepareStatement("SELECT * FROM `players_soup` WHERE `uuid` = ?");
                stm.setString(1, player.getUniqueId().toString());
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    losses = rs.getInt("losses");
                }
            } catch (SQLException e) {
                return 0;
            }
        }
        return losses;
    }

    public static void addLosses(Player player, MinigameType minigameType) {
        PreparedStatement stm = null;

        if (minigameType == MinigameType.SOUP) {
            try {
                stm = con.prepareStatement("UPDATE `players_soup` SET losses = ? WHERE uuid = ?");
                stm.setInt(1, getLosses(player, MinigameType.SOUP) + 1);
                stm.setString(2, player.getUniqueId().toString());
                stm.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static int getWinstreak(Player player, MinigameType minigameType) {
        PreparedStatement stm = null;

        int winstreak = 0;

        if (minigameType == MinigameType.SOUP) {
            try {
                stm = con.prepareStatement("SELECT * FROM `players_soup` WHERE `uuid` = ?");
                stm.setString(1, player.getUniqueId().toString());
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    winstreak = rs.getInt("winstreak");
                }
            } catch (SQLException e) {
                return 0;
            }
        }
        return winstreak;
    }

    public static String getRanking(Player player, MinigameType minigameType) {
        PreparedStatement stm = null;

        String ranking = "";

        if (minigameType == MinigameType.SOUP) {
            try {
                stm = con.prepareStatement("SELECT * FROM `players_soup` WHERE `uuid` = ?");
                stm.setString(1, player.getUniqueId().toString());
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    ranking = rs.getString("ranking");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ranking;
    }

    public static void addWinstreak(Player player, MinigameType minigameType) {
        PreparedStatement stm = null;

        if (minigameType == MinigameType.SOUP) {
            try {
                stm = con.prepareStatement("UPDATE `players_soup` SET winstreak = ? WHERE uuid = ?");
                stm.setInt(1, getWinstreak(player, MinigameType.SOUP) + 1);
                stm.setString(2, player.getUniqueId().toString());
                stm.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void removeWinstreak(Player player, MinigameType minigameType) {
        PreparedStatement stm = null;

        if (minigameType == MinigameType.SOUP) {
            try {
                stm = con.prepareStatement("UPDATE `players_soup` SET winstreak = ? WHERE uuid = ?");
                stm.setInt(1, 0);
                stm.setString(2, player.getUniqueId().toString());
                stm.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    public static void setLocation(Player player, String minigame) {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO `npc_locations`(`minigame`, `world`, `X`, `Y`, `Z`) VALUES (?,?," +
                    "?," +
                    "?,?)");
            stm.setString(1, minigame);
            stm.setString(2, player.getWorld().getName());
            stm.setDouble(3, player.getLocation().getX());
            stm.setDouble(4, player.getLocation().getY());
            stm.setDouble(5, player.getLocation().getZ());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setLocationPlay(Player player, String minigame) {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO `npc_locations_play`(`minigame`, `world`, `X`, `Y`, `Z`) VALUES " +
                    "(?,?," +
                    "?," +
                    "?,?)");
            stm.setString(1, minigame);
            stm.setString(2, player.getWorld().getName());
            stm.setDouble(3, player.getLocation().getX());
            stm.setDouble(4, player.getLocation().getY());
            stm.setDouble(5, player.getLocation().getZ());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean containsNPC(String minigame) {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("SELECT * FROM `npc_locations` WHERE `minigame` = ?");
            stm.setString(1, minigame);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean containsNPCPlay(String minigame) {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("SELECT * FROM `npc_locations_play` WHERE `minigame` = ?");
            stm.setString(1, minigame);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    public static double getLocationNPC(String minigame, String location) {
        double x = 0;
        double y = 0;
        double z = 0;

        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("SELECT X, Y, Z FROM `npc_locations` WHERE `minigame` = ?");
            stm.setString(1, minigame);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                x = rs.getDouble("X");
                y = rs.getDouble("Y");
                z = rs.getDouble("Z");
            }

            if (location == "x") {
                return x;
            }

            if (location == "y") {
                return y;
            }

            if (location == "z") {
                return z;
            }



        } catch (SQLException e) {
            return 0;
        }
        return x;
    }

    public static double getLocationNPCPlay(String minigame, String location) {
        double x = 0;
        double y = 0;
        double z = 0;

        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("SELECT X, Y, Z FROM `npc_locations_play` WHERE `minigame` = ?");
            stm.setString(1, minigame);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                x = rs.getDouble("X");
                y = rs.getDouble("Y");
                z = rs.getDouble("Z");
            }

            if (location == "x") {
                return x;
            }

            if (location == "y") {
                return y;
            }

            if (location == "z") {
                return z;
            }



        } catch (SQLException e) {
            return 0;
        }
        return x;
    }

    public static String getLocationNPCWorld(String minigame) {
        String world = null;

        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("SELECT world FROM `npc_locations` WHERE `minigame` = ?");
            stm.setString(1, minigame);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                world = rs.getString("world");
            }
        } catch (SQLException e) {
            return null;
        }
        return world;
    }

    public static String getLocationNPCPlayWorld(String minigame) {
        String world = null;

        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("SELECT world FROM `npc_locations_play` WHERE `minigame` = ?");
            stm.setString(1, minigame);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                world = rs.getString("world");
            }
        } catch (SQLException e) {
            return null;
        }
        return world;
    }

    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
                con = null;
                Bukkit.getConsoleSender().sendMessage("§aConexão com o banco de dados fechada com sucesso.");
            } catch (SQLException e) {
                e.printStackTrace();
                Bukkit.getConsoleSender().sendMessage("§cNão foi possível fechar a conexão.");
            }
        }
    }

}
