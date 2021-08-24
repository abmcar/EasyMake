package top.abmcar.easymake.config;

import org.bukkit.configuration.file.YamlConfiguration;
import top.abmcar.easymake.EasyMake;

import java.util.List;

public class ConfigData {
    public static ConfigData INSTANCE = new ConfigData();

    private ConfigData() {}

    private YamlConfiguration config = ConfigUtil.loadYaml(EasyMake.getPlugin(), "config.yml");

    public YamlConfiguration getConfigYaml() {
        return config;
    }

    public void setConfigYaml(YamlConfiguration config) {
        this.config = config;
    }

    //幸运值关键字
    public String LUCK_VALUE_KEYWORD = config.getString("KeyWord.LuckValue", "LuckValue");

    //菜单布局
    public List<String> MENU_SLOTS = config.getStringList("Menu.Slots");
    //默认打造数据文件名
    public String COMMON_MAKE_DATA_NAME = config.getString("commonMakeDataName");
    //菜单名字
    public String MENU_NAME = config.getString("Menu.Title");
    //菜单边框名字
    public String MENU_GLASS_NAME = config.getString("Menu.GlassName");
    //玻璃边框名字
    public String MENU_GLASS_PANE_NAME = config.getString("Menu.GlassPaneName");
    //打造按钮名字
    public String MENU_MAKE_BUTTON_NAME = config.getString("Menu.MakeButtonName");
    //可打造关键字
    public String MAKE_KEYWORD = config.getString("KeyWord.Make");
    //数值变化关键字
    public String ADD_VALUE_KEYWORD = config.getString("KeyWord.AddValue");
    //材料需要关键字
    public String REQUIRE_MATERIAL_KEYWORD = config.getString("KeyWord.RequireMaterial");
    //成功率关键字
    public String SUCCESS_RATE_KEYWORD = config.getString("KeyWord.SuccessRate");
    //损坏关键字
    public String BREAK_KEYWORD = config.getString("KeyWord.Break");

    public void reload() {
        COMMON_MAKE_DATA_NAME = config.getString("commonMakeDataName");
        MENU_NAME = config.getString("Menu.Title");
        MENU_SLOTS = config.getStringList("Menu.Slots");
        MENU_GLASS_NAME = config.getString("Menu.GlassName");
        MENU_GLASS_PANE_NAME = config.getString("Menu.GlassPaneName");
        MENU_MAKE_BUTTON_NAME = config.getString("Menu.MakeButtonName");
        MAKE_KEYWORD = config.getString("KeyWord.Make");
        ADD_VALUE_KEYWORD = config.getString("KeyWord.AddValue");
        REQUIRE_MATERIAL_KEYWORD = config.getString("KeyWord.RequireMaterial");
        SUCCESS_RATE_KEYWORD = config.getString("KeyWord.SuccessRate");
        BREAK_KEYWORD = config.getString("KeyWord.Break");
    }
}
