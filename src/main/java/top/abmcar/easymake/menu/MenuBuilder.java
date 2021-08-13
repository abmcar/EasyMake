package top.abmcar.easymake.menu;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import top.abmcar.easymake.config.ConfigData;
import top.abmcar.easymake.util.MenuUtil;

public class MenuBuilder {
    private Plugin plugin;
    private final Inventory inventory;

    public MenuBuilder(Plugin plugin) {
        setPlugin(plugin);
        inventory = Bukkit.createInventory(null, 54, ConfigData.INSTANCE.MENU_NAME);
    }

    public Inventory build() {
        int[] glass = MenuUtil.getGlass();
        for (int i : glass)
            inventory.setItem(i, MenuItem.INSTANCE.normalGlass);
        int[] glassPane = MenuUtil.getGlassPane();
        for (int i : glassPane)
            inventory.setItem(i, MenuItem.INSTANCE.glassPane);
        inventory.setItem(MenuUtil.getMakeButtonSlot(), MenuItem.INSTANCE.makeButton);
        return inventory;
    }


    private void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }
}
