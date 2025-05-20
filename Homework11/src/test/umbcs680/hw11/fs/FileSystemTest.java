package umbcs680.hw11.fs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class FileSystemTest {

    private static FileSystem fs;

    @BeforeAll
    public static void setUp() {
        fs = TestFixtureInitializer.createFS();
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

    @Test
    public void testAlphabeticalComparatorOnRootDirs() {
        List<FSElement> roots = new LinkedList<>(fs.getRootDirs());
        roots.sort(new AlphabeticalNameComparatorStrategy());
        for (int i = 1; i < roots.size(); i++) {
            assertTrue(roots.get(i - 1).getName().compareToIgnoreCase(roots.get(i).getName()) <= 0);
        }
    }

    @Test
    public void testReverseAlphabeticalComparatorOnRootDirs() {
        List<FSElement> roots = new LinkedList<>(fs.getRootDirs());
        roots.sort(new ReverseAlphabeticalComparatorStrategy());
        for (int i = 1; i < roots.size(); i++) {
            assertTrue(roots.get(i - 1).getName().compareToIgnoreCase(roots.get(i).getName()) >= 0);
        }
    }

    @Test
    public void testCreationTimeComparatorOnRootDirs() {
        List<FSElement> roots = new LinkedList<>(fs.getRootDirs());
        roots.sort(new CreationTimeComparatorStrategy());
        for (int i = 1; i < roots.size(); i++) {
            assertFalse(roots.get(i - 1).getCreationTime().isAfter(roots.get(i).getCreationTime()));
        }
    }
}
