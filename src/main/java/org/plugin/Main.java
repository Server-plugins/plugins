package org.plugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;


public final class Main extends JavaPlugin {
    Gamble gamble = new Gamble();
    private static int money;

    @Override
    public void onEnable() {
        // Plugin startup logic
       Gamble plugin = new Gamble();
       Bukkit.getPluginManager().registerEvents(gamble, plugin);
        getLogger().info("플러그인이 활성화되었습니다.");

       }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("dudumtack") && sender.isOp()) {
            Player p = (Player) sender;
            p.getInventory().addItem(gamble.GambleTicket()); //key : gamebletiket value : ouritem
            getLogger().info("커맨드 실행됨");
        }else {
            getLogger().info("커맨드 실패");
            return false;
        }
        return true;
    }
}
