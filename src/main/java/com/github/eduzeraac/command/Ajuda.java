package com.github.eduzeraac.command;

import com.github.eduzeraac.action.MenuAction;
import com.github.eduzeraac.inventory.Menu;
import com.github.eduzeraac.inventory.MenuBuilder;
import com.github.eduzeraac.service.HelpService;
import com.github.eduzeraac.object.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ajuda implements CommandExecutor {

    private final MenuBuilder menuBuilder;
    private final HelpService helpService;

    public Ajuda(MenuBuilder menuBuilder, HelpService helpService) {
        this.menuBuilder = menuBuilder;
        this.helpService = helpService;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.broadcastMessage("§cApenas jogadores tem acesso a este comando.");
            return false;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            Menu menu = new Menu("Ajuda", 6 * 9);
            menu.open(player, menuBuilder);
            return true;
        }
        final String[] message = String.join(" ", args).split("/n");
        helpService.addPlayerInDoubts(player, new Item(MenuAction.OPEN_HELP, player, message, Material.SKULL_ITEM, 1, 3, "§6" + player.getName()));
        return false;
    }
}
