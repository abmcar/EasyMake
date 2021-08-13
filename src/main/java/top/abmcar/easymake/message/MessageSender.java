package top.abmcar.easymake.message;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import top.abmcar.easymake.EasyMake;

public class MessageSender {
    public static MessageSender INSTANCE = new MessageSender();

    public MessageSender() {}

    public void serverBroadcast(Player player, ItemMeta itemMeta, boolean success) {
        String nowString = MessageData.INSTANCE.BROADCAST_MESSAGE;
        nowString = replaceColor(nowString);
        nowString = replacePlaceholder(nowString, player.getName(), itemMeta.getDisplayName(), success);
        Bukkit.broadcastMessage(nowString);
    }

    public void sendMessage(CommandSender commandSender, String message) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            message = replacePlaceholder(message, player.getName());
            player.sendMessage(message);
            return;
        }
        message = removeColors(message);
        EasyMake.getPlugin().getLogger().info(message);
    }

    private String removeColors(String string) {
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
        return string;
    }

    private String replaceColor(String string) {
        string = string.replace("&", "§");
        return string;
    }

    private String replacePlaceholder(String string, String playerName, String itemName, Boolean success) {
        string = string.replace("<player>", playerName);
        string = string.replace("<item>", itemName);
        if (success)
            string = string.replace("<success>", "成功");
        else
            string = string.replace("<success>", "失败");
        return string;
    }

    private String replacePlaceholder(String string, String playerName) {
        string = string.replace("<player>", playerName);
        return string;
    }


}
