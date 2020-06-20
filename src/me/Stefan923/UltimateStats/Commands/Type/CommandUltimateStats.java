package me.Stefan923.UltimateStats.Commands.Type;

import me.Stefan923.UltimateStats.Commands.AbstractCommand;
import me.Stefan923.UltimateStats.UltimateStats;
import me.Stefan923.UltimateStats.Utils.MessageUtils;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class CommandUltimateStats extends AbstractCommand implements MessageUtils {

    public CommandUltimateStats() {
        super(null, false, "ultimatestats");
    }

    @Override
    protected ReturnType runCommand(UltimateStats instance, CommandSender sender, String... args) {
        sender.sendMessage(formatAll(" "));
        sendCenteredMessage(sender, formatAll("&8&m--+----------------------------------------+--&r"));
        sendCenteredMessage(sender, formatAll("&3&lUltimateStats &f&lv" + instance.getDescription().getVersion()));
        sendCenteredMessage(sender, formatAll("&8&l» &fPlugin author: &bStefan923"));
        sendCenteredMessage(sender, formatAll(" "));
        sendCenteredMessage(sender, formatAll("&8&l» &fShows info about a certain player."));
        sendCenteredMessage(sender, formatAll("&8&m--+----------------------------------------+--&r"));
        sender.sendMessage(formatAll(" "));

        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(UltimateStats instance, CommandSender sender, String... args) {
        if (sender.hasPermission("ultimatestats.admin"))
            return Collections.singletonList("reload");
        return null;
    }

    @Override
    public String getPermissionNode() {
        return null;
    }

    @Override
    public String getSyntax() {
        return "/ultimatestats";
    }

    @Override
    public String getDescription() {
        return "Displays plugin info";
    }

}
