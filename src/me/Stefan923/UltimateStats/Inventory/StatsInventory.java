package me.Stefan923.UltimateStats.Inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class StatsInventory {
    InventoryManager inventoryManager;

    private List<Inventory> inventories;
    private List<String> watchers;

    public StatsInventory(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
        inventories = new ArrayList<>();
        watchers = new ArrayList<>();
    }

    public void closeForAll() {
        watchers.forEach(playerName -> {
            Player player = Bukkit.getPlayer(playerName);
            if (player != null) {
                player.closeInventory();
            }
        });
        inventoryManager.removeInventory(this);
    }

    public void close(Player player) {
        String playerName = player.getName();
        if (watchers.contains(playerName)) {
            player.closeInventory();
            watchers.remove(playerName);
        }

        if (watchers.isEmpty()) {
            inventoryManager.removeInventory(this);
        }
    }
}
