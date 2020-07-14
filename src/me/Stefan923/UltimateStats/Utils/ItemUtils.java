package me.Stefan923.UltimateStats.Utils;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;


public interface ItemUtils extends MessageUtils {
    default ItemStack configToItemStack(ConfigurationSection config, ConfigurationSection configEmpty, Player player) {
        if (config.isSet("Item Type")) {
            if (config.getString("Item Type").equalsIgnoreCase("player-hand")) {
                ItemStack itemStack = player.getItemInHand();
                return itemStack.getType().equals(Material.AIR) ? configToFillItemStack(configEmpty, player) : itemStack;
            } else if (config.getString("Item Type").equalsIgnoreCase("player-helmet")) {
                ItemStack itemStack = player.getEquipment().getHelmet();
                return itemStack == null ? configToFillItemStack(configEmpty, player) : itemStack;
            } else if (config.getString("Item Type").equalsIgnoreCase("player-chestplate")) {
                ItemStack itemStack = player.getEquipment().getChestplate();
                return itemStack == null ? configToFillItemStack(configEmpty, player) : itemStack;
            } else if (config.getString("Item Type").equalsIgnoreCase("player-leggings")) {
                ItemStack itemStack = player.getEquipment().getLeggings();
                return itemStack == null ? configToFillItemStack(configEmpty, player) : itemStack;
            } else if (config.getString("Item Type").equalsIgnoreCase("player-boots")) {
                ItemStack itemStack = player.getEquipment().getBoots();
                return itemStack == null ? configToFillItemStack(configEmpty, player) : itemStack;
            } else if (config.getString("Item Type").equalsIgnoreCase("player-item-on-slot")) {
                ItemStack itemStack = player.getInventory().getItem(config.getInt("Inventory Slot"));
                return itemStack == null ? configToFillItemStack(configEmpty, player) : itemStack;
            } else if (config.getString("Item Type").equalsIgnoreCase("player-head")) {
                return configToSkullItemStack(config, player);
            } else if (config.getString("Item Type").equalsIgnoreCase("open-page")) {
                return configToPageItemStack(config, player);
            } else if (config.getString("Item Type").equalsIgnoreCase("close-page")) {
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

        List<String> lores = new ArrayList<>();
        config.getStringList("Lore").forEach(string -> lores.add(formatAll(replacePlaceholders(player, string.replace("%page%", String.valueOf(config.getInt("Next Page")))))));

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(formatAll(config.getString("Display Name")));
        itemMeta.setLore(lores);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    default ItemStack configToFillItemStack(ConfigurationSection config, Player player) {
        ItemStack itemStack = new ItemStack(Material.valueOf(config.getString("Material")), 1);
        itemStack.setDurability((byte) config.getInt("Data"));

        List<String> lores = new ArrayList<>();
        config.getStringList("Lore").forEach(string -> lores.add(formatAll(replacePlaceholders(player, string))));

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(formatAll(config.getString("Display Name")));
        itemMeta.setLore(lores);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    default ItemStack configToSkullItemStack(ConfigurationSection config, Player player) {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1);
        itemStack.setDurability((byte) 3);

        List<String> lores = new ArrayList<>();
        config.getStringList("Lore").forEach(string -> lores.add(formatAll(replacePlaceholders(player, string))));

        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setDisplayName(formatAll(replacePlaceholders(player, config.getString("Display Name"))));
        skullMeta.setLore(lores);
        skullMeta.setOwner(player.getName());
        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }

    default ItemStack configToSimpleItemStack(ConfigurationSection config, Player player) {
        ItemStack itemStack = new ItemStack(Material.valueOf(config.getString("Material")), 1);
        itemStack.setDurability((byte) config.getInt("Data"));

        List<String> lores = new ArrayList<>();
        config.getStringList("Lore").forEach(string -> lores.add(formatAll(replacePlaceholders(player, string))));

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(formatAll(config.getString("Display Name")));
        itemMeta.setLore(lores);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
