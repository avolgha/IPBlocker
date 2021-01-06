# IPBlocker
### Spigot and BungeeCord Plugin for blocking multiple Accounts with the same IP Address

## How to use
1. Download `.jar` file from releases
2. Put `.jar` file into `plugins/` folder of your Bukkit/Spigot/Paper/BungeeCord Server
3. Restart Server

## How the plugins works
If a player tries to join the server, the plugin looks, if there are more accounts online with the same IP Address. If there are more than 2, the plugins kicks the player and tell the online players with the same IP that were wanted to come a player online with their IP Address.

## For Developer
if you want to add the plugin to your system, to check whether more players with the same ip address are online, simply call this as example in your login event: (example made in spigot, can also be called in bungeecord)

````java
import dev.marius.ipblocker.common.IPBlocker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginListener implements Listener {

    // Call Listener on Player Login
    @EventHandler
    public void onConnect(PlayerLoginEvent event) {
        // Try-Catch for fetching other player classes
        try {
            // Check if the player can NOT join
            if (IPBlocker.canNotJoin(
                    event.getPlayer().getAddress(), // Get IP Address as InetSocketAddress
                    event.getPlayer().getClass()    // Get Player / ProxiedPlayer class
            )) {
                // Run if there are mon than two players online
                event.getPlayer().kickPlayer("some message");
            }
        } catch (Exception e) {
            // Error handling
            e.printStackTrace();
        }
    }

}
````

So call: 
`````
IPBlocker.canNotJoin(InetSocketAddress, Class<?>);
--> Call only with Address and player class

IPBlocker.canNotJoin(InetSocketAddress, Class<?>, Integer);
--> Call with Address, player class, and max amount of players with same ip address

IPBlocker.canNotJoin(InetSocketAddress, Class<?>, Boolean);
--> Call with Address, player class, and send if boolean is true a message to 
the online players with the same ip

IPBlocker.canNotJoin(InetSocketAddress, Class<?>, Integer, Boolean);
--> Call with Address, player class, max amount of players with same ip address and send 
if boolean is true a message to the other online players with the same ip
`````

## Copy
You are free to copy the `dev.marius.ipblocker.common.IPBlocker` File. But it would be nice if you would mention me in your project and leave the comments in the class

## Get support
For support, please write me on Discord `Marius#0686`