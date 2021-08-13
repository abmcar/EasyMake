package top.abmcar.easymake.make;

import org.bukkit.configuration.file.YamlConfiguration;
import top.abmcar.easymake.config.Config;
import top.abmcar.easymake.config.ConfigData;
import top.abmcar.easymake.config.ConfigUtil;

import java.util.List;

public class MakeData {
    private final YamlConfiguration yamlConfiguration;

    public MakeData(String itemName) {
        Config config;
        if (ConfigUtil.isMakeDataExist(itemName))
            config = ConfigUtil.loadMakeDataConfig(itemName);
        else
            config = ConfigUtil.loadMakeDataConfig(ConfigData.INSTANCE.COMMON_MAKE_DATA_NAME);
        yamlConfiguration = config.getConfigYaml();
    }

    public List<String> getMaterialRequireList(Integer nowLevel) {
        return yamlConfiguration.getStringList(nowLevel.toString() + ".MaterialRequire");
    }

    public List<String> getAddValueList(Integer nowLevel) {
        return yamlConfiguration.getStringList(nowLevel.toString() + ".AddValue");
    }

    public String getSuccessRate(Integer nowLevel) {
        return yamlConfiguration.getString(nowLevel.toString() + ".SuccessRate");
    }

    public Boolean isBroadcast(Integer nowLevel) {
        return yamlConfiguration.getBoolean(nowLevel.toString() + ".Broadcast");
    }

//    public Boolean isBreak(Integer nowLevel) {
//        return yamlConfiguration.getBoolean(nowLevel.toString() + ".Break");
//    }
}
