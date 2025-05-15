package umbcs680.hw14.fs;

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
        assertTrue(subDirs.stream().anyMatch(d -> d.getName().equals("hw01")));
    }

    @Test
    public void testGetFiles() {
        List<File> files = root.getFiles();
        assertTrue(files.stream().anyMatch(f -> f.getName().equals("readme.md")));
    }

    @Test
    public void testGetLinks() {
        List<Link> links = hw01.getLinks();
        assertTrue(links.stream().anyMatch(l -> l.getName().equals("linkToReadme")));
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
    public void testSortChildrenByAscendingSizeWithLambda() {
        List<FSElement> sorted = root.getChildren((e1, e2) -> Integer.compare(e1.getSize(), e2.getSize()));
        for (int i = 1; i < sorted.size(); i++) {
            assertTrue(sorted.get(i - 1).getSize() <= sorted.get(i).getSize());
        }
    }

    @Test
    public void testSortChildrenByDescendingSizeWithLambda() {
        List<FSElement> sorted = root.getChildren((e1, e2) -> Integer.compare(e2.getSize(), e1.getSize()));
        for (int i = 1; i < sorted.size(); i++) {
            assertTrue(sorted.get(i - 1).getSize() >= sorted.get(i).getSize());
        }
    }

    @Test
    public void testSortChildrenByReverseAlphabeticalWithLambda() {
        List<FSElement> sorted = root.getChildren((e1, e2) -> e2.getName().compareToIgnoreCase(e1.getName()));
        for (int i = 1; i < sorted.size(); i++) {
            assertTrue(sorted.get(i - 1).getName().compareToIgnoreCase(sorted.get(i).getName()) >= 0);
        }
    }

    @Test
    public void testSortChildrenByCreationTimeWithLambda() {
        List<FSElement> sorted = root.getChildren((e1, e2) -> e1.getCreationTime().compareTo(e2.getCreationTime()));
        for (int i = 1; i < sorted.size(); i++) {
            assertFalse(sorted.get(i - 1).getCreationTime().isAfter(sorted.get(i).getCreationTime()));
        }
    }
}
