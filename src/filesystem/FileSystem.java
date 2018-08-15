package filesystem;

import java.util.ArrayList;
import java.util.List;

public class FileSystem {
    private final Directory root;

    public FileSystem() {
        root = new Directory("", null);
        root.setParent(root);
    }

    public List<String> findPath(String name) {
        StringBuilder sb = new StringBuilder();
        List<String> res = new ArrayList<>();
        findPath(root, sb, res, name);
        return res;
    }

    private void findPath(Node cur, StringBuilder sb, List<String> res, String name) {
        int len = sb.length();
        if (cur.getName().equals(name)) {
            sb.append("/" + cur.getName());
            if (!cur.isFile()) sb.append("/");
            res.add(sb.toString());
            sb.setLength(len);
        }
        if (cur.isFile()) return;

        sb.append("/" + cur.getName());
        for (Node next : ((Directory) cur).getContent()) {
            findPath(next, sb, res, name);
        }
        sb.setLength(len);
    }

    public Directory cd(String path, Directory cur) {
        if (path == null) throw new IllegalArgumentException("Invalid path!");
        if (path.length() == 0) return root;
        String[] folders;
        if (path.charAt(0) == '/') {
            cur = root;
            path = path.substring(1);
        }
        folders = path.split("/");
        Directory res = cd(folders, cur, 0);
        return res == null ? cur: res;
    }

    private Directory cd(String[] path, Directory cur, int idx) {
        if (idx == path.length) return cur;
        for (Node next : cur.getContent()) {
            if (!next.isFile() && next.getName().equals(path[idx])) {
                return cd(path, cur, idx + 1);
            }
        }
        return null;
    }

    public Directory createDirectory(String name) {
        return root.createNewFolder(name);
    }

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        Directory cur = fs.createDirectory("Documents").createNewFolder("apps");
        cur.createNewFile("java");
        cur = cur.createNewFolder("apps").createNewFolder("apps").createNewFolder("apps");
        // cur = cur.createNewFolder("java");
        for (String path : fs.findPath("apps"))
            System.out.println(path);
        System.out.println(cur.curPath());
    }
}
