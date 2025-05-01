package umbcs680.hw08.fs;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

// Directory class representing a composite node (can contain children)
public class Directory extends FSElement {
    private LinkedList<FSElement> children = new LinkedList<>();

    public Directory(String name, int size, LocalDateTime creationTime) {
        super(name, size, creationTime);
    }

    public void addChild(FSElement child) {
        children.add(child);
    }

    public List<FSElement> getChildren() {
        return children;
    }

    @Override
    public void accept(FSVisitor visitor) {
        visitor.visit(this); // First visit the directory itself
        for (FSElement child : children) {
            child.accept(visitor); // Then visit each child which leads to recursive calling of the accept()
        }
    }
}
