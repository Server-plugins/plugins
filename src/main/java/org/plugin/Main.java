package org.plugin;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.plugin.domain.command.CommandHandler;
import org.plugin.domain.command.MoneyCommandHandler;
import org.plugin.domain.command.ResidentCommandHandler;
import org.plugin.domain.customInventory.CustomInventoryGUI;
import org.plugin.domain.money.Money;
import org.plugin.domain.npc.CustomNPC;
import org.plugin.domain.npc.NPC;
import org.plugin.domain.scoreBoard.ScoreBoard;
import org.plugin.util.FileUtil;
import org.plugin.util.ItemUtil;
import org.plugin.util.Scheduler;

import java.util.Objects;

public final class Main extends JavaPlugin {

    private FileUtil fileUtil;
    private Scheduler scheduler;

    @Override
    public void onEnable() {
        this.fileUtil = new FileUtil(this);
        this.scheduler = new Scheduler(Bukkit.getScheduler(), this);



        Money money = new Money(fileUtil);
        money.upLoadMoneyData();

        ScoreBoard scoreBoard = new ScoreBoard(money);

        NPC npc = new NPC();

        scheduler.task(money::saveMoneyData, 30, 30);
        scheduler.task(scoreBoard::updateScoreBoard, 30, 30);

        Bukkit.getPluginManager().registerEvents(
                new EventManager(money, npc, scoreBoard),
                this);

        getLogger().info("플러그인이 활성화되었습니다.");

        CommandHandler commandHandler = new CommandHandler(
                new ResidentCommandHandler(),
                new MoneyCommandHandler(money));
        String[] commandNames = new String[]{"돈","땅"};
        for (String s : commandNames){
            Objects.requireNonNull(this.getCommand(s)).setExecutor(commandHandler);
        }

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

}
