import umbcs680.hw08.fs.*;

public class CountingVisitor implements FSVisitor {
    private int fileCount = 0;
    private int dirCount = 0;

    @Override
    public void visit(File file) {
        fileCount++;
    }

    @Override
    public void visit(Directory dir) {
        dirCount++;
        for (FSElement e : dir.getChildren()) {
            e.accept(this); // recursively visit children
        }
    }

    @Override
    public void visit(Link link) {
        // We do not count links as files or directories
    }

    public int getFileCount() {
        return fileCount;
    }

    public int getDirectoryCount() {
        return dirCount;
    }
}
