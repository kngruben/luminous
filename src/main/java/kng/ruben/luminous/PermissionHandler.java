package kng.ruben.luminous;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import kng.ruben.luminous.driver.MongoDBDriver;
import kng.ruben.luminous.objetcs.PermissionGroup;
import kng.ruben.luminous.objetcs.PermissionPlayer;
import org.bson.Document;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PermissionHandler {

    private final MongoDBDriver driver;
    private static final String PLAYER_COLLECTION = "permissions_player";
    private static final PermissionGroup DEFAULT_GROUP = new PermissionGroup("default", new ArrayList<>());

    private final LoadingCache<UUID, PermissionPlayer> cache =
            CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).build(new CacheLoader<>() {
                @Override
                public PermissionPlayer load(UUID uuid) throws Exception {
                    return null;
                }
            });

    public PermissionHandler(MongoDBDriver driver) {
        this.driver = driver;
    }
    public void createPlayer(Player player) {
        var permissionPlayer = new PermissionPlayer(player.getUniqueId(), DEFAULT_GROUP);

        var document = new Document();

        document.append("uuid", permissionPlayer.getUuid());
        document.append("group", permissionPlayer.getPermissionGroup().getName());
        document.append("playerPermissions", permissionPlayer.getPlayerPermissions());

        driver.insertDocument(PLAYER_COLLECTION, document);
    }

    public PermissionPlayer getPlayer(UUID uuid) {
        return null;
    }

    public void updatePermissions(Player player) {

    }

}
