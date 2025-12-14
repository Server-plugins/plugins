package org.plugin.domain.npc;

import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.plugin.domain.customInventory.CustomInventoryGUI;
import org.plugin.domain.customInventory.InventoryFrame;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.plugin.util.ItemUtil.item;

@RequiredArgsConstructor
public class NPC {

    private static final Map<UUID, CustomNPC> npcMap = new HashMap<>();

    public void spawnNPC(CustomNPC customNPC) {
        Location location = customNPC.getLocation();
        Villager npc = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
        npc.setCustomNameVisible(true);
        npc.setCustomName(customNPC.getName());
        npc.setGravity(false);
        npc.setInvulnerable(true);
        npc.setVisibleByDefault(true);
        npc.setSilent(true);
        npc.setCanPickupItems(false);
        npc.setAI(false);
        npc.setCollidable(false);

        npcMap.put(npc.getUniqueId(), customNPC);
    }

    public boolean containsNPC(UUID uuid) {
        return npcMap.containsKey(uuid);
    }

    public NPCType getNPCType(UUID uuid) {
        return npcMap.get(uuid).getNpcType();
    }

    public void openSettingGUI(Player player) {
        int inventorySize = 9;
        InventoryFrame inventoryFrame = new InventoryFrame(inventorySize);

        inventoryFrame.of(
                item(Material.DARK_OAK_BUTTON, "NPC 이름 변경", 0),
                item(Material.DARK_OAK_BUTTON, "NPC 역할 부여 : 상점", 2),
                item(Material.DARK_OAK_BUTTON, "NPC 제거", 8)
        );

        player.openInventory(
                new CustomInventoryGUI(
                        inventorySize,
                        ChatColor.AQUA + "설정",
                        inventoryFrame)
                        .getInventory());
    }
}
