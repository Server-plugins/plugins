package org.plugin;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Money {

    private static final Map<String, Integer> playerMoneyMap = new HashMap<>();
    private static final FileUtil fileUtil = new FileUtil();

    private Money() {}

    public static int getMoney(String playerName) {
        if (playerMoneyMap.containsKey(playerName)) {
            return playerMoneyMap.get(playerName);
        } else {
            playerMoneyMap.put(playerName, 0);
            return 0;
        }
    }

    public static void showPlayerDataOnCommand(Player p, Command command, String[] args) {
        if (command.getName().equalsIgnoreCase("돈")) {
            p.sendMessage("돈 :" + getMoney(p.getName()));
        }
    }

    public static void upLoadMoneyData() {
        fileUtil.upLoadYamlToMap(fileUtil.loadYaml("money.yml"), playerMoneyMap, Integer.class);
    }

    public static void saveMoneyData() {
        fileUtil.upLoadYamlToMap(fileUtil.loadYaml("money.yml"), playerMoneyMap, Integer.class);
    }

    public static void giveMoney(String playername, int money) {
        playerMoneyMap.put(playername, getMoney(playername) + money);
    }

    public static void takeMoney(String playername, int money) {
        playerMoneyMap.put(playername, getMoney(playername) - money);
    }

}
