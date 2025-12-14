package org.plugin.domain.money;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.plugin.util.FileUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Money {

    private static final Map<String, Integer> playerMoneyMap = new HashMap<>();
    private static final FileUtil fileUtil = new FileUtil();
    private static final String YAML_FILE_NAME = "money.yml";

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
        fileUtil.upLoadYamlToMap(fileUtil.loadYaml(YAML_FILE_NAME), playerMoneyMap, Integer.class);
    }

    public static void saveMoneyData() {
        fileUtil.upLoadMapToYaml(fileUtil.loadYaml(YAML_FILE_NAME), playerMoneyMap, Integer.class, YAML_FILE_NAME);
    }

    public static void addMoney(String playerName, int money) {
        playerMoneyMap.put(playerName, getMoney(playerName) + money);
    }

    public static void setMoney(String playerName, int money) {
        playerMoneyMap.put(playerName, money);
    }

}
