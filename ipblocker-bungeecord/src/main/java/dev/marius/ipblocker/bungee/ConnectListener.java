package dev.marius.ipblocker.bungee;

import dev.marius.ipblocker.common.IPBlocker;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class ConnectListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onConnect(PostLoginEvent event) {
        try {
            if (IPBlocker.canNotJoin(event.getPlayer().getPendingConnection().getAddress(), event.getPlayer().getClass())) {
                event.getPlayer().disconnect(new TextComponent("§4There are more than two player with the same ip online!"));
            }
        } catch (Exception e) {
            event.getPlayer().disconnect(new TextComponent("§4Error while trying to login: \n\n§7" + e.getMessage()));
        }
    }

}
