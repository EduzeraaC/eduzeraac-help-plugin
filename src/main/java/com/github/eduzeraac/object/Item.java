package com.github.eduzeraac.object;

import com.github.eduzeraac.action.MenuAction;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Item {

    private final MenuAction menuAction;
    private final ItemStack itemStack;
    private final Player player;
    private final String[] doubt;

    public Item(MenuAction menuAction, Player player, String[] doubt, Material material, int amount, int data, String name) {
        this.menuAction = menuAction;
        this.itemStack = makeItemStack(material, amount, data, name, doubt);
        this.player = player;
        this.doubt = doubt;
    }

    private ItemStack makeItemStack(Material material, int amount, int data, String name, String[] lore) {
        ItemStack itemStack = new ItemStack(material, amount, (byte) data);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(colorLines(lore));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private List<String> colorLines(String[] lore) {
        List<String> newLore = new ArrayList<>();
        for (String string : lore) {
            newLore.add("§7" + string);
        }
        return newLore;
    }
}
