package top.abmcar.easymake.util;

import org.bukkit.inventory.meta.ItemMeta;
import top.abmcar.easymake.config.ConfigData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EasyStringUtil {

    public static Boolean canBreak(ItemMeta itemMeta) {
        List<String> lores = itemMeta.getLore();
        for (String lore : lores)
        {
            if (lore.contains(ConfigData.INSTANCE.BREAK_KEYWORD))
                return true;
        }
        return false;
    }

    public static List<String> getAttributeLores(ItemMeta itemMeta) {
        Map<String, Integer> addValues = getAttributeAddValues(itemMeta);
        List<String> nowLores = itemMeta.getLore();
        int res;
        for (String attributeName : addValues.keySet()) {
            res = 0;
            for (String nowLore : nowLores) {
                if (nowLore.contains(attributeName) && !nowLore.contains(ConfigData.INSTANCE.ADD_VALUE_KEYWORD)) {
                    nowLore = getNewAttributeLore(nowLore, addValues.get(attributeName));
                    nowLores.set(res, nowLore);
                }
                res++;
            }
        }
        return nowLores;
    }

    public static Integer getSuccessRate(ItemMeta itemMeta) {
        int successRate = 0;
        List<String> nowLores = itemMeta.getLore();
        for (String nowLore : nowLores) {
            if (nowLore.contains(ConfigData.INSTANCE.SUCCESS_RATE_KEYWORD)) {
                successRate = getLoreInteger(nowLore);
                break;
            }
        }
        return successRate;
    }

    public static Map<String, Integer> getMaterialCount(ItemMeta itemMeta) {
        Map<String, Integer> stringIntegerMap = new HashMap<>();
        List<String> nowLores = itemMeta.getLore();
        for (String nowLore : nowLores) {
            if (nowLore.contains(ConfigData.INSTANCE.REQUIRE_MATERIAL_KEYWORD)) {
                String materialName = getMaterialName(nowLore);
                Integer materialCount = getLoreInteger(nowLore);
                stringIntegerMap.put(materialName, materialCount);
            }
        }
        return stringIntegerMap;
    }

    public static Boolean isRequired(ItemMeta itemMeta, String keyWord) {
        if (itemMeta.getDisplayName().contains(keyWord))
            return true;
        List<String> nowLores = itemMeta.getLore();
        if (nowLores != null)
            for (String nowLore : nowLores) {
                if (nowLore.contains(keyWord))
                    return true;
            }
        return false;
    }

    public static Integer getMakeLevel(ItemMeta itemMeta) {
        String displayName = itemMeta.getDisplayName();
        return getLoreInteger(displayName);
    }

    public static String getNewDisplayName(ItemMeta itemMeta) {
        String nowDisplayName = itemMeta.getDisplayName();
        if (getMakeLevel(itemMeta) == 0)
            nowDisplayName = nowDisplayName.concat(" §f+1");
        else
            nowDisplayName = getNewAttributeLore(nowDisplayName, 1);
        return nowDisplayName;
    }

    public static String getSimpleDisplayName(ItemMeta itemMeta) {
        String newDisplayName = getSimpleString(itemMeta.getDisplayName());
        newDisplayName = newDisplayName.replace(getLoreInteger(newDisplayName).toString(), "");
        return newDisplayName;
    }

    public static Boolean hasMakeWord(ItemMeta itemMeta) {
        List<String> nowLores = itemMeta.getLore();
        if (nowLores != null)
            for (String lore : nowLores) {
                if (lore.contains(ConfigData.INSTANCE.MAKE_KEYWORD))
                    return true;
            }
        return false;
    }

    public static List<String> replaceMaterialRequire(ItemMeta itemMeta, List<String> replaceList) {
        List<String> nowLores = itemMeta.getLore();
        List<String> deleteList = new ArrayList<>();
        int firstPos = 0;
        for (String lore : nowLores) {
            if (lore.contains(ConfigData.INSTANCE.REQUIRE_MATERIAL_KEYWORD)) {
                if (firstPos == 0)
                    firstPos = nowLores.indexOf(lore);
                deleteList.add(lore);
//                nowLores.remove(lore);
            }
        }
        for (String lore : deleteList)
            nowLores.remove(lore);
        for (String lore : replaceList)
            nowLores.add(firstPos, lore);
        return nowLores;
    }

    public static List<String> replaceAddValue(ItemMeta itemMeta, List<String> replaceList) {
        List<String> nowLores = itemMeta.getLore();
        List<String> deleteList = new ArrayList<>();
        int firstPos = 0;
        for (String lore : nowLores) {
            if (lore.contains(ConfigData.INSTANCE.ADD_VALUE_KEYWORD)) {
                if (firstPos == 0)
                    firstPos = nowLores.indexOf(lore);
                deleteList.add(lore);
//                nowLores.remove(lore);
            }
        }
        for (String lore : deleteList)
            nowLores.remove(lore);
        for (String lore : replaceList)
            nowLores.add(firstPos, lore);
        return nowLores;
    }

    public static List<String> replaceSuccessRate(ItemMeta itemMeta, String newSuccessRate) {
        List<String> nowLores = itemMeta.getLore();
        for (String lore : nowLores) {
            if (lore.contains(ConfigData.INSTANCE.SUCCESS_RATE_KEYWORD)) {
                Integer nowSuccessRate = getLoreInteger(lore);
                String newLore = lore.replace(nowSuccessRate.toString(), newSuccessRate);
                nowLores.set(nowLores.indexOf(lore), newLore);
                break;
            }
        }
        return nowLores;
    }

    private static String getSimpleString(String string) {
        if (string == null)
            return "Null";
        if (string.isEmpty())
            return "Null";
        string = string.replace(" ", "");
        string = string.replace("§1", "");
        string = string.replace("§2", "");
        string = string.replace("§3", "");
        string = string.replace("§4", "");
        string = string.replace("§5", "");
        string = string.replace("§6", "");
        string = string.replace("§7", "");
        string = string.replace("§8", "");
        string = string.replace("§9", "");
        string = string.replace("§0", "");
        string = string.replace("§a", "");
        string = string.replace("§b", "");
        string = string.replace("§c", "");
        string = string.replace("§d", "");
        string = string.replace("§e", "");
        string = string.replace("§f", "");
        string = string.replace("§g", "");
        string = string.replace("§h", "");
        string = string.replace("§i", "");
        string = string.replace("&1", "");
        string = string.replace("&2", "");
        string = string.replace("&3", "");
        string = string.replace("&4", "");
        string = string.replace("&5", "");
        string = string.replace("&6", "");
        string = string.replace("&7", "");
        string = string.replace("&8", "");
        string = string.replace("&9", "");
        string = string.replace("&0", "");
        string = string.replace("&a", "");
        string = string.replace("&b", "");
        string = string.replace("&c", "");
        string = string.replace("&d", "");
        string = string.replace("&e", "");
        string = string.replace("&f", "");
        string = string.replace("&g", "");
        string = string.replace("&h", "");
        string = string.replace("&i", "");
        string = string.replace("*", "");
        string = string.replace("%", "");
        string = string.replace(":", "");
        string = string.replace("+", "");
        string = string.replace("•", "");
        string = string.replace("°", "");
        string = string.replace("⁜", "");
        string = string.replace("※", "");
        string = string.replace("⋇", "");
        string = string.replace("⊙", "");
        string = string.replace("⋆", "");
        string = string.replace("⋅", "");
        string = string.replace("☉", "");
        string = string.replace("☪", "");
        return string;
    }

    private static Integer getLoreInteger(String lore) {
        String tempString = getSimpleString(lore);
        for (int i = 1; i < tempString.length(); i++) {
            if (tempString.charAt(i) >= '0' && tempString.charAt(i) <= '9') {
                return Integer.parseInt(tempString.substring(i));
            }
        }
        return 0;
    }

    private static String getAttributeName(String lore) {
        String simpleString = getSimpleString(lore);
        simpleString = simpleString.replace(ConfigData.INSTANCE.ADD_VALUE_KEYWORD, "");
        for (int i = 0; i < simpleString.length(); i++) {
            if (simpleString.charAt(i) >= '0' && simpleString.charAt(i) <= '9') {
                simpleString = simpleString.substring(0, i);
                break;
            }
        }
        return simpleString;
    }

    private static String getMaterialName(String lore) {
        String simpleString = getSimpleString(lore);
        String nowInteger = getLoreInteger(lore).toString();
        simpleString = simpleString.replace(nowInteger, "");
        simpleString = simpleString.substring(simpleString.indexOf(ConfigData.INSTANCE.REQUIRE_MATERIAL_KEYWORD) + ConfigData.INSTANCE.REQUIRE_MATERIAL_KEYWORD.length());
        return simpleString;
    }

    private static String getNewAttributeLore(String lore, Integer addValue) {
        Integer originalInteger = getLoreInteger(lore);
        int newInteger = originalInteger + addValue;
        lore = lore.replace(originalInteger.toString(), Integer.toString(newInteger));
        return lore;
    }

    private static Map<String, Integer> getAttributeAddValues(ItemMeta itemMeta) {
        List<String> lores = itemMeta.getLore();
        Map<String, Integer> addValues = new HashMap<>();
        for (String lore : lores) {
            if (lore.contains(ConfigData.INSTANCE.ADD_VALUE_KEYWORD)) {
                String nowAttributeName = getAttributeName(lore);
                Integer addValue = getLoreInteger(lore);
                addValues.put(nowAttributeName, addValue);
            }
        }
        return addValues;
    }
}
