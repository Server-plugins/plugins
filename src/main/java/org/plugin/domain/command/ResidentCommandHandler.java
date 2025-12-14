package org.plugin.domain.command;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResidentCommandHandler {





    public static void residentCommandHandler(CommandSender sender, Player player, String[] args) {
        switch (args[0]) {
            case "선택" -> residentSelect(sender, player, args);
            default -> showHelp(sender, player, args);
        }

    }

    private static void residentSelect(CommandSender sender, Player player, String[] args) {
       Location a = player.getLocation();
       int x = (int) a.getX();
       int z = (int) a.getZ();

       for (int i = -6; i <= 7; i++) {
           a.setX(x + i);
           a.setZ(z + -6);
           player.spawnParticle(Particle.END_ROD,a,2, 0.2,0,0,0);

           a.setX(x + i);
           a.setZ(z + 7);
           player.spawnParticle(Particle.END_ROD,a,2, 0.2,0,0,0);

           a.setX(x + -6);
           a.setZ(z + i);
           player.spawnParticle(Particle.END_ROD,a,2, 0,0,0.2,0);

           a.setX(x + 7);
           a.setZ(z + i);
           player.spawnParticle(Particle.END_ROD,a,2, 0,0,0.2,0);
       }

        player.sendMessage("선택된 곳의 좌표 :"+ x +" "+" "+z);
    }

    private static void showHelp(CommandSender sender, Player player, String[] args) {
        final int residentcost = 10000;
        player.sendMessage("/땅 선택 땅을 선택합니다");
        player.sendMessage("/땅 생성 자신의 땅을 생성합니다 가격은" + residentcost +"원입니다.");
        player.sendMessage("땅은 인당 1개만 가질수 있고 확장이 가능합니다.");
    }


}
