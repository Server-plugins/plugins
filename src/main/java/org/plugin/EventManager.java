package org.plugin;

import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.plugin.domain.gamble.Gamble;
import org.plugin.domain.npc.CustomNPC;
import org.plugin.domain.npc.NPC;

import static org.plugin.domain.money.Money.getMoney;
import static org.plugin.domain.scoreBoard.ScoreBoard.createScoreBoard;
import static org.plugin.domain.scoreBoard.ScoreBoard.updateScoreBoard;

@RequiredArgsConstructor
public class EventManager implements Listener {

    private final NPC npc;

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();
        p.sendMessage(p.getName() + "이 감지됨");
        if (item == null || item.getItemMeta().getPersistentDataContainer().isEmpty()) {
            return;
        }
        PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();

        if (action.equals(Action.RIGHT_CLICK_AIR) && pdc.has(Gamble.gambletiketKey)) {
            p.sendMessage("이벤트 감지됨");
            p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
        }
    }


    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        getMoney(player.getName());
        player.sendMessage("안녕");
        createScoreBoard(event.getPlayer());
        updateScoreBoard();
    }

    @EventHandler
    public void onPlayerQuitEventBoard(PlayerQuitEvent event) {
        updateScoreBoard();
    }

    // npc
    @EventHandler
    public void onRightClickBlock(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        if (event.getItem() == null) return;
        if (event.getItem().getType() != Material.WOODEN_HOE) return;

        Block block = event.getClickedBlock();
        if (block == null) return;

        npc.spawnNPC(
                new CustomNPC(
                        block.getLocation().add(0.5, 1, 0.5),
                        "npc"
                )
        );

        event.getPlayer().sendMessage("생성 완료");
    }

    @EventHandler
    public void onRightClickNPC(PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof Villager) ||
                !npc.containsNPC(event.getRightClicked().getUniqueId()) ||
                !event.getPlayer().isOp()) return;


        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getType() == Material.WOODEN_HOE) {
            npc.openSettingGUI(player);
        } else if (player.getInventory().getItemInMainHand().getType() == Material.IRON_HOE) {
            switch (npc.getNPCType(event.getRightClicked().getUniqueId())) {

            }
        }
    }
}
