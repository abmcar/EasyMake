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

    private void setNormalGlass() {
        normalGlass = new ItemStack(Material.GLASS);
        ItemMeta itemMeta = normalGlass.getItemMeta();
        itemMeta.setDisplayName(ConfigData.INSTANCE.MENU_GLASS_NAME);
        normalGlass.setItemMeta(itemMeta);
    }

    private void setMakeButton()
    {
        makeButton = new ItemStack(Material.ANVIL);
        ItemMeta itemMeta = makeButton.getItemMeta();
        itemMeta.setDisplayName(ConfigData.INSTANCE.MENU_MAKE_BUTTON_NAME);
    }

    private void setGlassPane()
    {
        glassPane = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta itemMeta = glassPane.getItemMeta();
        itemMeta.setDisplayName(ConfigData.INSTANCE.MENU_GLASS_PANE_NAME);
        glassPane.setItemMeta(itemMeta);
    }

}