package me.Stefan923.UltimateStats.Configuration;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
        boolean exists = cfile.exists();
        config = YamlConfiguration.loadConfiguration(cfile);

        config.options().header("UltimateStats by Stefan923\n");
        config.addDefault("Stats Menu Settings.Pages", 2);
        config.addDefault("Stats Menu Settings.Per Page Slots", 54);
        config.addDefault("Stats Menu Settings.Title", "&8%player_name%'s Profile");
        config.addDefault("Fill Empty Slots.Enabled", true);

        if (!exists) {
            config.addDefault("Fill Empty Slots.Item.Display Name", " ");
            config.addDefault("Fill Empty Slots.Item.Material", "STAINED_GLASS_PANE");
            config.addDefault("Fill Empty Slots.Item.Data", 15);
            config.addDefault("Fill Empty Slots.Item.Lore", Collections.singletonList(""));
            config.addDefault("Empty Player Slot.Item.Display Name", "&cEmpty!");
            config.addDefault("Empty Player Slot.Item.Material", "STAINED_GLASS_PANE");
            config.addDefault("Empty Player Slot.Item.Data", 0);
            config.addDefault("Empty Player Slot.Item.Lore", Collections.singletonList(""));
            config.addDefault("Stats Menu Items.1-hand-item.Item Type", "player-hand");
            config.addDefault("Stats Menu Items.1-hand-item.Slot", 1);
            config.addDefault("Stats Menu Items.1-hand-item.Page", 0);
            config.addDefault("Stats Menu Items.2-helmet.Item Type", "player-helmet");
            config.addDefault("Stats Menu Items.2-helmet.Slot", 10);
            config.addDefault("Stats Menu Items.2-helmet.Page", 0);
            config.addDefault("Stats Menu Items.3-chestplate.Item Type", "player-chestplate");
            config.addDefault("Stats Menu Items.3-chestplate.Slot", 19);
            config.addDefault("Stats Menu Items.3-chestplate.Page", 0);
            config.addDefault("Stats Menu Items.4-leggings.Item Type", "player-leggings");
            config.addDefault("Stats Menu Items.4-leggings.Slot", 28);
            config.addDefault("Stats Menu Items.4-leggings.Page", 0);
            config.addDefault("Stats Menu Items.5-boots.Item Type", "player-boots");
            config.addDefault("Stats Menu Items.5-boots.Slot", 37);
            config.addDefault("Stats Menu Items.5-boots.Page", 0);
            config.addDefault("Stats Menu Items.6-stats-1.Item Type", "player-head");
            config.addDefault("Stats Menu Items.6-stats-1.Slot", 22);
            config.addDefault("Stats Menu Items.6-stats-1.Page", 0);
            config.addDefault("Stats Menu Items.6-stats-1.Display Name", "%vault_groupprefix% &f%player_name%");
            config.addDefault("Stats Menu Items.6-stats-1.Lore", Arrays.asList("", "  &6\u26c3 Wallet &f%vault_eco_balance_formatted%", "  &c\u2694 Player Kills &f%statistic_player_kills%", "  &9\u2620 Deaths &f%statistic_deaths%"));
            config.addDefault("Stats Menu Items.7-visit.Item Type", "simple-item");
            config.addDefault("Stats Menu Items.7-visit.On Click Commands", Collections.singleton("is visit %target%"));
            config.addDefault("Stats Menu Items.7-visit.Slot", 15);
            config.addDefault("Stats Menu Items.7-visit.Page", 0);
            config.addDefault("Stats Menu Items.7-visit.Display Name", "&aVisit Island");
            config.addDefault("Stats Menu Items.7-visit.Material", "FEATHER");
            config.addDefault("Stats Menu Items.7-visit.Data", 0);
            config.addDefault("Stats Menu Items.7-visit.Lore", Collections.singletonList("&eClick to visit!"));
            config.addDefault("Stats Menu Items.8-trade.Item Type", "simple-item");
            config.addDefault("Stats Menu Items.8-trade.On Click Commands", Collections.singleton("trade %target%"));
            config.addDefault("Stats Menu Items.8-trade.Slot", 16);
            config.addDefault("Stats Menu Items.8-trade.Page", 0);
            config.addDefault("Stats Menu Items.8-trade.Display Name", "&aTrade Request");
            config.addDefault("Stats Menu Items.8-trade.Material", "EMERALD");
            config.addDefault("Stats Menu Items.8-trade.Data", 0);
            config.addDefault("Stats Menu Items.8-trade.Lore", Collections.singletonList("&eSend a trade request!"));
            config.addDefault("Stats Menu Items.9-invite.Item Type", "simple-item");
            config.addDefault("Stats Menu Items.9-invite.On Click Commands", Collections.singleton("is invite %target%"));
            config.addDefault("Stats Menu Items.9-invite.Slot", 24);
            config.addDefault("Stats Menu Items.9-invite.Page", 0);
            config.addDefault("Stats Menu Items.9-invite.Display Name", "&aInvite to Island");
            config.addDefault("Stats Menu Items.9-invite.Material", "RED_ROSE");
            config.addDefault("Stats Menu Items.9-invite.Data", 0);
            config.addDefault("Stats Menu Items.9-invite.Lore", Collections.singletonList("&eClick to invite!"));
            config.addDefault("Stats Menu Items.10-coop.Item Type", "simple-item");
            config.addDefault("Stats Menu Items.10-coop.On Click Commands", Collections.singleton("is coop %target%"));
            config.addDefault("Stats Menu Items.10-coop.Slot", 25);
            config.addDefault("Stats Menu Items.10-coop.Page", 0);
            config.addDefault("Stats Menu Items.10-coop.Display Name", "&aCo-op Request");
            config.addDefault("Stats Menu Items.10-coop.Material", "DIAMOND");
            config.addDefault("Stats Menu Items.10-coop.Data", 0);
            config.addDefault("Stats Menu Items.10-coop.Lore", Collections.singletonList("&eSend a co-op request!"));
            config.addDefault("Stats Menu Items.11-next-page-1.Item Type", "open-page");
            config.addDefault("Stats Menu Items.11-next-page-1.Next Page", 1);
            config.addDefault("Stats Menu Items.11-next-page-1.Slot", 50);
            config.addDefault("Stats Menu Items.11-next-page-1.Page", 0);
            config.addDefault("Stats Menu Items.11-next-page-1.Display Name", "&aNext Page");
            config.addDefault("Stats Menu Items.11-next-page-1.Material", "ARROW");
            config.addDefault("Stats Menu Items.11-next-page-1.Data", 0);
            config.addDefault("Stats Menu Items.11-next-page-1.Lore", Arrays.asList("", "&7&o- Go to next page."));
            config.addDefault("Stats Menu Items.12-next-page-2.Item Type", "open-page");
            config.addDefault("Stats Menu Items.12-next-page-2.Next Page", 0);
            config.addDefault("Stats Menu Items.12-next-page-2.Slot", 48);
            config.addDefault("Stats Menu Items.12-next-page-2.Page", 1);
            config.addDefault("Stats Menu Items.12-next-page-2.Display Name", "&aPrevious Page");
            config.addDefault("Stats Menu Items.12-next-page-2.Material", "ARROW");
            config.addDefault("Stats Menu Items.12-next-page-2.Data", 0);
            config.addDefault("Stats Menu Items.12-next-page-2.Lore", Arrays.asList("", "&7&o- Go to previous page."));
            config.addDefault("Stats Menu Items.13-close-page-1.Item Type", "close-page");
            config.addDefault("Stats Menu Items.13-close-page-1.Slot", 49);
            config.addDefault("Stats Menu Items.13-close-page-1.Page", 0);
            config.addDefault("Stats Menu Items.13-close-page-1.Display Name", "&cClose");
            config.addDefault("Stats Menu Items.13-close-page-1.Material", "BARRIER");
            config.addDefault("Stats Menu Items.13-close-page-1.Data", 0);
            config.addDefault("Stats Menu Items.13-close-page-1.Lore", Arrays.asList("", "&7&o- Close the stats menu."));
            config.addDefault("Stats Menu Items.14-close-page-2.Item Type", "close-page");
            config.addDefault("Stats Menu Items.14-close-page-2.Slot", 49);
            config.addDefault("Stats Menu Items.14-close-page-2.Page", 1);
            config.addDefault("Stats Menu Items.14-close-page-2.Display Name", "&cClose");
            config.addDefault("Stats Menu Items.14-close-page-2.Material", "BARRIER");
            config.addDefault("Stats Menu Items.14-close-page-2.Data", 0);
            config.addDefault("Stats Menu Items.14-close-page-2.Lore", Arrays.asList("", "&7&o- Close the stats menu."));
        }
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
