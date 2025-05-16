package umbcs680.hw16.util;

import umbcs680.hw16.fs.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CountingVisitorTest {

    @Test
    public void testInitialFileCountIsZero() {
        CountingVisitor visitor = new CountingVisitor();
        assertEquals(0, visitor.getFileCount());
    }

    @Test
    public void testInitialDirectoryCountIsZero() {
        CountingVisitor visitor = new CountingVisitor();
        assertEquals(0, visitor.getDirectoryCount());
    }

    @Test
    public void testFileCountAfterVisitingFiles() {
        CountingVisitor visitor = new CountingVisitor();
        File file1 = new File(null, "file1.txt", 10, java.time.LocalDateTime.now());
        File file2 = new File(null, "file2.txt", 20, java.time.LocalDateTime.now());

        visitor.visit(file1);
        visitor.visit(file2);

        assertEquals(2, visitor.getFileCount());
    }

    @Test
    public void testDirectoryCountAfterVisitingDirectories() {
        CountingVisitor visitor = new CountingVisitor();
        Directory dir1 = new Directory(null, "dir1", 0, java.time.LocalDateTime.now());
        Directory dir2 = new Directory(null, "dir2", 0, java.time.LocalDateTime.now());

        visitor.visit(dir1);
        visitor.visit(dir2);

        assertEquals(2, visitor.getDirectoryCount());
    }

    @Test
    public void testVisitLinkDoesNotAffectCounts() {
        CountingVisitor visitor = new CountingVisitor();
        File target = new File(null, "target.txt", 10, java.time.LocalDateTime.now());
        Link link = new Link(null, "link", 0, java.time.LocalDateTime.now(), target);

        visitor.visit(link);

        assertEquals(0, visitor.getFileCount());
        assertEquals(0, visitor.getDirectoryCount());
    }
}
