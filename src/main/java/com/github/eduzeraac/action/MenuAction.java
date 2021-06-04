package com.github.eduzeraac.action;

import com.github.eduzeraac.inventory.Menu;
import com.github.eduzeraac.inventory.MenuBuilder;
import org.bukkit.entity.Player;

public enum MenuAction implements MenuComponentAction {

    NEXT_PAGE {
        @Override
        public void compute(Player player, Menu menu, MenuBuilder menuBuilder) {
            menu.setCurrentPage(menu.getCurrentPage() >= menuBuilder.getAmountPages() - 1 ? 0 : menu.getCurrentPage() + 1);
            menu.open(player, menuBuilder);
        }
    },
    BACK_PAGE {
        @Override
        public void compute(Player player, Menu menu, MenuBuilder menuBuilder) {
            menu.setCurrentPage(Math.max(0, menu.getCurrentPage() - 1));
            menu.open(player, menuBuilder);
        }
    },
    NO_PERM {
        @Override
        public void compute(Player player) {
            player.sendMessage("FAZER");
            player.closeInventory();
        }
    },
    VIEW_TOP {
        @Override
        public void compute(Player player) {
            player.sendMessage("FAZER");
            player.closeInventory();
        }
    },
    OPEN_HELP {
        @Override
        public void compute(Player player, Menu menu, MenuBuilder menuBuilder, int slot) {
            String[] help = menu.getItem(slot).getDoubt();
            Player playerHelp = menu.getItem(slot).getPlayer();
            menu.openHelp(player, playerHelp, help, slot);
        }
    };
}

