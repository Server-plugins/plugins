package org.plugin.util;

import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

@RequiredArgsConstructor
public class Scheduler {
    private final BukkitScheduler scheduler;
    private final JavaPlugin plugin;

    public void task(Runnable action, long delay, long period) {
        scheduler.runTaskTimer(plugin, action, delay, period);
    }
}
