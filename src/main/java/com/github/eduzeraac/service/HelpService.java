package com.github.eduzeraac.service;

import com.github.eduzeraac.inventory.MenuBuilder;
import com.github.eduzeraac.object.Item;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.*;

@Getter
public class HelpService {

    private final MenuBuilder menuBuilder;
    private final Map<String, Item> doubts;
    private final Map<String, Item> answering;

    public HelpService(MenuBuilder menuBuilder) {
        this.doubts = new HashMap<>();
        this.answering = new HashMap<>();
        this.menuBuilder = menuBuilder;
    }

    public void addPlayerInDoubts(Player player, Item item) {
        if (doubts.containsKey(player.getName())) {
            player.sendMessage("§cVocê já tem uma dúvida em aberto.");
            return;
        }
        doubts.put(player.getName(), item);
        menuBuilder.addItem(item);
        player.sendMessage("§aDúvida enviada com sucesso.");
    }

    public void respondDoubt(Player staffer, String answer) {
        Item item = answering.get(staffer.getName());
        Player player = item.getPlayer();
        staffer.sendMessage("§aVocê respondeu a dúvida de§f: " + player.getName() + "§a.");
        player.sendMessage("§aSua dúvida foi respondida por§f: " + staffer.getName() + "§a.");
        player.sendMessage("");
        player.sendMessage("§aDúvida:");
        for (String string : item.getDoubt()) {
            player.sendMessage(string);
        }
        player.sendMessage("");
        player.sendMessage("§aResposta: §f" + answer);
        menuBuilder.removeItem(item);
        doubts.remove(player.getName());
        answering.remove(staffer.getName());
    }

    public void addStafferAnswering(Player staffer, Player player, String[] doubt, Item item) {
        staffer.closeInventory();
        menuBuilder.removeItem(item);
        if (staffAnswering(staffer)) {
            staffer.sendMessage("§cVocê já está respondendo uma dúvida.");
            return;
        }
        answering.put(staffer.getName(), item);
        staffer.sendMessage("");
        staffer.sendMessage("§aDúvida de: §f" + player.getName());
        for (String string : doubt) {
            staffer.sendMessage(string);
        }
        staffer.sendMessage("");
        staffer.sendMessage("§eDigite 'cancelar' para cancelar.");
    }

    public void cancelAnswering(Player staffer) {
        if (!staffAnswering(staffer)) return;
        menuBuilder.addItem(answering.get(staffer.getName()));
        answering.remove(staffer.getName());
        staffer.sendMessage("§cVocê não está mais respondendo uma dúvida.");
    }

    public void removePlayerDoubts(Player player) {
        doubts.remove(player.getName());
        menuBuilder.removeItem(doubts.get(player.getName()));
    }

    public boolean staffAnswering(Player staffer) {
        return answering.containsKey(staffer.getName());
    }

    public boolean playerDoubts(Player player) {
        return doubts.containsKey(player.getName());
    }
}
