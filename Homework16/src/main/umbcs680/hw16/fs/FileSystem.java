package umbcs680.hw16.fs;

import java.util.LinkedList;

public class FileSystem {

    private static FileSystem instance = null;
    private LinkedList<Directory> rootDirs = new LinkedList<>();

    private FileSystem() {}

    public static FileSystem getFileSystem() {
        if (instance == null) {
            instance = new FileSystem();
        }
        return instance;
    }

    public LinkedList<Directory> getRootDirs() {
        return rootDirs;
    }

    public void appendRootDir(Directory root) {
        rootDirs.add(root);
    }

    public void clearRootDirs() {
        rootDirs.clear();
    }

    public void accept(FSVisitor visitor) {
        rootDirs.forEach(root -> root.accept(visitor));
    }
}
