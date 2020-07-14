package me.Stefan923.UltimateStats.Commands.Type;

import me.Stefan923.UltimateStats.Commands.AbstractCommand;
import me.Stefan923.UltimateStats.UltimateStats;
import me.Stefan923.UltimateStats.Utils.MessageUtils;
import me.Stefan923.UltimateStats.Utils.User;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandStats extends AbstractCommand implements MessageUtils {

    public CommandStats() {
        super(true, true, "stats");
    }

    @Override
    protected ReturnType runCommand(UltimateStats instance, CommandSender sender, String... args) {
        Player senderPlayer = (Player) sender;
        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer == null) {
            return ReturnType.FAILURE;
        }
        if (targetPlayer.getName() == senderPlayer.getName()) {
            return ReturnType.FAILURE;
        }
        User user = instance.getUser(senderPlayer);
        
        instance.getInventoryManager().getInventory(targetPlayer).open(senderPlayer, user, 0);
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(UltimateStats instance, CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "ultimatestats.stats";
    }

    @Override
    public String getSyntax() {
        return "/stats";
    }

    @Override
    public String getDescription() {
        return "Shows info about a player.";
    }

}
