package me.Stefan923.UltimateStats.Listeners;

import me.Stefan923.UltimateStats.UltimateStats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private UltimateStats instance;

    public PlayerJoinListener(UltimateStats instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        instance.addUser(event.getPlayer());
    }
}
