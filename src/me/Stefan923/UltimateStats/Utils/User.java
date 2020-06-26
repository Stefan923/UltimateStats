package me.Stefan923.UltimateStats.Utils;

import me.Stefan923.UltimateStats.Inventory.StatsInventory;
import org.bukkit.entity.Player;

public class User {
    Player player;

    private boolean watching;

    private StatsInventory statsInventory;

    public User(Player player) {
        this.player = player;
        this.statsInventory = null;
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
}
