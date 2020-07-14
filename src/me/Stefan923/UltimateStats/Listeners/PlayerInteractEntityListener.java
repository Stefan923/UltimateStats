package me.Stefan923.UltimateStats.Listeners;

import me.Stefan923.UltimateStats.Inventory.InventoryManager;
import me.Stefan923.UltimateStats.UltimateStats;
import me.Stefan923.UltimateStats.Utils.User;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractEntityListener implements Listener {
    UltimateStats instance;
    InventoryManager inventoryManager;

    public PlayerInteractEntityListener(UltimateStats instance, InventoryManager inventoryManager) {
        this.instance = instance;
        this.inventoryManager = inventoryManager;
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();

        if (!(entity instanceof Player)) {
            return;
        }

        Player target = (Player) entity;
        Player player = event.getPlayer();
        User user = instance.getUser(player);
        inventoryManager.getInventory(target).open(player, user, 0);
    }
}
