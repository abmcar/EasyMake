package top.abmcar.easymake.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import top.abmcar.easymake.EasyMake;
import top.abmcar.easymake.menu.MenuBuilder;

public class EasyMakeCommand  implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if (strings.length == 0)
        {
            if (commandSender instanceof Player)
            {
                Player player = (Player)commandSender;
                MenuBuilder menuBuilder = new MenuBuilder(EasyMake.getPlugin());
                Inventory inventory = menuBuilder.build();
                player.openInventory(inventory);
            }
            return true;
        }
        return false;
    }
}
