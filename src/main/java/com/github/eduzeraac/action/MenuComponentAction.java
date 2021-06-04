package com.github.eduzeraac.action;

import com.github.eduzeraac.inventory.Menu;
import com.github.eduzeraac.inventory.MenuBuilder;
import org.bukkit.entity.Player;

public interface MenuComponentAction {

    default void compute(Player player, Menu menu, MenuBuilder menuBuilder, int slot) {
        compute(player, menu, menuBuilder);
    }

    default void compute(Player player, Menu menu, MenuBuilder menuBuilder) {
        compute(player);
    }

    default void compute(Player player) {
        throw new UnsupportedOperationException("Not implemented, yet.");
    }
}
