package umbcs680.hw14.fs;

import java.time.LocalDateTime;

public abstract class FSElement {
    protected Directory parent;
    protected String name;
    protected int size;
    protected LocalDateTime creationTime;

    public FSElement(Directory parent,String name, int size, LocalDateTime creationTime) {
        this.parent = parent;
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
    }

    public Directory getParent(){
        return this.parent;
    }
    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public abstract boolean isDirectory();

    public abstract void accept(FSVisitor visitor);

    public abstract boolean isLink();
}

