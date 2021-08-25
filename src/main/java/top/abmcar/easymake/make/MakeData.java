package top.abmcar.easymake.make;

import org.bukkit.configuration.file.YamlConfiguration;
import top.abmcar.easymake.config.Config;
import top.abmcar.easymake.config.ConfigData;
import top.abmcar.easymake.config.ConfigUtil;
import top.abmcar.easymake.util.CalUtil;

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

    public List<String> getCalRules() {
        return yamlConfiguration.getStringList("calRules");
    }

    public List<String> replaceCalRule(List<String> strings, Integer nowLevel) {
        List<String> rules = getCalRules();
        for (String lore : strings) {
            for (String nowRule : rules) {
                if (lore.contains(nowRule)) {
                    String tempString = lore;
                    lore = lore.replace(nowRule, CalUtil.getCalAns(yamlConfiguration.getString(nowRule, "<nowLevel>"), nowLevel));
                    strings.set(strings.indexOf(tempString), lore);
                }
            }
        }
        return strings;
    }

    public List<String> getMaterialRequireList(Integer nowLevel) {
        return replaceCalRule(yamlConfiguration.getStringList(nowLevel.toString() + ".MaterialRequire"), nowLevel);
    }

    public List<String> getAddValueList(Integer nowLevel) {
        return replaceCalRule(yamlConfiguration.getStringList(nowLevel.toString() + ".AddValue"), nowLevel);
    }

    public String getSuccessRate(Integer nowLevel) {
        return CalUtil.getCalAns(yamlConfiguration.getString(nowLevel.toString() + ".SuccessRate"), nowLevel);
    }

    public Boolean isBroadcast(Integer nowLevel) {
        return yamlConfiguration.getBoolean(nowLevel.toString() + ".Broadcast");
    }

    public Integer getMaxMakeLevel() {
        return yamlConfiguration.getInt("MaxMakeLevel", 32);
    }

//    public Boolean isBreak(Integer nowLevel) {
//        return yamlConfiguration.getBoolean(nowLevel.toString() + ".Break");
//    }
}
