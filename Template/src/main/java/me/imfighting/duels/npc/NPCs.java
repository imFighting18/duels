package me.imfighting.duels.npc;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface NPCs {
    String getName();
    void showTo(Player player);
    void hideFrom(Player player);
    void delete();
    Location getLocation();
    int getId();
}
