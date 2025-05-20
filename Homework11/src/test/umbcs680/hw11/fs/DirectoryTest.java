package umbcs680.hw11.fs;

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
        fs = TestFixtureInitializer.createFS();
        root = fs.getRootDirs().getFirst();
        hw01 = root.getSubDirectories().getFirst();
        src = hw01.getSubDirectories().getFirst();
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
            if (d.getName().equals("hw01")) {
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
            if (f.getName().equals("readme.md")) {
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
            if (l.getName().equals("linkToReadme")) {
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

    @Test
    public void testAscendingSizeComparator() {
        List<FSElement> sorted = root.getChildren(new AscendingSizeComparatorStrategy());
        for (int i = 1; i < sorted.size(); i++) {
            assertTrue(sorted.get(i - 1).getSize() <= sorted.get(i).getSize());
        }
    }

    @Test
    public void testDescendingSizeComparator() {
        List<FSElement> sorted = root.getChildren(new DescendingSizeComparatorStrategy());
        for (int i = 1; i < sorted.size(); i++) {
            assertTrue(sorted.get(i - 1).getSize() >= sorted.get(i).getSize());
        }
    }

    @Test
    public void testReverseAlphabeticalComparator() {
        List<FSElement> sorted = root.getChildren(new ReverseAlphabeticalComparatorStrategy());
        for (int i = 1; i < sorted.size(); i++) {
            assertTrue(sorted.get(i - 1).getName().compareToIgnoreCase(sorted.get(i).getName()) >= 0);
        }
    }

    @Test
    public void testCreationTimeComparator() {
        List<FSElement> sorted = root.getChildren(new CreationTimeComparatorStrategy());
        for (int i = 1; i < sorted.size(); i++) {
            assertTrue(!sorted.get(i - 1).getCreationTime().isAfter(sorted.get(i).getCreationTime()));
        }
    }
}

