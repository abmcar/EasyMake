package top.abmcar.easymake.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import top.abmcar.easymake.EasyMake;

import java.io.File;
import java.io.IOException;

public class ConfigUtil {
    @SuppressWarnings("all")
    public static YamlConfiguration loadYaml(Plugin plugin, String configName) {
        File pluginDataFolder = plugin.getDataFolder();
        File configFile = new File(pluginDataFolder, configName);
        if (!pluginDataFolder.exists()) pluginDataFolder.mkdir();
        if (!configFile.exists()) {
            plugin.saveResource(configName, true);
        }
        return YamlConfiguration.loadConfiguration(configFile);
    }

    @SuppressWarnings("all")
    public static Config loadConfig(Plugin plugin, String configName) {
        File pluginDataFolder = plugin.getDataFolder();
        File configFile = new File(pluginDataFolder, configName);
        if (!pluginDataFolder.exists()) pluginDataFolder.mkdir();
        if (!configFile.exists()) {
            try {
                if (!configName.equalsIgnoreCase("config.yml"))
                    configFile.createNewFile();
                else
                    plugin.saveResource(configName, true);
            } catch (Exception e) {
                plugin.getLogger().info("创建" + configFile + "文件失败");
                plugin.getLogger().info(e.toString());
            }
        }
        return new Config(pluginDataFolder, configFile, YamlConfiguration.loadConfiguration(configFile));
    }

    @SuppressWarnings("all")
    public static Boolean isMakeDataExist(String itemName) {
        Plugin plugin = EasyMake.getPlugin();
        File pluginDataFolder = plugin.getDataFolder();
        if (!pluginDataFolder.exists()) pluginDataFolder.mkdir();
        File makeDataFolder = new File(pluginDataFolder, "makeData");
        if (!makeDataFolder.exists()) makeDataFolder.mkdir();
        File makeDataFile = new File(makeDataFolder, itemName + ".yml");
        return makeDataFile.exists();
    }

    public static Config loadMakeDataConfig(String itemName) {
        Plugin plugin = EasyMake.getPlugin();
        File pluginDataFolder = plugin.getDataFolder();
        File makeDataFolder = new File(pluginDataFolder, "makeData");
        File makeDataFile = new File(makeDataFolder, itemName + ".yml");
        if (itemName.equalsIgnoreCase(ConfigData.INSTANCE.COMMON_MAKE_DATA_NAME) && !makeDataFile.exists())
            plugin.saveResource("makeData\\" + itemName + ".yml", true);
        return new Config(makeDataFolder, makeDataFile, YamlConfiguration.loadConfiguration(makeDataFile));
    }

    public static void saveFile(Config config) {
        YamlConfiguration yaml = config.getConfigYaml();
        try {
            yaml.save(config.getConfigFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
