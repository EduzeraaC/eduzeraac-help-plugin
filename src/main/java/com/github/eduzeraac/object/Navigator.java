package com.github.eduzeraac.object;

import com.github.eduzeraac.action.MenuAction;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@Getter
public class Navigator {

    private final MenuAction menuAction;
    private final ItemStack itemStack;

    public Navigator(MenuAction menuAction, Material material, int amount, int data, String name, List<String> lore) {
        this.menuAction = menuAction;
        this.itemStack = makeItemStack(material, amount, data, name, lore);
    }

    private ItemStack makeItemStack(Material material, int amount, int data, String name, List<String> lore) {
        ItemStack itemStack = new ItemStack(material, amount, (byte) data);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
