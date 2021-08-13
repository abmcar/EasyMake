package top.abmcar.easymake;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import top.abmcar.easymake.command.EasyMakeCommand;
import top.abmcar.easymake.config.Config;
import top.abmcar.easymake.config.ConfigUtil;
import top.abmcar.easymake.menu.MenuClickListener;

public final class EasyMake extends JavaPlugin {
    private static Plugin plugin;
    private Config config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        config = ConfigUtil.loadConfig(plugin, "config.yml");
        getCommand("easymake").setExecutor(new EasyMakeCommand());
        Bukkit.getPluginManager().registerEvents(new MenuClickListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ConfigUtil.saveFile(config);
    }

    public static Plugin getPlugin() {
        return plugin;
    }

}
