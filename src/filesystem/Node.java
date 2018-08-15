package filesystem;

import java.util.Calendar;
import java.util.Date;

public abstract class Node {
    protected Node parent;
    protected final int[] permissions;
    protected final boolean file;
    protected String name;
    protected final Date createTime;
    protected Date modifiedTime;
    protected static final int[] DEFAULT_PERMISSION = new int[]{7, 5, 4};

    // unused
    protected Node(String name, Node parent, int[] permissions, boolean file) {
        this.name = name;
        this.parent = parent;
        this.permissions = permissions.clone();
        this.file = file;
        createTime = Calendar.getInstance().getTime();
        modifiedTime = createTime;
    }

    public abstract long getSize();
    public abstract boolean isFile();
    protected abstract int getFileCount();

    public Node getParent() {
        return parent;
    }

    public void setParent(Directory parent) {
        setModifiedTime();
        this.parent = parent;
    }

    public int[] getPermissions() {
        return permissions.clone();
    }

    public void chmod(int user, int group, int global) {
        setModifiedTime();
        permissions[0] = user;
        permissions[1] = group;
        permissions[2] = global;
    }

    public String getName() {
        return name;
    }

    public void reName(String name) {
        setModifiedTime();
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    protected void setModifiedTime() {
        modifiedTime = Calendar.getInstance().getTime();
    }

    public String curPath() {
        StringBuilder path = new StringBuilder();
        path.append("/");
        curPath(this, path);
        return path.toString();
    }

    private void curPath(Node cur, StringBuilder path) {
        if (cur == null || cur.getParent() == null) throw new IllegalArgumentException("Invalid folder");
        if (cur.getParent() != cur) {
            curPath(cur.getParent(), path);
        }
        path.append(cur.getName());
        if (!cur.isFile()) path.append("/");
    }
}
