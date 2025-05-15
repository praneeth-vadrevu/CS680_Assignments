package umbcs680.hw14.util;

import umbcs680.hw14.fs.*;
import umbcs680.hw14.util.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileSearchVisitorTest {

    private static FileSystem fs;

    @BeforeAll
    public static void setUp() {
        fs = FileSystem.getFileSystem();
        fs.clearRootDirs(); // Reset file system state
        TestFixtureInitializer.createFS();
    }

    @Test
    public void testSearchExistingFile() {
        // Search for "readme.md"
        FileSearchVisitor visitor = new FileSearchVisitor("readme.md");
        fs.accept(visitor);

        List<File> foundFiles = visitor.getFoundFiles();
        assertEquals(1, foundFiles.size(), "Should find exactly one file named readme.md");
        assertEquals("readme.md", foundFiles.get(0).getName());
    }

    @Test
    public void testSearchNonExistingFile() {
        // Search for a file that doesn't exist
        FileSearchVisitor visitor = new FileSearchVisitor("nonexistent.txt");
        fs.accept(visitor);

        List<File> foundFiles = visitor.getFoundFiles();
        assertTrue(foundFiles.isEmpty(), "Should not find any file named nonexistent.txt");
    }

    @Test
    public void testSearchViaLink() {
        // The link rm.md points to readme.md, so searching for readme.md should still return it
        FileSearchVisitor visitor = new FileSearchVisitor("readme.md");
        fs.accept(visitor);

        List<File> foundFiles = visitor.getFoundFiles();
        assertEquals(1, foundFiles.size(), "Should find readme.md through link traversal");
        assertEquals("readme.md", foundFiles.get(0).getName());
    }
}
