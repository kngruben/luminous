package kng.ruben.luminous.objetcs;

import java.util.UUID;

public class PermissionPlayer {

    private UUID uuid;
    private PermissionGroup permissionGroup;

    public PermissionPlayer(UUID uuid, PermissionGroup permissionGroup) {
        this.uuid = uuid;
        this.permissionGroup = permissionGroup;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public PermissionGroup getPermissionGroup() {
        return permissionGroup;
    }

    public void setPermissionGroup(PermissionGroup permissionGroup) {
        this.permissionGroup = permissionGroup;
    }
}
