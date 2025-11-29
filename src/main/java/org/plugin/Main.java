package org.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static org.plugin.Money.saveMoneyData;
import static org.plugin.Money.upLoadMoneyData;


public final class Main extends JavaPlugin {
    private static Main plugin;


    @Override
    public void onEnable() {
        plugin = this;
//        // Plugin startup logic
//        Gamble plugin = new Gamble();
        Scheduler scheduler = new Scheduler();
        Bukkit.getPluginManager().registerEvents(new EventManager(), this);

        upLoadMoneyData();

        scheduler.task(Money::saveMoneyData, 30, 30);

        getLogger().info("플러그인이 활성화되었습니다.");
        Objects.requireNonNull(this.getCommand("돈")).setExecutor(new UseCommand());
    }





    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

//    @Override
//    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
//        if (command.getName().equalsIgnoreCase("dudumtack") && sender.isOp()) {
//            Player p = (Player) sender;
//            p.getInventory().addItem(gamble.GambleTicket()); //key : gamebletiket value : ouritem
//            getLogger().info("커맨드 실행됨");
//        } else {
//            getLogger().info("커맨드 실패");
//            return false;
//        }
//        return true;
//    }

    public static Main getInstance() {
        return plugin;
    }


}
