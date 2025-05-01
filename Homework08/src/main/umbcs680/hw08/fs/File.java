package umbcs680.hw08.fs;

import java.time.LocalDateTime;


public class File extends FSElement {

    public File(String name, int size, LocalDateTime creationTime) {
        super(name, size, creationTime);
    }

    @Override
    public void accept(FSVisitor visitor) {
        visitor.visit(this); // Passes itself to the visitor
    }
}
