package top.abmcar.easymake.util;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import top.abmcar.easymake.config.ConfigData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class MenuUtil {
    private static final Vector<Integer> glass = new Vector<>();
    private static InventoryClickEvent inventoryClickEvent;
    private static final Vector<Integer> glassPane = new Vector<>();
    private static final Vector<Integer> materialSlots = new Vector<>();
    private static int equipmentSlot = 29, makeButtonSlot = 33;

    public static Vector<Integer> getGlass() {
        return glass;
    }

    public static Vector<Integer> getGlassPane() {
        return glassPane;
    }

    public static Vector<Integer> getMaterialSlots() {
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
        List<Integer> glassSlot = new ArrayList<>(glass);
        glassSlot.addAll(glassPane);
        return glassSlot;
    }

    public static void setInventoryClickEvent(InventoryClickEvent inventoryClickEvent) {
        MenuUtil.inventoryClickEvent = inventoryClickEvent;
    }

    public static void loadMenuSlots() {
        List<String> menuSlots = ConfigData.INSTANCE.MENU_SLOTS;
        int cnt = 0;
        for (String nowString : menuSlots) {
            for (int j = 0; j < nowString.length(); j++) {
                if (nowString.charAt(j) == '*')
                    glass.add(cnt);
                if (nowString.charAt(j) == '0')
                    materialSlots.add(cnt);
                if (nowString.charAt(j) == '-')
                    glassPane.add(cnt);
                if (nowString.charAt(j) == '1')
                    equipmentSlot = cnt;
                if (nowString.charAt(j) == '2')
                    makeButtonSlot = cnt;
                cnt++;
            }
        }
    }

}
