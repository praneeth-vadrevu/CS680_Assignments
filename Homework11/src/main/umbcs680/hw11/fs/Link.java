package umbcs680.hw11.fs;
import java.time.LocalDateTime;


public class Link extends FSElement {
    private FSElement target;

    public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
        super(parent, name, size, creationTime);
        this.target = target;
    }

    public FSElement getTarget() {
        return target;
    }

    @Override
    public boolean isDirectory() {
        return target.isDirectory();
    }

    @Override
    public boolean isLink() {
        return true;
    }

    @Override
    public int getSize() {
        return target.getSize();
    }
    @Override
    public void accept(FSVisitor visitor) {
        visitor.visit(this);
    }

}
