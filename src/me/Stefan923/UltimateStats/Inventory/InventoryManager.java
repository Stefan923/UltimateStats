package me.Stefan923.UltimateStats.Inventory;

import me.Stefan923.UltimateStats.UltimateStats;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private UltimateStats instance;

    private List<StatsInventory> statsInventories;

    public InventoryManager(UltimateStats instance) {
        this.instance = instance;

        statsInventories = new ArrayList<>();
    }

    public void addInventory(StatsInventory statsInventory) {
        statsInventories.add(statsInventory);
    }

    public void removeInventory(StatsInventory statsInventory) {
        statsInventories.remove(statsInventory);
    }
}
