package org.plugin.util;

import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class FileUtil {
    private final JavaPlugin plugin;

    public FileConfiguration loadYaml(String path) {
        File file = new File(plugin.getDataFolder(), path);
        if (!file.exists()) plugin.saveResource(path, true);

        return YamlConfiguration.loadConfiguration(file);
    }

    public <T> void upLoadYamlToMap(FileConfiguration fileData, Map<String, T> map, Class<T> type) {
        for (String key : fileData.getKeys(false)) {
            Object value = fileData.get(key);
            if (type.isInstance(value)) {
                map.put(key, (T) value);
            }
        }
    }

    public <T> void upLoadMapToYaml(FileConfiguration fileData, Map<String, T> map, Class<T> type, String fileName) {
        map.forEach((key, value) -> {
            if (type.isInstance(value)) {
                fileData.set(key, value);
            }
        });
        try {
            fileData.save(new File(plugin.getDataFolder(), fileName));
        } catch (IOException e) {}
    }
}
