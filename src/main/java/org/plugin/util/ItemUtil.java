package org.plugin.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.plugin.domain.customInventory.ItemFrame;

import java.util.Objects;

public class ItemUtil {

    public static ItemStack createItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(name);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemFrame item(Material material, String name, int index) {
        return new ItemFrame(material, name, index);
    }
}
