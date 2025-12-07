package org.plugin.domain.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.plugin.Main;
import org.plugin.domain.money.Money;

public class MoneyCommandHandler {

    private final Main plugin = Main.getInstance();

    public static void moneyCommandHandler(CommandSender sender, Player player, String[] args) throws IllegalAccessException {
        if (args.length == 0) {
            sendHelp(sender);
        }else {
            String sub = args[0];

            switch (sub) {
                case "송금" -> sendMoney(sender, player, args);
                case "수표" -> checkMoney(sender, player, args);
                case "저장" -> saveMoney(sender);
                case "설정" -> setMoney(sender, args);
                case "빼기" -> minusMoney(sender, args);
                case "주기" -> plusMoney(sender, args);
                case "확인" -> checkPlayerMoney(sender, player, args);
                default     -> sender.sendMessage("알 수 없는 명령어입니다. /돈 help");
            }
        }


    }

    private static void sendMoney(CommandSender sender, Player player, String[] args) {
        String target = args[1];
        String amountStr = args[2];

        int amount;
        try {
            amount = Integer.parseInt(amountStr);
        } catch (NumberFormatException e) {
            sender.sendMessage("숫자를 입력해주세요.");
            return;
        }

        int senderMoney = Money.getMoney(player.getName());

        if (amount <= 0) {
            sender.sendMessage("1 이상의 금액을 입력해주세요.");
            return;
        }

        if (amount > senderMoney) {
            sender.sendMessage("보유 금액보다 많습니다!");
            return;
        }

        Money.addMoney(target, amount);
        Money.addMoney(player.getName(), -amount);

        sender.sendMessage(target + "에게 " + amount + "원을 보냈습니다.");
    }

    private static void checkMoney(CommandSender sender, Player player, String[] args) {
        /*
        * todo: 수표 아이템 코드 작성 이후 개발
         */
    }

    private static void saveMoney(CommandSender sender) throws IllegalAccessException {
        opCheck(sender);

        Money.saveMoneyData();
        sender.sendMessage("Money Data 저장 완료.");
    }

    private static void minusMoney(CommandSender sender, String[] args) throws IllegalAccessException {
        opCheck(sender);
        Money.addMoney(args[1], Integer.parseInt(args[2]) * -1);
    }

    private static void plusMoney(CommandSender sender, String[] args) throws IllegalAccessException {
        opCheck(sender);
        Money.addMoney(args[1], Integer.parseInt(args[2]));
    }

    private static void checkPlayerMoney(CommandSender sender, Player player, String[] args) throws IllegalAccessException {
        opCheck(sender);
        sender.sendMessage(args[1]+"의 소지금 : " + Money.getMoney(player.getName()));
    }

    private static void setMoney(CommandSender sender, String[] args) throws IllegalAccessException {
        opCheck(sender);
        Money.setMoney(args[1], Integer.parseInt(args[2]));
    }

    private static void opCheck(CommandSender sender) throws IllegalAccessException {
        if (!sender.isOp()) sender.sendMessage("관리자 전용 명령어입니다.");
        throw new IllegalAccessException("관리자 전용 명령어 사용 시도: " + sender.getName());
    }

    private static void sendHelp(CommandSender sender) {
        sender.sendMessage("/돈 명령어의 사용법을 안내합니다. \n");
        sender.sendMessage("/돈 송금 (닉네임) (금액) 닉네임에게 금액만큼 송금합니다 \n");
        sender.sendMessage("/돈 수표 (금액) 금액만큼 자신의 돈을 수표로 만듭니다\n");
    }
}
