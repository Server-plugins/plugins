package org.plugin.domain.scoreBoard;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.plugin.domain.money.Money;

import java.util.Objects;

public class ScoreBoard {
    public static void createScoreBoard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = Objects.requireNonNull(manager).getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective("scoreboard", Criteria.DUMMY, "dummy");

        obj.setDisplayName(player.getName());
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);



       final String scoretextline1 = ChatColor.of("#54DAF4") + "t" + ChatColor.of("#54B1DF") + "e" + ChatColor.of("#5487CB") + "s" + ChatColor.of("#545EB6") + "t" + ChatColor.of("#815DC0") + "서" + ChatColor.of("#AD5CCA") + "버";

                Score moneyScore = obj.getScore("돈");
        moneyScore.setScore(Money.getMoney(player.getName()));

        player.setScoreboard(scoreboard);
    }

    public static void updateScoreBoard() {
        for (Player online : Bukkit.getOnlinePlayers()) {
            Score moneyScore = Objects.requireNonNull(online.getScoreboard().getObjective(DisplaySlot.SIDEBAR)).getScore("돈");
            moneyScore.setScore(Money.getMoney(online.getName()));
        }
    }
}
