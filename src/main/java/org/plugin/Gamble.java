package org.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import java.util.ArrayList;
import java.util.List;

public class Gamble {
    private ItemStack item = new ItemStack(Material.PAPER); //ItemStack(Material.PAPER);
    NamespacedKey gambletiketKey = new NamespacedKey("gamble", "gamebletiket");
    List<String> lore = new ArrayList<String>();


//    public NamespacedKey createNamespacedKey(@NotNull String key) {
//        return new NamespacedKey("Gamble", key);
//    }



    @EventHandler
    public void onUseTicket(PlayerInteractEvent event) {
    Player p = event.getPlayer();
    Action action = event.getAction();
    ItemStack item = event.getItem();
        p.sendMessage(p.getName() + "이 감지됨");
    if (item == null) {
        p.sendMessage("item이 null임");
        return;
    }
        PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();

    if (action.equals(Action.RIGHT_CLICK_AIR) &&  pdc.has(gambletiketKey)) {
        p.sendMessage("이벤트 감지됨");
        p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
    }
    }


    public ItemStack GambleTicket(){
        lore.clear();
        ItemStack item1 = item;
        ItemMeta meta =  item1.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();


        item1.addUnsafeEnchantment(Enchantment.UNBREAKING, 5); //이거 왜 안되노
        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "도박권 사용");
        //아이템 이름 설정 색먼저 스타일은 나중

        lore.add(ChatColor.WHITE + "사용시 아이템을 얻을 수도 있습니다.");
        meta.setLore(lore);//설명 추가


        pdc.set(gambletiketKey, PersistentDataType.STRING,"ouritem");
        item1.setItemMeta(meta);
        return item1;
    }















}
