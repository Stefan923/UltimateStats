package me.Stefan923.UltimateStats;

import me.Stefan923.UltimateStats.Commands.CommandManager;
import me.Stefan923.UltimateStats.Configuration.LanguageManager;
import me.Stefan923.UltimateStats.Configuration.SettingsManager;
import me.Stefan923.UltimateStats.Inventory.InventoryManager;
import me.Stefan923.UltimateStats.Listeners.*;
import me.Stefan923.UltimateStats.Utils.MessageUtils;
import me.Stefan923.UltimateStats.Utils.Metrics;
import me.Stefan923.UltimateStats.Utils.User;
import me.Stefan923.UltimateStats.Utils.VersionUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class UltimateStats extends JavaPlugin implements MessageUtils, VersionUtils {

    private static UltimateStats instance;

    private SettingsManager settingsManager;
    private HashMap<String, LanguageManager> languageManagers;
    private CommandManager commandManager;
    private InventoryManager inventoryManager;

    private HashMap<String, User> users;

    @Override
    public void onEnable() {
        instance = this;

        settingsManager = SettingsManager.getInstance();
        settingsManager.setup(this);
        inventoryManager = new InventoryManager(this);

        languageManagers = new HashMap<>();
        for (String fileName : settingsManager.getConfig().getStringList("Languages.Available Languages")) {
            LanguageManager languageManager = new LanguageManager();
            fileName = fileName.toLowerCase();
            languageManager.setup(this, fileName);
            languageManagers.put(fileName, languageManager);
        }

        users = new HashMap<>();

        new Metrics(this, 7702);

        sendLogger("&8&l> &7&m------- &8&l( &3&lUltimateStats &b&lby Stefan923 &8&l) &7&m------- &8&l<");
        sendLogger("&b   Plugin has been initialized.");
        sendLogger("&b   Version: &3v" + getDescription().getVersion());
        sendLogger("&b   Enabled listeners: &3" + enableListeners());
        sendLogger("&b   Enabled commands: &3" + enableCommands());
        sendLogger("&8&l> &7&m------- &8&l( &3&lUltimateStats &b&lby Stefan923 &8&l) &7&m------- &8&l<");

        if (settingsManager.getConfig().getBoolean("Update Checker.Enable.On Plugin Enable"))
            Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
                checkForUpdate(this);
            });
    }

    public static UltimateStats getInstance() {
        return instance;
    }

    private Integer enableListeners() {
        Integer i = 4;
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new InventoryClickListener(instance), this);
        pluginManager.registerEvents(new InventoryCloseListener(instance), this);
        pluginManager.registerEvents(new PlayerJoinListener(instance), this);
        pluginManager.registerEvents(new PlayerQuitListener(instance), this);
        if (settingsManager.getConfig().getBoolean("Open Stats Invetory.Methods.Right Click")) {
            pluginManager.registerEvents(new PlayerInteractEntityListener(instance, inventoryManager), this);
            i++;
        }
        return i;
    }

    private Integer enableCommands() {
        commandManager = new CommandManager(this);
        return commandManager.getCommands().size();
    }

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }

    public void reloadSettingManager() {
        settingsManager.reload();
    }

    public LanguageManager getLanguageManager(String language) {
        return languageManagers.get(language);
    }

    public HashMap<String, LanguageManager> getLanguageManagers() {
        return languageManagers;
    }

    public void reloadLanguageManagers() {
        languageManagers.clear();
        for (String fileName : settingsManager.getConfig().getStringList("Languages.Available Languages")) {
            LanguageManager languageManager = new LanguageManager();
            fileName = fileName.toLowerCase();
            languageManager.setup(this, fileName);
            languageManagers.put(fileName, languageManager);
        }
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    @Override
    public void onDisable() {
        inventoryManager.closeAll();
    }

    public User addUser(Player player) {
        User user = null;
        if (users.containsKey(player.getName())) {
            user = users.get(player.getName());
        } else {
            user = new User(player);
            users.put(player.getName(), user);
        }
        return user;
    }

    public void removeUser(Player player) {
        users.remove(player.getName());
    }

    public User getUser(Player player) {
        String name = player.getName();
        return users.getOrDefault(name, null);
    }
}
