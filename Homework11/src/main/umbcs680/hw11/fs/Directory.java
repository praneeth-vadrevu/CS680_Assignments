package umbcs680.hw11.fs;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Directory extends FSElement {

    private LinkedList<FSElement> children = new LinkedList<>();

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    public void appendChild(FSElement child) {
        this.children.add(child);

    }

    public List<FSElement> getChildren() {
        return this.children;
    }


    public List<FSElement> getChildren(Comparator<FSElement> comparator){
        List<FSElement> sortedList = getChildren();
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


    public List<Directory> getSubDirectories(Comparator<FSElement> comparator){
        List<Directory> sortedList = getSubDirectories();
        sortedList.sort(comparator);
        return sortedList;
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


    public List<File> getFiles(Comparator<FSElement> comparator){
        List<File> sortedList = getFiles();
        sortedList.sort(comparator);
        return sortedList;
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

    public List<Link> getLinks(Comparator<FSElement> comparator){
        List<Link> sortedList = getLinks();
        sortedList.sort(comparator);
        return sortedList;
    }

    public int countChildren() {
        return this.children.size();
    }

    public int getTotalSize() {
        int totalSize = 0;
        for (FSElement elem : this.children) {
            if (elem instanceof Directory) {
                totalSize += ((Directory) elem).getTotalSize(); // recurse
            } else if (!(elem instanceof Link)) {
                totalSize += elem.getSize();
            }
        }
        return totalSize;
    }

    @Override
    public void accept(FSVisitor visitor) {
        visitor.visit(this);  // Visit this directory first
        for (FSElement child : children) {
            child.accept(visitor);  // Recursively visit children
        }
    }


    @Override
    public boolean isLink() {
        return false;
    }
}


