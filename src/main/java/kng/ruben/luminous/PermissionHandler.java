package kng.ruben.luminous;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import kng.ruben.luminous.driver.MongoDBDriver;
import kng.ruben.luminous.objetcs.PermissionGroup;
import kng.ruben.luminous.objetcs.PermissionPlayer;
import org.bson.Document;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class PermissionHandler {

    private final MongoDBDriver driver;
    private static final String PLAYER_COLLECTION = "permissions_player";
    private static final PermissionGroup DEFAULT_GROUP = new PermissionGroup("default", new ArrayList<>());
    private final Map<UUID, PermissionAttachment> permissions = new HashMap<>();

    private final LoadingCache<UUID, PermissionPlayer> cache = CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).build(new CacheLoader<>() {
        @Override
        public PermissionPlayer load(UUID uuid) throws Exception {
            var result = loadPlayerDocument(uuid);
            if (result == null)
                return null;

            //TODO: load correct group

            return new PermissionPlayer(uuid, DEFAULT_GROUP, result.getList("playerPermissions", String.class));
        }
    });

    public PermissionHandler(MongoDBDriver driver) {
        this.driver = driver;
    }

    public boolean isInDatabase(UUID uuid) {
        return loadPlayerDocument(uuid) != null;
    }

    public void createPlayer(Player player) {
        var permissionPlayer = new PermissionPlayer(player.getUniqueId(), DEFAULT_GROUP);

        var document = new Document();

        document.append("_id", permissionPlayer.getUuid());
        document.append("group", permissionPlayer.getPermissionGroup().getName());
        document.append("playerPermissions", permissionPlayer.getPlayerPermissions());

        driver.insertDocument(PLAYER_COLLECTION, document);
    }

    private Document loadPlayerDocument(UUID uuid) {
        var query = new Document("_id", uuid);
        return driver.findDocument(PLAYER_COLLECTION, query);
    }

    public PermissionPlayer getPlayer(UUID uuid) {
        try {
            return cache.get(uuid);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    public void updatePermissions(Player player) {
        var attachment = player.addAttachment(Luminous.getInstance());
        permissions.put(player.getUniqueId(), attachment);

        for (String playerPermission : getPlayer(player.getUniqueId()).getPlayerPermissions())
            attachment.setPermission(playerPermission, true);
    }

}
