package umbcs680.hw16.fs;

import java.time.LocalDateTime;
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

    public List<Directory> getSubDirectories() {
        List<Directory> subDirs = new LinkedList<>();
        this.children.forEach(elem -> {
            if (elem instanceof Directory) {
                subDirs.add((Directory) elem);
            }
        });
        return subDirs;
    }

    public List<File> getFiles() {
        List<File> files = new LinkedList<>();
        this.children.forEach(elem -> {
            if (elem instanceof File) {
                files.add((File) elem);
            }
        });
        return files;
    }

    public List<Link> getLinks() {
        List<Link> links = new LinkedList<>();
        this.children.forEach(elem -> {
            if (elem instanceof Link) {
                links.add((Link) elem);
            }
        });
        return links;
    }

    public int countChildren() {
        return this.children.size();
    }

    public int getTotalSize() {
        final int[] totalSize = {0};
        this.children.forEach(elem -> {
            if (elem instanceof Directory) {
                totalSize[0] += ((Directory) elem).getTotalSize();
            } else if (!(elem instanceof Link)) {
                totalSize[0] += elem.getSize();
            }
        });
        return totalSize[0];
    }

    @Override
    public void accept(FSVisitor visitor) {
        visitor.visit(this);
        this.children.forEach(child -> child.accept(visitor));
    }

    @Override
    public boolean isLink() {
        return false;
    }
}
