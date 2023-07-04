package kng.ruben.luminous.objetcs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PermissionPlayer {

    private UUID uuid;
    private PermissionGroup permissionGroup;
    private List<String> playerPermissions;

    public PermissionPlayer(UUID uuid, PermissionGroup permissionGroup, List<String> playerPermissions) {
        this.uuid = uuid;
        this.permissionGroup = permissionGroup;
        this.playerPermissions = playerPermissions;
    }

    public PermissionPlayer(UUID uuid, PermissionGroup permissionGroup) {
        this.uuid = uuid;
        this.permissionGroup = permissionGroup;
        this.playerPermissions = new ArrayList<>();
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

    public List<String> getPlayerPermissions() {
        return playerPermissions;
    }

    public void setPlayerPermissions(List<String> playerPermissions) {
        this.playerPermissions = playerPermissions;
    }
}
