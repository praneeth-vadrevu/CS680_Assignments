package umbcs680.hw11.fs;

// Visitor Interface – declares visit methods for each concrete element type
public interface FSVisitor {
    void visit(File file);
    void visit(Directory directory);
    void visit(Link link);
}
