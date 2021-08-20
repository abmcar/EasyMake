package top.abmcar.easymake.util;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import top.abmcar.easymake.config.ConfigData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuUtil {
    private static final int[] materialSlots = {10, 11, 12, 13, 14, 15, 16};
    private static final int equipmentSlot = 29, makeButtonSlot = 33;
    private static InventoryClickEvent inventoryClickEvent;
    private static final int[] glass = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};
    private static final int[] glassPane = {19, 20, 21, 22, 23, 24, 25, 28, 30, 31, 32, 34, 37, 38, 39, 40, 41, 42, 43};

    public static int[] getGlass() {
        return glass;
    }

    public static int[] getGlassPane() {
        return glassPane;
    }

    public static int[] getMaterialSlots() {
        return materialSlots;
    }

    public static int getEquipmentSlot() {
        return equipmentSlot;
    }

    public static int getMakeButtonSlot() {
        return makeButtonSlot;
    }

    public static Boolean isMakeInventory() {
        return inventoryClickEvent.getView().getTitle().equalsIgnoreCase(ConfigData.INSTANCE.MENU_NAME);
    }

    public static Boolean isMaterialRequire(Map<String, Integer> materialRequire, Inventory inventory) {
        for (String nowMaterial : materialRequire.keySet()) {
            for (int i : materialSlots) {
                ItemStack itemStack = inventory.getItem(i);
                if (itemStack == null)
                    continue;
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (EasyStringUtil.isRequired(itemMeta, nowMaterial)) {
                    materialRequire.put(nowMaterial, materialRequire.get(nowMaterial) - itemStack.getAmount());
                }
            }
        }
        for (String nowMaterial : materialRequire.keySet())
            if (materialRequire.get(nowMaterial) > 0)
                return false;
        return true;
    }

    public static List<Integer> getGlassSlot() {
        List<Integer> glassSlot = new ArrayList<>();
        for (int i : glass)
            glassSlot.add(i);
        for (int i : glassPane)
            glassSlot.add(i);
        return glassSlot;
    }

    public static void setInventoryClickEvent(InventoryClickEvent inventoryClickEvent) {
        MenuUtil.inventoryClickEvent = inventoryClickEvent;
    }

}
