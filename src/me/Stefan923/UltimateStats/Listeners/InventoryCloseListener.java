package me.Stefan923.UltimateStats.Listeners;

import me.Stefan923.UltimateStats.UltimateStats;
import me.Stefan923.UltimateStats.Utils.User;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {
    UltimateStats instance;

    public InventoryCloseListener(UltimateStats instance) {
        this.instance = instance;
    }
    public void onInventoryClose(InventoryCloseEvent event) {
        User user = instance.getUser((Player) event.getPlayer());

        if (user.isWatching()) {
            user.setWatching(false);
            user.getStatsInventory().closed((Player) event.getPlayer());
        }
    }
}
