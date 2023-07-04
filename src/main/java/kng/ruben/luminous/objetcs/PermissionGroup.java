package kng.ruben.luminous.objetcs;

import java.util.List;

public class PermissionGroup {

    private String name;
    private List<String> permissions;
    private int id;

    public PermissionGroup(String name, List<String> permissions, int id) {
        this.name = name;
        this.permissions = permissions;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
