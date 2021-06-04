package com.github.eduzeraac.inventory;

import com.github.eduzeraac.action.MenuAction;
import com.github.eduzeraac.object.Item;
import com.github.eduzeraac.object.Navigator;
import com.google.common.collect.Lists;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.*;

@Getter
public class MenuBuilder {

    private final List<Item> content = new ArrayList<>();
    private static final int[] validateSlots = { 10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41 , 42, 43 };
    private List<List<Item>> pages;

    public void addItem(Item item) {
        content.add(item);
    }

    public void removeItem(Item item) {
        content.remove(item);
    }

    public int getAmountPages() {
        return pages.size();
    }

    public void buildMenu(Menu menu, Player player) {
        menu.clearInventory();
        menu.addNavigator(53, new Navigator(MenuAction.NEXT_PAGE, Material.ARROW, 1, 0, "§aAvançar", Arrays.asList("§7Clique para avançar")));
        menu.addNavigator(45, new Navigator(MenuAction.BACK_PAGE, Material.ARROW, 1, 0, "§cVoltar", Arrays.asList("§7Clique para voltar")));
        menu.addNavigator(49, new Navigator(MenuAction.VIEW_TOP, Material.DIAMOND, 1, 0, "§eTop", Arrays.asList("§7Clique para ver o TOP.")));
        if (!player.hasPermission("eajuda.acesso")) {
            menu.addNavigator(22, new Navigator(MenuAction.NO_PERM, Material.BARRIER, 1, 0, "§cSem acesso", Arrays.asList("§7Você não tem permissão para ver as dúvidas.")));
            return;
        }
        pages = Lists.partition(content, 28);
        if (pages.isEmpty()) return;
        final List<Item> items = pages.get(menu.getCurrentPage());
        if (items == null) return;
        for (int index = 0; index < items.size(); index++) {
            menu.addItem(validateSlots[index], items.get(index));
        }
    }
}
