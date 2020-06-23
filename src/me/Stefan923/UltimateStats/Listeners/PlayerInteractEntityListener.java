package me.Stefan923.UltimateStats.Listeners;

import me.Stefan923.UltimateStats.Inventory.InventoryManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractEntityListener implements Listener {
    InventoryManager inventoryManager;

    public PlayerInteractEntityListener(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();

        if (!(entity instanceof Player)) {
            return;
        }

        Player target = (Player) entity;
        inventoryManager.getInventory(target).open(event.getPlayer(), 0);

    }
}
