package umbcs680.hw08.fs;

import java.util.LinkedList;

public class FileSystem {
    private static FileSystem instance = null;
    private LinkedList<Directory> rootDirs = new LinkedList<>();

    // Singleton pattern to ensure only one FileSystem exists
    private FileSystem() {}

    public static FileSystem getFileSystem() {
        if (instance == null) {
            instance = new FileSystem();
        }
        return instance;
    }

    public void appendRootDir(Directory root) {
        this.rootDirs.add(root);
    }

    public LinkedList<Directory> getRootDirs() {
        return this.rootDirs;
    }

    // Accept a visitor and apply it to all root directories
    public void accept(FSVisitor visitor) {
        for (Directory root : rootDirs) {
            root.accept(visitor);  // This triggers the recursive traversal
        }
    }
}
