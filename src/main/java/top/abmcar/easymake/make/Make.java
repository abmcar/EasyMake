package top.abmcar.easymake.make;

import org.bukkit.inventory.meta.ItemMeta;
import top.abmcar.easymake.config.ConfigData;
import top.abmcar.easymake.util.EasyStringUtil;
import top.abmcar.easyvar.EasyVar;

import java.util.List;
import java.util.Random;

public class Make {
    public static Make INSTANCE = new Make();

    public boolean isSuccess(ItemMeta itemMeta) {
        int successRate = EasyStringUtil.getSuccessRate(itemMeta);
        Random random = new Random();
        int randomInt = random.nextInt(100);
        return randomInt < successRate;
    }

    public boolean isSuccess(ItemMeta itemMeta, String playerName) {
        int successRate = EasyStringUtil.getSuccessRate(itemMeta);
        Random random = new Random();
        int randomInt = random.nextInt(100);
        return randomInt < successRate + EasyVar.getVarManager().getPlayerValue(playerName, ConfigData.INSTANCE.LUCK_VALUE_KEYWORD);
    }

    public boolean isBroadcast(ItemMeta itemMeta) {
        MakeData nowData = new MakeData(itemMeta.getDisplayName());
        int nowLevel = EasyStringUtil.getMakeLevel(itemMeta);
        return nowData.isBroadcast(nowLevel);
    }

//    public ItemMeta getNewItemMeta(ItemMeta itemMeta) {
//        MakeData nowData = new MakeData(EasyStringUtil.getSimpleDisplayName(itemMeta));
//        int nowLevel = EasyStringUtil.getMakeLevel(itemMeta);
//        List<String> addValueList = nowData.getAddValueList(nowLevel);
//        List<String> materialRequiredList = nowData.getMaterialRequireList(nowLevel);
//        String successRate = nowData.getSuccessRate(nowLevel);
//        itemMeta.setLore(EasyStringUtil.getAttributeLores(itemMeta));
//        itemMeta.setLore(EasyStringUtil.replaceAddValue(itemMeta, addValueList));
//        itemMeta.setLore(EasyStringUtil.replaceMaterialRequire(itemMeta, materialRequiredList));
//        itemMeta.setLore(EasyStringUtil.replaceSuccessRate(itemMeta, successRate));
//        itemMeta.setDisplayName(EasyStringUtil.getNewDisplayName(itemMeta));
//        return itemMeta;
//    }

    public ItemMeta getNewItemMeta(ItemMeta itemMeta, String playerName) {
        MakeData nowData = new MakeData(EasyStringUtil.getSimpleDisplayName(itemMeta));
        int nowLevel = EasyStringUtil.getMakeLevel(itemMeta);
//        List<String> addValueList = nowData.getAddValueList(nowLevel);
//        List<String> materialRequiredList = nowData.getMaterialRequireList(nowLevel);
//        String successRate = nowData.getSuccessRate(nowLevel);
        List<String> addValueList = nowData.getAddValueList(nowLevel, playerName);
        List<String> materialRequiredList = nowData.getMaterialRequireList(nowLevel, playerName);
        String successRate = nowData.getSuccessRate(nowLevel, playerName);
//        if (EasyMake.EasyVar != null) {
//            addValueList = nowData.getAddValueList(nowLevel, playerName);
//            materialRequiredList = nowData.getMaterialRequireList(nowLevel, playerName);
//            successRate = nowData.getSuccessRate(nowLevel, playerName);
//        }
        itemMeta.setLore(EasyStringUtil.getAttributeLores(itemMeta));
        itemMeta.setLore(EasyStringUtil.replaceAddValue(itemMeta, addValueList));
        itemMeta.setLore(EasyStringUtil.replaceMaterialRequire(itemMeta, materialRequiredList));
        itemMeta.setLore(EasyStringUtil.replaceSuccessRate(itemMeta, successRate));
        itemMeta.setDisplayName(EasyStringUtil.getNewDisplayName(itemMeta));
        return itemMeta;
    }

    public Boolean canMake(ItemMeta itemMeta) {
        return EasyStringUtil.hasMakeWord(itemMeta);
    }

    public Boolean isMaxMake(ItemMeta itemMeta) {
        MakeData makeData = new MakeData(EasyStringUtil.getSimpleDisplayName(itemMeta));
        int nowLevel = EasyStringUtil.getMakeLevel(itemMeta);
        return nowLevel <= makeData.getMaxMakeLevel();
    }

    public ItemMeta makeItem(ItemMeta itemMeta, String playerName) {
        itemMeta = getNewItemMeta(itemMeta, playerName);
        return itemMeta;
    }
}
