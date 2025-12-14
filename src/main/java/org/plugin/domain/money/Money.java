package org.plugin.domain.money;

import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.plugin.util.FileUtil;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class Money {

    private final FileUtil fileUtil;
    private static final Map<String, Integer> playerMoneyMap = new HashMap<>();
    private static final String YAML_FILE_NAME = "money.yml";

    public int getMoney(String playerName) {
        if (playerMoneyMap.containsKey(playerName)) {
            return playerMoneyMap.get(playerName);
        } else {
            playerMoneyMap.put(playerName, 0);
            return 0;
        }
    }

    public void showPlayerDataOnCommand(Player p, Command command, String[] args) {
        if (command.getName().equalsIgnoreCase("돈")) {
            p.sendMessage("돈 :" + getMoney(p.getName()));
        }
    }

    public void upLoadMoneyData() {
        fileUtil.upLoadYamlToMap(fileUtil.loadYaml(YAML_FILE_NAME), playerMoneyMap, Integer.class);
    }

    public void saveMoneyData() {
        fileUtil.upLoadMapToYaml(fileUtil.loadYaml(YAML_FILE_NAME), playerMoneyMap, Integer.class, YAML_FILE_NAME);
    }

    public void addMoney(String playerName, int money) {
        playerMoneyMap.put(playerName, getMoney(playerName) + money);
    }

    public void setMoney(String playerName, int money) {
        playerMoneyMap.put(playerName, money);
    }

}
