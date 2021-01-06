package dev.marius.ipblocker.bungee;

import dev.marius.ipblocker.common.Messages;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;

public final class BungeeCord extends Plugin {

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerListener(this, new ConnectListener());
        getProxy().getConsole().sendMessage(new TextComponent(Messages.START.data));
    }

    @Override
    public void onDisable() {
        getProxy().getConsole().sendMessage(new TextComponent(Messages.STOP.data));
    }
    
}
