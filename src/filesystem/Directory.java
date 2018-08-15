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

    public int getFileCount() {
        int cnt = 0;
        for (Node n : content) {
            cnt += n.isFile() ? 1: ((Directory) n).getFileCount();
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
