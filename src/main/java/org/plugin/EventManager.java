package org.plugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.plugin.domain.gamble.Gamble;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.plugin.domain.money.Money.getMoney;
import static org.plugin.domain.scoreBoard.ScoreBoard.createScoreBoard;
import static org.plugin.domain.scoreBoard.ScoreBoard.updateScoreBoard;

public class EventManager implements Listener {

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
        createScoreBoard(event.getPlayer());
        updateScoreBoard();
    }

    @EventHandler
    public void onPlayerQuitEventBoard(PlayerQuitEvent event) {
        updateScoreBoard();
    }
}
