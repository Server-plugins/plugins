package org.plugin.domain.scoreBoard;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.plugin.domain.money.Money;
import org.plugin.util.TextDesign;

import java.util.Objects;

@RequiredArgsConstructor
public class ScoreBoard {
    private final Money money;

    public void createScoreBoard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = Objects.requireNonNull(manager).getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective("scoreboard", Criteria.DUMMY, "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.setDisplayName(scoreBoardColorStyle(0, player));
        Score line1 = obj.getScore(scoreBoardColorStyle(1, player) + money.getMoney(player.getName()));
        line1.setScore(10);
        Score line2 = obj.getScore(scoreBoardColorStyle(2, player));
        line2.setScore(9);

        player.setScoreboard(scoreboard);
    }

    public void updateScoreBoard() {
        for (Player online : Bukkit.getOnlinePlayers()) {
            Objects.requireNonNull(online.getScoreboard().getObjective(DisplaySlot.SIDEBAR)).unregister();
            createScoreBoard(online);
        }
    }

    public String scoreBoardColorStyle(int line, Player player) {
        final String line0 = "순무서버";
        final String[] color0 = {"#54DAF4", "#549CD5", "#545EB6", "#AD5CCA"};
        final String[][] style0 = {{"BOLD", "ITALIC"}, {"BOLD", "ITALIC"}, {"BOLD", "ITALIC"}, {"BOLD", "ITALIC"}};

        final String line1 = "소지금 : ";

        final String line2 = "직업 : "; /*TODO"구현예정"*/
        ;

        TextDesign textDesign = new TextDesign();
        return switch (line) {
            case 0 -> textDesign.getText(line0, color0, style0);
            case 1 -> ChatColor.YELLOW + line1;
            case 2 -> ChatColor.GREEN + line2;
            default -> "";
        };
    }
}
