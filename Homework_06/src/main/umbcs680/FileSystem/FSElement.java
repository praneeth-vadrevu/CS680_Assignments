package umbcs680.FileSystem;
import java.io.*;
import java.util.*;
import java.time.*;

public abstract class FSElement{
    protected String name;
    protected int size;
    protected LocalDateTime creationTime;
    protected Directory parent;

    public  FSElement(Directory parent,String name,int size, LocalDateTime creationTime){
        this.parent = parent;
        this.name = name;
        this.creationTime = creationTime;
        this.size = size;

    }

    public Directory getParent() {
        return this.parent;
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return this.size;
    }

    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void setSize(int size) {
//        this.size = size;
//    }
//
//    public void setCreationTime(LocalDateTime creationTime) {
//        this.creationTime = creationTime;
//    }

    //This is the methods that needs to be overriden by the children classes in order to know whether they are a directory or no.
    public abstract boolean isDirectory();
}

