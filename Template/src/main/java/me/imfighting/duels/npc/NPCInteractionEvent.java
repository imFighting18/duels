package me.imfighting.duels.npc;

import lombok.Getter;
import me.imfighting.duels.npc.NPCClickAction;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class NPCInteractionEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    @Getter
    private final NPCs clicked;

    @Getter
    private final Player player;

    @Getter
    private final NPCClickAction clickAction;

    public NPCInteractionEvent(NPCs clicked, Player player, NPCClickAction clickAction) {
        this.clicked = clicked;
        this.player = player;
        this.clickAction = clickAction;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
