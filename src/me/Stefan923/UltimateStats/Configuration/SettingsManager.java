package me.Stefan923.UltimateStats.Configuration;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class SettingsManager {

    private static SettingsManager instance = new SettingsManager();
    private FileConfiguration config;
    private File cfile;

    public static SettingsManager getInstance() {
        return instance;
    }

    public void setup(Plugin p) {
        cfile = new File(p.getDataFolder(), "settings.yml");
        config = YamlConfiguration.loadConfiguration(cfile);
        config.options().header("UltimateStats by Stefan923\n");
        config.addDefault("Languages.Default Language", "lang_en.yml");
        config.addDefault("Languages.Available Languages", Collections.singletonList("lang_en.yml"));
        config.addDefault("Enabled Commands.Stats", true);
        config.addDefault("Open Stats Invetory.Methods.Right Click", true);
        config.addDefault("Update Checker.Enable.On Plugin Enable", true);
        config.addDefault("Update Checker.Enable.On Join", true);
        config.options().copyDefaults(true);
        save();
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void resetConfig() {
        config.set("Languages.Default Language", "lang_en.yml");
        config.set("Languages.Available Languages", Collections.singletonList("lang_en.yml"));
        config.set("Open Stats Invetory.Methods.Right Click", true);
        config.set("Open Stats Invetory.Methods.Shift Right Click", true);
        config.set("Open Stats Invetory.Methods.Left Click", true);
        config.set("Open Stats Invetory.Methods.Shift Left Click", true);
        config.set("Update Checker.Enable.On Plugin Enable", true);
        config.set("Update Checker.Enable.On Join", true);
        save();
    }

    private void save() {
        try {
            config.save(cfile);
        } catch (IOException e) {
            Bukkit.getLogger().severe(ChatColor.RED + "File 'settings.yml' couldn't be saved!");
        }
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(cfile);
    }

}
