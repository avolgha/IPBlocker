package dev.marius.ipblocker.spigot;

import dev.marius.ipblocker.common.Messages;
import org.bukkit.plugin.java.JavaPlugin;

public final class Spigot extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new LoginListener(), this);
        getServer().getConsoleSender().sendMessage(Messages.START.data);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(Messages.STOP.data);
    }

}
