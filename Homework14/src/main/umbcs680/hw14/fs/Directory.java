package umbcs680.hw14.fs;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class Directory extends FSElement {

    private LinkedList<FSElement> children = new LinkedList<>();

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

//      Appends child and re-sorts alphabetically by name (default sorting policy).
//     This keeps the internal children list alphabetically ordered by default.
    public void appendChild(FSElement child) {
        this.children.add(child);
        this.children.sort((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName())); // Reorder alphabetically
    }



    public List<FSElement> getChildren() {
        return this.children;
    }


//    Overloaded: Returns children sorted using a client-provided comparator (lambda).
    public List<FSElement> getChildren(Comparator<FSElement> comparator) {
        List<FSElement> sortedList = new LinkedList<>(this.children); // Copy to avoid mutating internal state
        sortedList.sort(comparator);
        return sortedList;
    }


    public List<Directory> getSubDirectories() {
        List<Directory> subDirs = new LinkedList<>();
        for (FSElement elem : this.children) {
            if (elem instanceof Directory) {
                subDirs.add((Directory) elem);
            }
        }
        return subDirs;
    }


    public List<Directory> getSubDirectories(Comparator<FSElement> comparator) {
        List<Directory> subDirs = new LinkedList<>();
        for (FSElement elem : this.children) {
            if (elem instanceof Directory) {
                subDirs.add((Directory) elem);
            }
        }
        subDirs.sort(comparator);
        return subDirs;
    }


    public List<File> getFiles() {
        List<File> files = new LinkedList<>();
        for (FSElement elem : this.children) {
            if (elem instanceof File) {
                files.add((File) elem);
            }
        }
        return files;
    }


//     Overloaded: Returns files sorted using a client-provided comparator (lambda).

    public List<File> getFiles(Comparator<FSElement> comparator) {
        List<File> files = new LinkedList<>();
        for (FSElement elem : this.children) {
            if (elem instanceof File) {
                files.add((File) elem);
            }
        }
        files.sort(comparator);
        return files;
    }


    public List<Link> getLinks() {
        List<Link> links = new LinkedList<>();
        for (FSElement elem : this.children) {
            if (elem instanceof Link) {
                links.add((Link) elem);
            }
        }
        return links;
    }


//      Overloaded: Returns links sorted using a client-provided comparator (lambda).

    public List<Link> getLinks(Comparator<FSElement> comparator) {
        List<Link> links = new LinkedList<>();
        for (FSElement elem : this.children) {
            if (elem instanceof Link) {
                links.add((Link) elem);
            }
        }
        links.sort(comparator);
        return links;
    }


//     Counts all immediate children.

    public int countChildren() {
        return this.children.size();
    }


//      Recursively calculates total size, excluding links.

    public int getTotalSize() {
        int totalSize = 0;
        for (FSElement elem : this.children) {
            if (elem instanceof Directory) {
                totalSize += ((Directory) elem).getTotalSize();
            } else if (!(elem instanceof Link)) {
                totalSize += elem.getSize();
            }
        }
        return totalSize;
    }

    @Override
    public void accept(FSVisitor visitor) {
        visitor.visit(this);
        for (FSElement child : children) {
            child.accept(visitor);
        }
    }

    @Override
    public boolean isLink() {
        return false;
    }
}
