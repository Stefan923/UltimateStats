package me.Stefan923.UltimateStats.Listeners;

import me.Stefan923.UltimateStats.UltimateStats;
import me.Stefan923.UltimateStats.Utils.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryClickListener implements Listener {
    UltimateStats instance;

    public InventoryClickListener(UltimateStats instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        User user = instance.getUser(player);
        if (!user.isWatching() || event.getClickedInventory().equals(player.getInventory())) {
            return;
        }

        user.getStatsInventory().action(player, user, user.getInventoryPage() * 54 + event.getSlot());

        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        User user = instance.getUser(player);
        if (!user.isWatching()) {
            return;
        }

        event.setCancelled(true);
    }
}
