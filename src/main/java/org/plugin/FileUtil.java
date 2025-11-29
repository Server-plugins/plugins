package org.plugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Map;

public class FileUtil {
    private final Main plugin = Main.getInstance();

    public FileConfiguration loadYaml(String path) {
        File file = new File(plugin.getDataFolder(), path);
        if (!file.exists()) plugin.saveResource(path, false);

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

    public <T> void upLoadMapToYaml(FileConfiguration fileData, Map<String, T> map, Class<T> type) {
        map.forEach((key, value) -> {
            if (type.isInstance(value)) {
                fileData.set(key, value);
            }
        });
    }
}
