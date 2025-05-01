import umbcs680.hw08.fs.*;

import java.util.*;

public class FileCrawlingVisitor implements FSVisitor {
    private List<File> files = new LinkedList<>();

    @Override
    public void visit(File file) {
        files.add(file); // Collect the file
    }

    @Override
    public void visit(Directory dir) {
        for (FSElement e : dir.getChildren()) {
            e.accept(this); // Recursively visit children
        }
    }

    @Override
    public void visit(Link link) {
        // Skip links
    }

    public List<File> getFiles() {
        return files;
    }
}



