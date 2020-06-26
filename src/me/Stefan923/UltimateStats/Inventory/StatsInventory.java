package me.Stefan923.UltimateStats.Inventory;

import me.Stefan923.UltimateStats.Utils.ItemUtils;
import me.Stefan923.UltimateStats.Utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class StatsInventory implements MessageUtils, ItemUtils {
    Player player;

    InventoryManager inventoryManager;

    private List<Inventory> inventories;
    private List<String> watchers;

    public StatsInventory(InventoryManager inventoryManager, Player player) {
        this.inventoryManager = inventoryManager;
        this.player = player;
        inventories = new ArrayList<>();
        watchers = new ArrayList<>();

        build();

        inventoryManager.addInventory(this);
    }

    public void build() {
        FileConfiguration config = inventoryManager.getInventorySettingsManager().getConfig();

        for (int page = 0; page < config.getInt("Stats Menu Settings.Pages"); page++) {
            Inventory inventory = Bukkit.createInventory(null, config.getInt("Stats Menu Settings.Per Page Slots"), formatAll(config.getString("Stats Menu Settings.Title").replace("{page}", String.valueOf(page + 1)).replace("{player}", player.getName())));

            if (config.getBoolean("Fill Empty Slots.Enabled")) {
                ItemStack fillItem = configToFillItemStack(config.getConfigurationSection("Fill Empty Slots.Item"), player);
                for (int slot = 0; slot < config.getInt("Stats Menu Settings.Per Page Slots"); slot++) {
                    inventory.setItem(slot, fillItem);
                }
            }

            inventories.add(inventory);
        }

        for (String key : config.getConfigurationSection("Stats Menu Items").getKeys(false)) {
            ConfigurationSection configurationSection = config.getConfigurationSection("Stats Menu Items." + key);
            ItemStack itemStack = configToItemStack(configurationSection, player);

            inventories.get(configurationSection.getInt("Page")).setItem(configurationSection.getInt("Slot"), itemStack);
        }
    }

    public void open(Player player, int page) {
        if (inventories.size() <= page) {
            return;
        }

        player.openInventory(inventories.get(page));

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

    public void closeFor(Player player) {
        String playerName = player.getName();
        if (watchers.contains(playerName)) {
            player.closeInventory();
            watchers.remove(playerName);
        }

        if (watchers.isEmpty()) {
            inventoryManager.removeInventory(this);
        }
    }

    public void closed(Player player) {
        String playerName = player.getName();
        watchers.remove(playerName);

        if (watchers.isEmpty()) {
            inventoryManager.removeInventory(this);
        }
    }
}
