import java.io.*;
import java.util.*;
import java.time.*;

public abstract class FSElement{
    protected String name;
    protected int size;
    protected LocalDateTime creationTime;
    protected Directory parent;

    public void FSElement(Directory parent,String name,int size, LocalDateTime creationTime){
        this.parent = parent;
        this.name = name;
        this.creationTime = creationTime;

    }

    public Directory getParent() {
        return parent;
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

    //This is the methods that needs to be overriden by the children classes in order to know whether they are a directory or no.
    public abstract boolean isDirectory();
}

}