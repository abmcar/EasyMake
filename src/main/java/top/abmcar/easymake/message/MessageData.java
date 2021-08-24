package top.abmcar.easymake.message;

import org.bukkit.configuration.file.YamlConfiguration;
import top.abmcar.easymake.EasyMake;
import top.abmcar.easymake.config.ConfigUtil;

public class MessageData {
    public static MessageData INSTANCE = new MessageData();
    private final YamlConfiguration configuration = ConfigUtil.loadYaml(EasyMake.getPlugin(), "messages.yml");

    public MessageData() {}

    //成功公告
    public String BROADCAST_MESSAGE = configuration.getString("broadcastMessage");
    //成功信息
    public String SUCCESS_MESSAGE = configuration.getString("successMessage");
    //失败信息
    public String FAILURE_MESSAGE = configuration.getString("failureMessage");
    //缺少打造材料
    public String LACK_MATERIAL_MESSAGE = configuration.getString("lackMaterialMessage");
    //不可打造
    public String UNABLE_MAKE_MESSAGE = configuration.getString("unableMakeMessage");
    //缺货数值变化/物品
    public String LACK_EQUIPMENT_MESSAGE = configuration.getString("lackEquipmentMessage");
    //打造上限
    public String MAX_MAKE_LEVEL = configuration.getString("maxMakeLevel");
}
