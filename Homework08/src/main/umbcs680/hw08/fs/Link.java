package umbcs680.hw08.fs;

import java.time.LocalDateTime;

// Link class represents a reference to another FSElement
public class Link extends FSElement {
    private FSElement target; // The element this link points to

    public Link(String name, int size, LocalDateTime creationTime, FSElement target) {
        super(name, size, creationTime);
        this.target = target;
    }

    public FSElement getTarget() {
        return target;
    }

    @Override
    public void accept(FSVisitor visitor) {
        visitor.visit(this);
    }
}
