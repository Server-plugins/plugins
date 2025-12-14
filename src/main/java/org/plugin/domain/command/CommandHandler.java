package org.plugin.domain.command;

import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class CommandHandler implements CommandExecutor{
  private final ResidentCommandHandler residentCommandHandler;
  private final MoneyCommandHandler moneyCommandHandler;


    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        Player player = (Player) sender;
        try {
            switch (command.getName()) {
                case "땅" -> {
                    residentCommandHandler.residentCommandHandler(sender, player, args);
                }
                case "돈" -> {
                    moneyCommandHandler.moneyCommandHandler(sender, player, args);
                }

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
