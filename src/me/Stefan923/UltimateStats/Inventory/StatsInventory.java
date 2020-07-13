package me.Stefan923.UltimateStats.Inventory;

import me.Stefan923.UltimateStats.Utils.ItemUtils;
import me.Stefan923.UltimateStats.Utils.MessageUtils;
import me.Stefan923.UltimateStats.Utils.User;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StatsInventory implements MessageUtils, ItemUtils {
    StatsInventory instance;
    Player player;

    InventoryManager inventoryManager;

    private List<Inventory> inventories;
    private HashMap<Integer, String> actions;
    private List<String> watchers;

    public StatsInventory(InventoryManager inventoryManager, Player player) {
        this.inventoryManager = inventoryManager;
        this.player = player;
        this.inventories = new ArrayList<>();
        this.watchers = new ArrayList<>();
        this.actions = new HashMap<>();
        this.instance = this;

        build();

        inventoryManager.addInventory(this);
    }

    public StatsInventory getInstance() {
        return instance;
    }

    public void build() {
        FileConfiguration config = inventoryManager.getInventorySettingsManager().getConfig();

        for (int page = 0; page < config.getInt("Stats Menu Settings.Pages"); page++) {
            Inventory inventory = Bukkit.createInventory(null, config.getInt("Stats Menu Settings.Per Page Slots"), formatAll(config.getString("Stats Menu Settings.Title").replace("%page%", String.valueOf(page + 1)).replace("%player_name%", player.getName())));

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
            if (configurationSection.getString("Item Type").equalsIgnoreCase("open-page")) {
                actions.put(configurationSection.getInt("Page") + configurationSection.getInt("Slot"), "open " + configurationSection.getInt("Next Page"));
            }
        }
    }

    public void open(Player player, User user, int page) {
        if (inventories.size() <= page) {
            return;
        }

        String playerName = player.getName();
        if (!watchers.contains(playerName)) {
            watchers.add(playerName);
        }

        player.openInventory(inventories.get(page));
        user.setWatching(true);
        user.setInventoryPage(page);
        user.setStatsInventory(this);
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

    public void action(Player player, User user, int key) {
        if (actions.containsKey(key)) {
            String action = actions.get(key);
            if (action.startsWith("open")) {
                player.closeInventory();
                open(player, user, Integer.parseInt(action.replace("open ", "")));
            }
        }
    }
}
