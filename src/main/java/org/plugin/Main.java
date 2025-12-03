package org.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.plugin.domain.command.CommandHandler;
import org.plugin.domain.money.Money;
import org.plugin.domain.scoreBoard.ScoreBoard;
import org.plugin.util.Scheduler;

import java.util.Objects;

import static org.plugin.domain.money.Money.upLoadMoneyData;


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
        scheduler.task(ScoreBoard::updateScoreBoard, 30, 30);

        getLogger().info("플러그인이 활성화되었습니다.");
        Objects.requireNonNull(this.getCommand("돈")).setExecutor(new CommandHandler());
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
