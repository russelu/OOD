package filesystem;

import java.util.ArrayList;
import java.util.List;

public class Directory extends Node {
    private List<Node> content;

    public Directory(String name, Node parent, int[] permissions) {
        super(name, parent, permissions, false);
        content = new ArrayList<>();
    }

    public Directory(String name, Node parent) {
        this(name, parent, DEFAULT_PERMISSION);
    }

    public List<Node> getContent() {
        return new ArrayList<>(content);
    }

    public Node addNode(Node child) {
        setModifiedTime();
        content.add(child);
        return child;
    }

    public boolean deleteNode(Node node) {
        for (Node next : content) {
            if (next == node) {
                return content.remove(node);
            }
            if (!next.isFile() && ((Directory) next).deleteNode(node))
                return true;
        }
        return false;
    }

    public boolean deleteCurNode() {
        if (parent == this) throw new IllegalArgumentException("Not able to delete root");
        return ((Directory) parent).getContent().remove(this);
    }

    public Directory createNewFolder(String name) {
        setModifiedTime();
        Directory folder = new Directory(name, this);
        content.add(folder);
        return folder;
    }

    public File createNewFile(String name) {
        setModifiedTime();
        File file = new File(name, this);
        content.add(file);
        return file;
    }

    protected boolean contains(String name) {
        for (Node child : content) {
            if (child.getName().equals(name))
                return true;
        }
        return false;
    }

    protected Node getChild(String name) {
        for (Node child : content) {
            if (child.getName().equals(name))
                return child;
        }
        return null;
    }

    protected Directory getChildFolder(String name) {
        for (Node child : content) {
            if (child.isFile() && child.getName().equals(name))
                return (Directory) child;
        }
        return null;
    }

    protected int getFileCount() {
        int cnt = 0;
        for (Node n : content) {
            cnt += n.getFileCount();
        }
        return cnt;
    }

    public long getSize() {
        long size = 0L;
        for (Node n : content) {
            size += n.getSize();
        }
        return size;
    }

    public boolean isFile() {return false;}
}
