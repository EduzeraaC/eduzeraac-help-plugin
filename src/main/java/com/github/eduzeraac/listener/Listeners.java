package com.github.eduzeraac.listener;

import com.github.eduzeraac.inventory.Menu;
import com.github.eduzeraac.inventory.MenuBuilder;
import com.github.eduzeraac.object.Item;
import com.github.eduzeraac.object.Navigator;
import com.github.eduzeraac.service.HelpService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener {

    private final MenuBuilder menuBuilder;
    private final HelpService helpService;

    public Listeners(MenuBuilder menuBuilder, HelpService helpService) {
        this.menuBuilder = menuBuilder;
        this.helpService = helpService;
    }

    @EventHandler
    private void onClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || !(event.getInventory().getHolder() instanceof Menu)) return;
        final Menu menu = (Menu) event.getInventory().getHolder();
        final Item item = menu.getItem(event.getSlot());
        if (item != null) {
            event.setCancelled(true);
            menu.onClickItem(item, (Player) event.getWhoClicked(), menuBuilder, event.getSlot());
            return;
        }
        final Navigator navigator = menu.getNavigator(event.getSlot());
        if (navigator != null) {
            event.setCancelled(true);
            menu.onClickNavigator(navigator, (Player) event.getWhoClicked(), menuBuilder);
        }
    }

    @EventHandler
    private void onChat(AsyncPlayerChatEvent event) {
        if (!helpService.staffAnswering(event.getPlayer())) return;
        String answer = event.getMessage();
        event.setCancelled(true);
        if (answer.equalsIgnoreCase("cancelar")) {
            helpService.cancelAnswering(event.getPlayer());
            return;
        }
        helpService.respondDoubt(event.getPlayer(), answer);
    }

    @EventHandler
    private void onQuit(PlayerQuitEvent event) {
        if (!helpService.playerDoubts(event.getPlayer())) return;
        helpService.removePlayerDoubts(event.getPlayer());
    }
}
