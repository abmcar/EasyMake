package top.abmcar.easymake.menu;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import top.abmcar.easymake.config.ConfigData;
import top.abmcar.easymake.make.Make;
import top.abmcar.easymake.message.MessageData;
import top.abmcar.easymake.message.MessageSender;
import top.abmcar.easymake.util.EasyStringUtil;
import top.abmcar.easymake.util.MenuUtil;

import java.util.List;
import java.util.Map;
import java.util.Vector;

public class MenuClickListener implements Listener {


    @EventHandler
    public void onPlayerClickInv(InventoryClickEvent event) {
        MenuUtil.setInventoryClickEvent(event);
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getView().getTopInventory();
        if (!MenuUtil.isMakeInventory())
            return;
        List<Integer> glassSlots = MenuUtil.getGlassSlot();
        if (glassSlots.contains(event.getRawSlot()))
            event.setCancelled(true);
        if (event.getRawSlot() == MenuUtil.getMakeButtonSlot()) {
            event.setCancelled(true);
            ItemStack equipmentItemStack = inventory.getItem(MenuUtil.getEquipmentSlot());
            if (equipmentItemStack == null) {
                MessageSender.INSTANCE.sendMessage(player, MessageData.INSTANCE.LACK_EQUIPMENT_MESSAGE);
                return;
            }
            ItemMeta equipmentMeta = equipmentItemStack.getItemMeta();
            if (!Make.INSTANCE.canMake(equipmentItemStack.getItemMeta())) {
                MessageSender.INSTANCE.sendMessage(player, MessageData.INSTANCE.UNABLE_MAKE_MESSAGE);
            } else {
                if (!Make.INSTANCE.isMaxMake(equipmentMeta)) {
                    MessageSender.INSTANCE.sendMessage(player, MessageData.INSTANCE.MAX_MAKE_LEVEL);
                    return;
                }
                List<String> lores = equipmentMeta.getLore();
                if (lores == null) {
                    MessageSender.INSTANCE.sendMessage(player, MessageData.INSTANCE.UNABLE_MAKE_MESSAGE);
                    return;
                }
                Map<String, Integer> materialRequire = EasyStringUtil.getMaterialCount(equipmentMeta);
                Map<String, Integer> tempRequire = EasyStringUtil.getMaterialCount(equipmentMeta);
                if (!MenuUtil.isMaterialRequire(tempRequire, inventory)) {
                    MessageSender.INSTANCE.sendMessage(player, MessageData.INSTANCE.LACK_MATERIAL_MESSAGE);
                    return;
                }
                Vector<Integer> materialSlots = MenuUtil.getMaterialSlots();
                for (String nowRequire : materialRequire.keySet()) {
                    for (int i : materialSlots) {
                        ItemStack nowItemStack = inventory.getItem(i);
                        if (nowItemStack == null)
                            continue;
                        ItemMeta itemMeta = nowItemStack.getItemMeta();
                        if (EasyStringUtil.isRequired(itemMeta, nowRequire)) {
                            int reduceAmount = Math.min(nowItemStack.getAmount(), materialRequire.get(nowRequire));
                            nowItemStack.setAmount(nowItemStack.getAmount() - reduceAmount);
                            materialRequire.put(nowRequire, materialRequire.get(nowRequire) - reduceAmount);
                            if (materialRequire.get(nowRequire) == 0)
                                break;
                        }
                    }
                }
                ItemStack successItemStack;
                successItemStack = inventory.getItem(MenuUtil.getEquipmentSlot());
                boolean isSuccess = Make.INSTANCE.isSuccess(equipmentMeta);
                if (isSuccess) {
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 0);
                    successItemStack.setItemMeta(Make.INSTANCE.makeItem(equipmentMeta));
                    MessageSender.INSTANCE.sendMessage(player, MessageData.INSTANCE.SUCCESS_MESSAGE);
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1, 0);
                    MessageSender.INSTANCE.sendMessage(player, MessageData.INSTANCE.FAILURE_MESSAGE);
                    if (EasyStringUtil.canBreak(equipmentMeta))
                        successItemStack = new ItemStack(Material.AIR);
                }
                if (Make.INSTANCE.isBroadcast(equipmentMeta))
                    MessageSender.INSTANCE.serverBroadcast(player, equipmentMeta, isSuccess);
                boolean ok = false;
                for (int i = 0; i < inventory.getSize(); i++) {
                    if (inventory.getItem(i) == null) {
                        ok = true;
                        inventory.setItem(i, successItemStack);
                        break;
                    }
                }
                if (!ok)
                    player.getInventory().addItem(successItemStack);
                equipmentItemStack.setAmount(equipmentItemStack.getAmount() - 1);
                inventory.setItem(MenuUtil.getEquipmentSlot(), equipmentItemStack);
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerCloseInv(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory inventory = event.getInventory();
        if (event.getView().getTitle().equalsIgnoreCase(ConfigData.INSTANCE.MENU_NAME)) {
            for (int i : MenuUtil.getMaterialSlots()) {
                if (inventory.getItem(i) != null)
                    player.getInventory().addItem(inventory.getItem(i));
            }
            if (inventory.getItem(MenuUtil.getEquipmentSlot()) != null)
                player.getInventory().addItem(inventory.getItem(MenuUtil.getEquipmentSlot()));
        }
    }

}
