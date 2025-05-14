package umbcs680.hw08.fs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class FileSystemTest {

    private static FileSystem fs;

    @BeforeAll
    public static void setUp() {
        fs = FileSystem.getFileSystem();
        fs.clearRootDirs();
        fs.getRootDirs().clear();  // Clear any previous state
        TestFixtureInitializer.createFS();
    }

    @Test
    public void testSingletonInstance() {
        FileSystem anotherInstance = FileSystem.getFileSystem();
        assertSame(fs, anotherInstance, "FileSystem should be singleton");
    }

    @Test
    public void testGetRootDirs() {
        LinkedList<Directory> roots = fs.getRootDirs();
        assertFalse(roots.isEmpty());
        assertEquals("umbcs680", roots.getFirst().getName());
    }

    @Test
    public void testAppendRootDir() {
        Directory newRoot = new Directory(null, "extraRoot", 0, LocalDateTime.now());
        int beforeCount = fs.getRootDirs().size();
        fs.appendRootDir(newRoot);
        int afterCount = fs.getRootDirs().size();

        assertEquals(beforeCount + 1, afterCount);
        assertTrue(fs.getRootDirs().contains(newRoot));
        assertEquals("extraRoot", fs.getRootDirs().getLast().getName());
    }
}
