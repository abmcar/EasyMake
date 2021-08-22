package top.abmcar.easymake;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import top.abmcar.easymake.command.EasyMakeCommand;
import top.abmcar.easymake.config.Config;
import top.abmcar.easymake.config.ConfigData;
import top.abmcar.easymake.config.ConfigUtil;
import top.abmcar.easymake.make.MakeData;
import top.abmcar.easymake.menu.MenuClickListener;
import top.abmcar.easymake.util.MenuUtil;

public final class EasyMake extends JavaPlugin {
    private static Plugin plugin;
    private Config config;

    public static void reload() {
        try {
            try {
                ConfigData.INSTANCE.setConfigYaml(ConfigUtil.loadConfig(plugin, "config.yml").getConfigYaml());
                ConfigData.INSTANCE.reload();
                if (ConfigData.INSTANCE.MENU_SLOTS.isEmpty())
                    plugin.saveResource("config.yml", true);
                MenuUtil.loadMenuSlots();
                plugin.getLogger().info("插件配置载入成功！");
            } catch (Exception e) {
                plugin.saveResource("config.yml", true);
                plugin.getLogger().info("插件配置载入出错！将使用默认配置！");
            }
            try {
                new MakeData("commonMakeData");
                plugin.getLogger().info("菜单布局载入成功！");
            } catch (Exception e) {
                plugin.getLogger().info("菜单布局载入失败！请尝试删除插件数据文件夹后重试！");
            }
        } catch (Exception e) {
            plugin.getLogger().info("插件重载失败！请尝试删除插件数据文件夹后重试！");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ConfigUtil.saveFile(config);
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        try {
            config = ConfigUtil.loadConfig(plugin, "config.yml");
            if (ConfigData.INSTANCE.MENU_SLOTS.isEmpty())
                plugin.saveResource("config.yml", true);
            MenuUtil.loadMenuSlots();
            plugin.getLogger().info("插件配置载入成功！");
        } catch (Exception e) {
            plugin.saveResource("config.yml", true);
            plugin.getLogger().info("插件配置载入出错！将使用默认配置！");
        }
        try {
            new MakeData("commonMakeData");
            plugin.getLogger().info("菜单布局载入成功！");
        } catch (Exception e) {
            plugin.getLogger().info("菜单布局载入失败！请尝试删除插件数据文件夹后重试！");
        }
        getCommand("easymake").setExecutor(new EasyMakeCommand());
        Bukkit.getPluginManager().registerEvents(new MenuClickListener(), this);
    }

}
