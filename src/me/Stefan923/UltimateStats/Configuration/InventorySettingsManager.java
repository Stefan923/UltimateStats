package me.Stefan923.UltimateStats.Configuration;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class InventorySettingsManager {

    private static InventorySettingsManager instance = new InventorySettingsManager();
    private FileConfiguration config;
    private File cfile;

    public static InventorySettingsManager getInstance() {
        return instance;
    }

    public void setup(Plugin p) {
        cfile = new File(p.getDataFolder(), "stats-menu.yml");
        config = YamlConfiguration.loadConfiguration(cfile);
        config.options().header("UltimateStats by Stefan923\n");
        config.addDefault("Stats Menu Settings.Pages", 1);
        config.addDefault("Stats Menu Settings.Per Page Slots", 54);
        config.addDefault("Fill Empty Slots.Enabled", true);
        config.addDefault("Fill Empty Slots.Item.Display Name", "&bStats");
        config.addDefault("Fill Empty Slots.Item.Material", "STAINED_GLASS_PANE");
        config.addDefault("Fill Empty Slots.Item.Data", 15);
        config.addDefault("Fill Empty Slots.Item.Lore", Arrays.asList("", "&7&o- UltimateStats"));
        config.addDefault("Fill Empty Slots.Item.Item Action", "COMMAND");
        config.addDefault("Fill Empty Slots.Item.Commands", Collections.singletonList("/say You're seeing someone's stats."));
        config.addDefault("Stats Menu Items.1-hand-item.Item Type", "player-hand");
        config.addDefault("Stats Menu Items.1-helmet.Slot", 1);
        config.addDefault("Stats Menu Items.1-helmet.Page", 1);
        config.addDefault("Stats Menu Items.2-helmet.Item Type", "player-helmet");
        config.addDefault("Stats Menu Items.2-helmet.Slot", 10);
        config.addDefault("Stats Menu Items.2-helmet.Page", 1);
        config.addDefault("Stats Menu Items.3-chestplate.Item Type", "player-chestplate");
        config.addDefault("Stats Menu Items.3-chestplate.Slot", 19);
        config.addDefault("Stats Menu Items.3-chestplate.Page", 1);
        config.addDefault("Stats Menu Items.4-leggings.Item Type", "player-leggings");
        config.addDefault("Stats Menu Items.4-leggings.Slot", 28);
        config.addDefault("Stats Menu Items.4-leggings.Page", 1);
        config.addDefault("Stats Menu Items.5-boots.Item Type", "player-boots");
        config.addDefault("Stats Menu Items.5-boots.Slot", 37);
        config.addDefault("Stats Menu Items.5-boots.Page", 1);
        config.options().copyDefaults(true);
        save();
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void resetConfig() {
        config.set("Stats Menu Settings.Pages", 1);
        config.set("Stats Menu Settings.Per Page Slots", 54);
        config.set("Fill Empty Slots.Enabled", true);
        config.set("Fill Empty Slots.Item.Display Name", "&bStats");
        config.set("Fill Empty Slots.Item.Material", "STAINED_GLASS_PANE");
        config.set("Fill Empty Slots.Item.Data", 15);
        config.set("Fill Empty Slots.Item.Lore", Arrays.asList("", "&7&o- UltimateStats"));
        config.set("Fill Empty Slots.Item.Item Action", "COMMAND");
        config.set("Fill Empty Slots.Item.Commands", Collections.singletonList("/say You're seeing someone's stats."));
        config.set("Stats Menu Items.1-hand-item.Item Type", "player-hand");
        config.set("Stats Menu Items.1-helmet.Slot", 1);
        config.set("Stats Menu Items.1-helmet.Page", 1);
        config.set("Stats Menu Items.2-helmet.Item Type", "player-helmet");
        config.set("Stats Menu Items.2-helmet.Slot", 10);
        config.set("Stats Menu Items.2-helmet.Page", 1);
        config.set("Stats Menu Items.3-chestplate.Item Type", "player-chestplate");
        config.set("Stats Menu Items.3-chestplate.Slot", 19);
        config.set("Stats Menu Items.3-chestplate.Page", 1);
        config.set("Stats Menu Items.4-leggings.Item Type", "player-leggings");
        config.set("Stats Menu Items.4-leggings.Slot", 28);
        config.set("Stats Menu Items.4-leggings.Page", 1);
        config.set("Stats Menu Items.5-boots.Item Type", "player-boots");
        config.set("Stats Menu Items.5-boots.Slot", 37);
        config.set("Stats Menu Items.5-boots.Page", 1);
        save();
    }

    private void save() {
        try {
            config.save(cfile);
        } catch (IOException e) {
            Bukkit.getLogger().severe(ChatColor.RED + "File 'stats-menu.yml' couldn't be saved!");
        }
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(cfile);
    }

}
