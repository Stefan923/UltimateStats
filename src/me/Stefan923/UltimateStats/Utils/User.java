package me.Stefan923.UltimateStats.Utils;

import me.Stefan923.UltimateStats.Inventory.StatsInventory;
import org.bukkit.entity.Player;

public class User {
    Player player;

    private boolean watching;

    private StatsInventory statsInventory;
    private Integer inventoryPage;

    public User(Player player) {
        this.player = player;
        this.statsInventory = null;
        this.inventoryPage = 0;
        this.watching = false;
    }

    public void setWatching(boolean watching) {
        this.watching = watching;
    }

    public boolean isWatching() {
        return watching;
    }

    public StatsInventory getStatsInventory() {
        return statsInventory;
    }

    public void setStatsInventory(StatsInventory statsInventory) {
        this.statsInventory = statsInventory;
    }

    public Integer getInventoryPage() {
        return inventoryPage;
    }

    public void setInventoryPage(Integer inventoryPage) {
        this.inventoryPage = inventoryPage;
    }
}
