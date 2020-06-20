package me.Stefan923.UltimateStats.Utils;

import me.Stefan923.UltimateStats.UltimateStats;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public interface VersionUtils extends MessageUtils, PlayerUtils {

    default void checkForUpdate(UltimateStats instance) {
        String version = getLatestPluginVersion(instance);

        FileConfiguration languageConfig = getLanguageConfig(instance);
        if (!version.equalsIgnoreCase(getCurrentPluginVersion())) {
            sendLogger(formatAll(languageConfig.getString("Update Checker.Available").replace("%link%", "https://www.spigotmc.org/resources/replace-soon")));
        } else
            sendLogger(formatAll(languageConfig.getString("Update Checker.Not Available")));
    }

    default void checkForUpdate(UltimateStats instance, Player player) {
        String version = getLatestPluginVersion(instance);

        FileConfiguration languageConfig = getLanguageConfig(instance, player);
        if (!version.equalsIgnoreCase(getCurrentPluginVersion()))
            player.sendMessage(formatAll(languageConfig.getString("Update Checker.Available").replace("%link%", "https://www.spigotmc.org/resources/replace-soon")));
        else
            player.sendMessage(formatAll(languageConfig.getString("Update Checker.Not Available")));
    }

    default String getLatestPluginVersion(UltimateStats instance) {
        String version = "";
        try {
            InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=replace-soon").openStream();
            Scanner scanner = new Scanner(inputStream);
            if (scanner.hasNext())
                version = scanner.next();
        } catch (IOException exception) {
            instance.getLogger().info("Cannot look for updates: " + exception.getMessage());
        }
        return version;
    }

    default String getCurrentPluginVersion() {
        return Bukkit.getPluginManager().getPlugin("UltimateStats").getDescription().getVersion();
    }

}
