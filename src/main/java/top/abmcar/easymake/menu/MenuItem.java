package top.abmcar.easymake.menu;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import top.abmcar.easymake.config.ConfigData;

public class MenuItem {
    public static MenuItem INSTANCE = new MenuItem();

    public ItemStack normalGlass, glassPane, makeButton;

    public MenuItem() {
        setNormalGlass();
        setMakeButton();
        setGlassPane();
    }

    public void reload() {
        setNormalGlass();
        setMakeButton();
        setGlassPane();
    }

    private void setNormalGlass() {
        normalGlass = new ItemStack(Material.getMaterial(ConfigData.INSTANCE.MENU_MATERIAL0));
        ItemMeta itemMeta = normalGlass.getItemMeta();
        itemMeta.setDisplayName(ConfigData.INSTANCE.MENU_GLASS_NAME);
        normalGlass.setItemMeta(itemMeta);
    }

    private void setMakeButton() {
        makeButton = new ItemStack(Material.getMaterial(ConfigData.INSTANCE.MENU_MATERIAL2));
        ItemMeta itemMeta = makeButton.getItemMeta();
        itemMeta.setDisplayName(ConfigData.INSTANCE.MENU_MAKE_BUTTON_NAME);
    }

    private void setGlassPane()
    {
        glassPane = new ItemStack(Material.getMaterial(ConfigData.INSTANCE.MENU_MATERIAL1));
        ItemMeta itemMeta = glassPane.getItemMeta();
        itemMeta.setDisplayName(ConfigData.INSTANCE.MENU_GLASS_PANE_NAME);
        glassPane.setItemMeta(itemMeta);
    }

}