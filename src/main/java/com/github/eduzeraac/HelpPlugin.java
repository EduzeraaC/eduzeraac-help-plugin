package com.github.eduzeraac;

import com.github.eduzeraac.command.Ajuda;
import com.github.eduzeraac.inventory.Menu;
import com.github.eduzeraac.inventory.MenuBuilder;
import com.github.eduzeraac.listener.Listeners;
import com.github.eduzeraac.service.HelpService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class HelpPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        MenuBuilder menuBuilder = new MenuBuilder();
        HelpService helpService = new HelpService(menuBuilder);
        Menu.HELP_SERVICE = helpService;
        Bukkit.getServer().getPluginManager().registerEvents(new Listeners(menuBuilder, helpService), this);
        getCommand("ajuda").setExecutor(new Ajuda(menuBuilder, helpService));
    }
}
