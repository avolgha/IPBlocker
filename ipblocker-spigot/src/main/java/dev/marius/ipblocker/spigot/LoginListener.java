package dev.marius.ipblocker.spigot;

import dev.marius.ipblocker.common.IPBlocker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onConnect(PlayerLoginEvent event) {
        try {
            if (IPBlocker.canNotJoin(event.getPlayer().getAddress(), event.getPlayer().getClass())) {
                event.getPlayer().kickPlayer("§4There are more than two player with the same ip online!");
            }
        } catch (Exception e) {
            event.getPlayer().kickPlayer("§4Error while trying to login: \n\n§7" + e.getMessage());
        }
    }

}