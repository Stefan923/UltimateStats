package me.Stefan923.UltimateStats.Configuration;

import me.Stefan923.UltimateStats.Utils.MessageUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class LanguageManager implements MessageUtils {

    private static LanguageManager instance = new LanguageManager();
    private FileConfiguration config;
    private File cfile;
    private String languageFile;

    public static LanguageManager getInstance() {
        return instance;
    }

    public void setup(Plugin p, String languageFile) {
        this.languageFile = languageFile;

        cfile = new File(p.getDataFolder(), "languages/" + languageFile);
        if (!cfile.exists()) {
            try {
                //noinspection ResultOfMethodCallIgnored
                File dir = new File(p.getDataFolder() + "/languages");

                if (!dir.exists())
                    dir.mkdir();

                cfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(cfile);
        config.options().header("UltimateStats by Stefan923.\n");
        config.addDefault("Command.Invalid Command Syntax", "&8(&3!&8) &cInvalid Syntax or you have no permission!\n&8(&3!&8) &fThe valid syntax is: &b%syntax%");
        config.addDefault("General.Must Be Online", "&8(&3!&8) &cSpecified player must be online!");
        config.addDefault("General.Must Be Player", "&8(&3!&8) &cYou must be a player to do this!");
        config.addDefault("General.No Permission", "&8(&3!&8) &cYou need the &4%permission% &cpermission to do that!");
        config.addDefault("Update Checker.Available", "&8(&3!&8) &fThere is a new version of &bUltimateStats &favailable!\n&8(&3!&8) &fDownload link: &b%link%");
        config.addDefault("Update Checker.Not Available", "&8(&3!&8) &fThere's no update available for &bUltimateStats&f.");
        config.options().copyDefaults(true);

        save();
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void reset() {
        config.set("Update Checker.Available", "&8(&3!&8) &fThere is a new version of &bUltimateStats &favailable!\n&8(&3!&8) &fDownload link: &b%link%");
        config.set("Update Checker.Not Available", "&8(&3!&8) &fThere's no update available for &bUltimateStats&f.");

        save();
    }

    public void save() {
        try {
            config.save(cfile);
        } catch (IOException e) {
            sendLogger(ChatColor.RED + "File '" + languageFile + "' couldn't be saved!");
        }
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(cfile);
    }

    public String getLanguageFileName() {
        return languageFile;
    }

}
