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
        this(name, parent, new int[]{7, 5, 4});
    }

    public List<Node> getContent() {
        return new ArrayList<>(content);
    }

    public Node createNewNode(Node child) {
        super.setModifiedTime();
        content.add(child);
        return child;
    }

    public Directory createNewFolder(String name) {
        super.setModifiedTime();
        Directory folder = new Directory(name, this);
        content.add(folder);
        return folder;
    }

    public File createNewFile(String name) {
        super.setModifiedTime();
        File file = new File(name, this);
        content.add(file);
        return file;
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
