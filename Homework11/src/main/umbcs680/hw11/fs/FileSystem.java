package umbcs680.hw11.fs;

import java.util.LinkedList;

public class FileSystem {

    private static FileSystem instance = null;
    private LinkedList<Directory> rootDirs = new LinkedList<>();

    // Private constructor for Singleton Pattern
    private FileSystem() {}

    // Singleton accessor
    public static FileSystem getFileSystem() {
        if (instance == null) {
            instance = new FileSystem();
        }
        return instance;
    }

    // Return root directories
    public LinkedList<Directory> getRootDirs() {
        return rootDirs;
    }

    // Add a root directory
    public void appendRootDir(Directory root) {
        rootDirs.add(root);
    }

    // Clear all root directories - Added for clean state management in tests
    public void clearRootDirs() {
        rootDirs.clear();
    }


    public void accept(FSVisitor visitor) {
        for (Directory root : rootDirs) {
            root.accept(visitor);  // Propagate the visitor to each root directory
        }
    }

}
