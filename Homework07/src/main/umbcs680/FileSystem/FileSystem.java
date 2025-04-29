package umbcs680.FileSystem;
import java.util.*;


public class FileSystem{

    private static FileSystem instance = null;

    private LinkedList <Directory> rootDirs = new LinkedList<>();

    private FileSystem(){} //private constructor to avoid instantitation from outside.

    public FileSystem getFileSystem(){
        if (instance == null){
            instance = new FileSystem();
        }
        return instance;
    }
    public LinkedList<Directory> getRootDirs() {
        return rootDirs;
    }
    public void appendRootDir(Directory root){
        rootDirs.add(root);
    }
}
