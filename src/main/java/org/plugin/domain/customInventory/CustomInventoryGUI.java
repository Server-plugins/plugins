package org.plugin.domain.customInventory;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import static org.plugin.util.ItemUtil.createItem;

@RequiredArgsConstructor
public class CustomInventoryGUI {

    public final Inventory inventory;

    public CustomInventoryGUI(int size, String title, InventoryFrame inventoryFrame) {
        this.inventory = Bukkit.createInventory(null, size, title);
        inventoryFrameToInventory(inventory, inventoryFrame);
    }

    public CustomInventoryGUI(InventoryType type, String title, InventoryFrame inventoryFrame) {
        this.inventory = Bukkit.createInventory(null, type, title);
        inventoryFrameToInventory(inventory, inventoryFrame);
    }

    public Inventory getInventory() {
        return inventory;
    }

    private Inventory inventoryFrameToInventory(Inventory inventory, InventoryFrame inventoryFrame) {
        for (int i = 0; i < inventoryFrame.getSize(); i++) {
            Material m = inventoryFrame.getMaterial(i);
            String s = inventoryFrame.getName(i);
            if (m == null || s == null) continue;
            inventory.setItem(i, createItem(m, s));
        }

        return inventory;
    }
}
