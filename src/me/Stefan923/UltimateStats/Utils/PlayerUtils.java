package me.Stefan923.UltimateStats.Utils;

import me.Stefan923.SuperCore.API.SuperCoreAPI;
import me.Stefan923.UltimateStats.UltimateStats;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public interface PlayerUtils {

    default FileConfiguration getLanguageConfig(UltimateStats instance, CommandSender commandSender) {
        if (commandSender instanceof Player) {
            return getLanguageConfig(instance, (Player) commandSender);
        }
        return getLanguageConfig(instance);
    }

    default FileConfiguration getLanguageConfig(UltimateStats instance, Player player) {
        String defaultLanguage = instance.getSettingsManager().getConfig().getString("Languages.Default Language");
        if (Bukkit.getPluginManager().getPlugin("SuperCore").isEnabled()) {
            String userLanguage = SuperCoreAPI.getInstance().getUser(player.getName()).getLanguage();
            return instance.getLanguageManagers().containsKey(userLanguage) ? instance.getLanguageManager(userLanguage).getConfig() : instance.getLanguageManager(defaultLanguage).getConfig();
        }
        return instance.getLanguageManager(defaultLanguage).getConfig();
    }

    default FileConfiguration getLanguageConfig(UltimateStats instance) {
        return instance.getLanguageManager(instance.getSettingsManager().getConfig().getString("Languages.Default Language")).getConfig();
    }
}
