package org.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.plugin.Money.getMoney;
import static org.plugin.Money.showPlayerDataOnCommand;

public class UseCommand implements CommandExecutor {
    private Main plugin;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("돈")) {

            switch (args.length) {

                case 0:
                    showPlayerDataOnCommand((Player) sender, command, args);
                    break;
                case 1:
                    if (args[0].equals("주기")) {
                         sender.sendMessage("대상을 입력해주세요");
                    }
                    break;
                case 2:
                        sender.sendMessage("금액을 입력해주세요");
                    break;
                case 3:
                    try {
                        if (Integer.parseInt(args[2]) >= 0 && Integer.parseInt(args[2]) <= getMoney(plugin.getName())) {
                            Money.giveMoney(args[1], Integer.parseInt(args[2]));
                            Money.takeMoney(sender.getName(), Integer.parseInt(args[2]));

                        } else {
                            sender.sendMessage("가진것보다 많네요");
                        }
                    } catch (NumberFormatException e) {
                        sender.sendMessage("숫자를 입력해주세요");
                    }

            }




        }
        return false;



    }




}
