package umbcs680.hw16.fs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class DirectoryTest {

    private static FileSystem fs;
    private static Directory root;
    private static Directory hw01;
    private static Directory src;

    @BeforeAll
    public static void setUp() {
        fs = FileSystem.getFileSystem();
        fs.clearRootDirs();
        fs = TestFixtureInitializer.createFS();
        root = fs.getRootDirs().getFirst();
        hw01 = root.getSubDirectories().getFirst();
        src = hw01.getSubDirectories().getFirst();
    }

    private String[] dirToStringArray(Directory d) {
        return new String[]{ d.getName(), String.valueOf(d.getSize()), d.getCreationTime().toString() };
    }

    @Test
    public void testDirectoryConstructor() {
        LocalDateTime now = LocalDateTime.now();
        Directory testDir = new Directory(null, "testDir", 0, now);
        assertEquals("testDir", testDir.getName());
        assertEquals(0, testDir.getSize());
        assertEquals(now, testDir.getCreationTime());
        assertNull(testDir.getParent());
    }

    @Test
    public void testGetName() {
        assertEquals("umbcs680", root.getName());
    }

    @Test
    public void testGetSize() {
        assertEquals(0, root.getSize());
    }

    @Test
    public void testGetCreationTime() {
        assertNotNull(root.getCreationTime());
    }

    @Test
    public void testGetParent() {
        assertNull(root.getParent());
        assertEquals(root, hw01.getParent());
    }

    @Test
    public void testIsDirectory() {
        assertTrue(root.isDirectory());
    }

    @Test
    public void testIsLink() {
        assertFalse(root.isLink());
    }

    @Test
    public void testAppendChildAndGetChildren() {
        Directory newDir = new Directory(root, "newDir", 0, LocalDateTime.now());
        int beforeCount = root.countChildren();
        root.appendChild(newDir);
        int afterCount = root.countChildren();

        assertEquals(beforeCount + 1, afterCount);
        assertTrue(root.getChildren().contains(newDir));
    }

    @Test
    public void testGetSubDirectories() {
        List<Directory> subDirs = root.getSubDirectories();
        boolean found = false;
        for (Directory d : subDirs) {
            if ("hw01".equals(d.getName())) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testGetFiles() {
        List<File> files = root.getFiles();
        boolean found = false;
        for (File f : files) {
            if ("readme.md".equals(f.getName())) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testGetLinks() {
        List<Link> links = hw01.getLinks();
        boolean found = false;
        for (Link l : links) {
            if ("linkToReadme".equals(l.getName())) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testCountChildren() {
        int expected = root.getChildren().size();
        assertEquals(expected, root.countChildren());
    }

    @Test
    public void testGetTotalSize() {
        assertEquals(425, root.getTotalSize());
    }
}
