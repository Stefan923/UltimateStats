package me.Stefan923.UltimateStats.Listeners;

import me.Stefan923.UltimateStats.UltimateStats;
import me.Stefan923.UltimateStats.Utils.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private UltimateStats instance;

    public PlayerQuitListener(UltimateStats instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        User user = instance.getUser(player);

        if (user.isWatching()) {
            user.getStatsInventory().closeFor(player);
        }

        instance.removeUser(event.getPlayer());
    }
}
