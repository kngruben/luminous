package kng.ruben.luminous;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import kng.ruben.luminous.driver.MongoDBDriver;
import kng.ruben.luminous.objetcs.PermissionPlayer;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PermissionHandler {

    private final MongoDBDriver driver;
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

    public PermissionPlayer getPlayer(UUID uuid) {
        return null;
    }

}
