package kng.ruben.luminous.objetcs;

import java.util.List;

public class PermissionGroup {

    private String name;
    private List<String> permissions;

    public PermissionGroup(String name, List<String> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
