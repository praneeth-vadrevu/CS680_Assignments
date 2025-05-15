package umbcs680.hw16.util;

import umbcs680.hw16.fs.*;
import java.util.ArrayList;
import java.util.List;

public class FileSearchVisitor implements FSVisitor {

    private String fileName;
    private List<File> foundFiles = new ArrayList<>();

    public FileSearchVisitor(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void visit(Link link) {
        FSElement target = link.getTarget();
        if (target instanceof File file && file.getName().equals(fileName)) {
            addIfNotAlreadyFound(file);
        }
    }

    @Override
    public void visit(Directory dir) {
        // No action needed for directories
    }

    @Override
    public void visit(File file) {
        if (file.getName().equals(fileName)) {
            addIfNotAlreadyFound(file);
        }
    }

    private void addIfNotAlreadyFound(File file) {

        boolean alreadyFound = foundFiles.stream().anyMatch(existingFile -> existingFile == file);
        if (!alreadyFound) {
            foundFiles.add(file);
        }
    }

    public List<File> getFoundFiles() {
        return foundFiles;
    }
}
