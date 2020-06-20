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
        if (Bukkit.getPluginManager().getPlugin("SuperCore").isEnabled()) {
            return instance.getLanguageManager(SuperCoreAPI.getInstance().getUser(player.getName()).getLanguage()).getConfig();
        }
        return instance.getLanguageManager(instance.getSettingsManager().getConfig().getString("Languages.Default Language")).getConfig();
    }

    default FileConfiguration getLanguageConfig(UltimateStats instance) {
        return instance.getLanguageManager(instance.getSettingsManager().getConfig().getString("Languages.Default Language")).getConfig();
    }
}
