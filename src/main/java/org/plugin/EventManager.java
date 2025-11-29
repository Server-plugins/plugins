package org.plugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

import static org.plugin.Money.getMoney;

public class EventManager implements Listener {

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();
        p.sendMessage(p.getName() + "이 감지됨");
        if (item == null || item.getItemMeta().getPersistentDataContainer().isEmpty()) {
            p.sendMessage("item이 null이거나 ㅇㅇ 암튼 그럼");
            return;
        }
        PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();

        if (action.equals(Action.RIGHT_CLICK_AIR) &&  pdc.has(Gamble.gambletiketKey)) {
            p.sendMessage("이벤트 감지됨");
            p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
        }
    }


    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        getMoney(player.getName());
        player.sendMessage("안녕");
    }


}
