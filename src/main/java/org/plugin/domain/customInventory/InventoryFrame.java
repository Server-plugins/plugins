package org.plugin.domain.customInventory;

import lombok.Getter;
import org.bukkit.Material;
import org.plugin.util.ItemUtil.*;


@Getter
public class InventoryFrame {
    private Material[] materials;
    private String[] names;
    private int size;

    public InventoryFrame(int size) {
        materials = new Material[size];
        names = new String[size];
        this.size = size;
    }

    public void addItem(Material material, String name, int index) {
        materials[index] = material;
        names[index] = name;
    }

    public void of(ItemFrame... itemFrames) {
        for (ItemFrame itemFrame : itemFrames) {


            addItem(itemFrame.material(), itemFrame.name(), itemFrame.index());
        }
    }

    public Material getMaterial(int index) {
        return materials[index];
    }

    public String getName(int index) {
        return names[index];
    }
}
