package me.Stefan923.UltimateStats.Listeners;

import me.Stefan923.UltimateStats.UltimateStats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener {
    private UltimateStats instance;

    public PlayerQuitListener(UltimateStats instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        instance.removeUser(event.getPlayer());
    }
}
