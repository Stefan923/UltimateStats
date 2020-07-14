package me.Stefan923.UltimateStats.Inventory;

import me.Stefan923.UltimateStats.Configuration.InventorySettingsManager;
import me.Stefan923.UltimateStats.UltimateStats;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryManager {
    private UltimateStats instance;

    private InventorySettingsManager inventorySettingsManager;

    private List<StatsInventory> statsInventories;

    public InventoryManager(UltimateStats instance) {
        this.instance = instance;

        inventorySettingsManager = InventorySettingsManager.getInstance();
        inventorySettingsManager.setup(instance);
        statsInventories = new ArrayList<>();
    }

    public StatsInventory getInventory(Player player) {
        List<StatsInventory> list = statsInventories.stream().filter(statsInventory -> statsInventory.player.getName().equalsIgnoreCase(player.getName())).collect(Collectors.toList());

        return list.isEmpty() ? new StatsInventory(this, player) : list.get(0);
    }

    public void addInventory(StatsInventory statsInventory) {
        statsInventories.add(statsInventory);
    }

    public void removeInventory(StatsInventory statsInventory) {
        statsInventories.remove(statsInventory);
    }

    public InventorySettingsManager getInventorySettingsManager() {
        return inventorySettingsManager;
    }

    public void closeAll() {
        statsInventories.forEach(StatsInventory::closeForAll);
    }
}
