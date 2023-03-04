package me.imfighting.duels.database;

import me.imfighting.duels.DuelsPlugin;
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
        try {
            stm = con.prepareStatement("CREATE TABLE IF NOT EXISTS `npc_locations` (`minigame` TEXT, `world` TEXT, " +
                    "`X` float, `Y` " +
                    "float, `Z` float)");
            stm.execute();
            stm.close();
            Bukkit.getConsoleSender().sendMessage("§aTabela de NPCs criada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§cErro ao criar a tabela.");
            plugin.getPluginLoader().disablePlugin(plugin);
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
