package com.github.eduzeraac.inventory;

import com.github.eduzeraac.action.MenuAction;
import com.github.eduzeraac.object.Item;
import com.github.eduzeraac.object.Navigator;
import com.github.eduzeraac.service.HelpService;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;
import java.util.Map;

public class Menu implements InventoryHolder {

    public static HelpService HELP_SERVICE;

    private final Inventory inventory;
    private final Map<Integer, Item> pageItems;
    private final Map<Integer, Navigator> pageNavigators;
    @Setter
    @Getter
    private int currentPage;

    public Menu(final String name, final int size) {
        this.inventory = Bukkit.createInventory(this, size, name);
        pageItems = new HashMap<>();
        pageNavigators = new HashMap<>();
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public void addItem(final int slot, final Item item) {
        inventory.setItem(slot, item.getItemStack());
        pageItems.put(slot, item);
    }

    public Item getItem(final int slot) {
        return pageItems.get(slot);
    }

    public void addNavigator(final int slot, final Navigator navigator) {
        inventory.setItem(slot, navigator.getItemStack());
        pageNavigators.put(slot, navigator);
    }

    public Navigator getNavigator(final int slot) {
        return pageNavigators.get(slot);
    }

    public void open(final Player player, final MenuBuilder menuBuilder) {
        menuBuilder.buildMenu(this, player);
        player.openInventory(inventory);
    }

    public void clearInventory() {
        inventory.clear();
        pageItems.clear();
    }

    public void onClickItem(final Item item, final Player player, final MenuBuilder menuBuilder, final int slot) {
        final MenuAction menuAction = item.getMenuAction();
        if (menuAction == null) return;
        menuAction.compute(player, this, menuBuilder, slot);
    }

    public void onClickNavigator(final Navigator navigator, final Player player, final MenuBuilder menuBuilder) {
        final MenuAction menuAction = navigator.getMenuAction();
        if (menuAction == null) return;
        menuAction.compute(player, this, menuBuilder);
    }

    public void openHelp(final Player staffer, final Player player, final String[] help, int slot) {
        HELP_SERVICE.addStafferAnswering(staffer, player, help, getItem(slot));
    }
}
