import umbcs680.hw08.fs.*;

import java.util.LinkedList;

public class FileSearchVisitor implements FSVisitor {

    private String fileName;
    private LinkedList<File> foundFiles = new LinkedList<>();

    // Constructor takes the target file name to search for
    public FileSearchVisitor(String fileName) {
        this.fileName = fileName;
    }

    // Visit a File: if it matches the name, add to foundFiles
    public void visit(File file) {
        if (file.getName().equals(fileName)) {
            foundFiles.add(file);
        }
    }

    // Visit a Directory: recursively visit all its children
    public void visit(Directory dir) {
        for (FSElement child : dir.getChildren()) {
            child.accept(this);  // Dispatch to the correct visit method
        }
    }

    // Visit a Link: follow the target
    public void visit(Link link) {
        link.getTarget().accept(this);  // Follow link to actual target
    }

    // Return the list of matched files
    public LinkedList<File> getFoundFiles() {
        return foundFiles;
    }
}
