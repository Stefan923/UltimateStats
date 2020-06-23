package me.Stefan923.UltimateStats.Utils;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;


public interface ItemUtils extends MessageUtils {
    default ItemStack configToItemStack(ConfigurationSection config, Player player) {
        if (config.isSet("Item Type")) {
            if (config.getString("Item Type").equalsIgnoreCase("player-hand")) {
                return player.getItemInHand();
            } else if (config.getString("Item Type").equalsIgnoreCase("player-helmet")) {
                return player.getEquipment().getHelmet();
            } else if (config.getString("Item Type").equalsIgnoreCase("player-chestplate")) {
                return player.getEquipment().getChestplate();
            } else if (config.getString("Item Type").equalsIgnoreCase("player-leggings")) {
                return player.getEquipment().getLeggings();
            } else if (config.getString("Item Type").equalsIgnoreCase("player-boots")) {
                return player.getEquipment().getBoots();
            } else if (config.getString("Item Type").equalsIgnoreCase("player-item-on-slot")) {
                return player.getInventory().getItem(config.getInt("Inventory Slot"));
            } else if (config.getString("Item Type").equalsIgnoreCase("player-page")) {
                return configToPageItemStack(config, player);
            } else if (config.getString("Item Type").equalsIgnoreCase("simple-item")) {
                return configToSimpleItemStack(config, player);
            }
        }

        return null;
    }

    default ItemStack configToPageItemStack(ConfigurationSection config, Player player) {
        ItemStack itemStack = new ItemStack(Material.valueOf(config.getString("Material")), 1);
        itemStack.setDurability((byte) config.getInt("Data"));

        List<String> lores = config.getStringList("Lore");
        lores.forEach(string -> string = replacePlaceholders(player, string.replace("%page%", String.valueOf(config.getInt("Next Page")))));

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(config.getString("Display Name"));
        itemMeta.setLore(lores);
        return itemStack;
    }

    default ItemStack configToFillItemStack(ConfigurationSection config, Player player) {
        ItemStack itemStack = new ItemStack(Material.valueOf(config.getString("Fill Empty Slots.Item.Material")), 1);
        itemStack.setDurability((byte) config.getInt("Fill Empty Slots.Item.Data"));

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(config.getString("Fill Empty Slots.Item.Display Name"));
        itemMeta.setLore(config.getStringList("Fill Empty Slots.Item.Lore"));

        return itemStack;
    }

    default ItemStack configToSimpleItemStack(ConfigurationSection config, Player player) {
        ItemStack itemStack = new ItemStack(Material.valueOf(config.getString("Material")), 1);
        itemStack.setDurability((byte) config.getInt("Data"));

        List<String> lores = config.getStringList("Lore");
        lores.forEach(string -> string = replacePlaceholders(player, string));

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(config.getString("Display Name"));
        itemMeta.setLore(lores);
        return itemStack;
    }
}
