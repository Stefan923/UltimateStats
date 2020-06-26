package me.Stefan923.UltimateStats.Commands.Type;

import me.Stefan923.UltimateStats.Commands.AbstractCommand;
import me.Stefan923.UltimateStats.Inventory.StatsInventory;
import me.Stefan923.UltimateStats.UltimateStats;
import me.Stefan923.UltimateStats.Utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandStats extends AbstractCommand implements MessageUtils {

    public CommandStats() {
        super(true, false, "stats");
    }

    @Override
    protected ReturnType runCommand(UltimateStats instance, CommandSender sender, String... args) {
        Player senderPlayer = (Player) sender;
        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer == null) {
            return ReturnType.FAILURE;
        }

        instance.getInventoryManager().getInventory(targetPlayer).open(senderPlayer, 0);
        instance.getUser(senderPlayer).setWatching(true);
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
