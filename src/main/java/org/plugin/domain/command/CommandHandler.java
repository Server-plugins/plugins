package org.plugin.domain.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.plugin.domain.command.MoneyCommandHandler.moneyCommandHandler;

public class CommandHandler implements CommandExecutor{

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        Player player = (Player) sender;

        try {
            switch (label) {
                case "돈" -> moneyCommandHandler(sender, player, args);
                default -> {
                    return true;
                }
            }
        } catch (IllegalAccessException e) { // 관리자 권한 예외처리
            return false;
        }

        return true;
    }
}
