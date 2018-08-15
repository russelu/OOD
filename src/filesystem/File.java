package filesystem;

public class File extends Node {
    private byte[] content;
    private static final int BYTE_SIZE = 8;

    public File(String name, Node parent, int[] permissions, byte[] content) {
        super(name, parent, permissions, true);
        this.content = content;
    }

    public File(String name, Node parent, byte[] content) {
        this(name, parent, new int[]{7, 5, 4}, content);
    }

    public File(String name, Node parent) {
        this(name, parent, new int[]{7, 5, 4}, new byte[]{});
    }

    public long getSize() {
        return BYTE_SIZE * content.length;
    }

    public boolean isFile() {return true;}
}
