import java.time.*;
import java.util.*;


public class Directory extends FSElement {
    private LinkedList<FSElement> children = new LinkedList<>();

    //    Constructor function
    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    //function to add new children of the FSElement class to the List "children"
    public void appendChild(FSElement child) {
        children.add(child);
    }

    public LinkedList<FSElement> getChildren() {
        return this.children;
    }

    public int countChildren() {
        return this.children.size();
    }

    public LinkedList<Directory> getSubDirectories() {
        List<Directory> subDirs = new LinkedList<>();

        for (FSElement element : this.children) {
            if (element.isDirectory()) {
                subDirs.add((Directory) element);
//                here we are typecasting the element to directory from a generic FSElement
//                this way let java know that this is a diretory and the list subDirs only stores objects
//                of Directory type.

            }
        }
        return subDirs;
    }

    public LinkedList<File> getFiles() {
        List <File> files = new LinkedList<>();
        for (FSElement element : children) {
            if (!element.isDirectory()) {
                files.add((File) element);
            }
        }
        return files;
    }

    public int getTotalSize() {
        int totalSize = 0;
        for (FSElement element : children) {
            if (element.isDirectory()) {
                totalSize += ((Directory) element).getTotalSize();
//             so that we can account for the files inside the directories recursively.
            } else {
                totalSize += element.getSize();
            }
        }
        return totalSize;

    }
}