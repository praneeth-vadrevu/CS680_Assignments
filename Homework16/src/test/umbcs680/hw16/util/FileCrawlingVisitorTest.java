package umbcs680.hw16.util;

import umbcs680.hw16.fs.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class FileCrawlingVisitorTest {

    @Test
    public void testInitialFileListIsEmpty() {
        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        List<File> files = visitor.getFiles();
        assertTrue(files.isEmpty(), "Initial file list should be empty");
    }

    @Test
    public void testVisitFileAddsFileToList() {
        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        File file = new File(null, "testFile.txt", 100, java.time.LocalDateTime.now());

        visitor.visit(file);

        List<File> files = visitor.getFiles();
        assertEquals(1, files.size(), "File list should contain one file after visit");
        assertSame(file, files.get(0), "Visited file should be the one in the list");
    }

    @Test
    public void testVisitDirectoryDoesNotAffectFileList() {
        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        Directory directory = new Directory(null, "testDir", 0, java.time.LocalDateTime.now());

        visitor.visit(directory);

        List<File> files = visitor.getFiles();
        assertTrue(files.isEmpty(), "Visiting a directory should not affect the file list");
    }

    @Test
    public void testVisitLinkDoesNotAffectFileList() {
        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        File target = new File(null, "targetFile.txt", 50, java.time.LocalDateTime.now());
        Link link = new Link(null, "link", 0, java.time.LocalDateTime.now(), target);

        visitor.visit(link);

        List<File> files = visitor.getFiles();
        assertTrue(files.isEmpty(), "Visiting a link should not affect the file list");
    }
}
