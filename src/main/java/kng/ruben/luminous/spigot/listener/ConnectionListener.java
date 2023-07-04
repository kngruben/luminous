package kng.ruben.luminous.spigot.listener;

import kng.ruben.luminous.Luminous;
import kng.ruben.luminous.PermissionHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {

    private static final PermissionHandler PERMISSION_HANDLER = Luminous.getInstance().getPermissionHandler();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        var player = event.getPlayer();
        var uuid = player.getUniqueId();

        if (!PERMISSION_HANDLER.isInDatabase(uuid))
            PERMISSION_HANDLER.createPlayer(player);

        PERMISSION_HANDLER.updatePermissions(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

    }

}
