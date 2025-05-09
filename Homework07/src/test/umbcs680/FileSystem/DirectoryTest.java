package umbcs680.FileSystem;

import static org.junit.jupiter.api.Assertions.*;
        import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;

public class DirectoryTest {

    // Static fields to share across all tests
    private static FileSystem fs;
    private static Directory umbcs680;
    private static Directory hw01;
    private static Directory src;

    // This method is run once before all test cases
    @BeforeAll
    public static void setUp() {
        fs = TestFixtureInitializer.createFS();     // Builds and returns the file system
        umbcs680 = fs.getRootDirs().get(0);         // The root directory
        hw01 = umbcs680.getSubDirectories().get(0); // Subdirectory of root
        src = hw01.getSubDirectories().get(0);      // Subdirectory of hw01
    }

    // Helper to convert a Directory into a string array for equality checks
    private String[] dirToStringArray(Directory d) {
        return new String[] {
                d.getName(),
                String.valueOf(d.getSize()),
                d.getCreationTime().toString()
        };
    }

    @Test
    public void directoryConstructorTest() {
        LocalDateTime now = LocalDateTime.now();
        Directory testDir = new Directory(null, "testDir", 0, now);
        assertEquals("testDir", testDir.getName());
        assertEquals(0, testDir.getSize());
        assertEquals(now, testDir.getCreationTime());
        assertNull(testDir.getParent());
    }

    @Test
    public void verifyDirectoryEqualityUmbcs680Test() {
        String[] expected = { "umbcs680", "0", umbcs680.getCreationTime().toString() };
        assertArrayEquals(expected, dirToStringArray(umbcs680));
    }

    @Test
    public void verifyDirectoryEqualityHw01Test() {
        String[] expected = { "hw01", "0", hw01.getCreationTime().toString() };
        assertArrayEquals(expected, dirToStringArray(hw01));
    }

    @Test
    public void verifyDirectoryEqualitySrcTest() {
        String[] expected = { "src", "0", src.getCreationTime().toString() };
        assertArrayEquals(expected, dirToStringArray(src));
    }

    @Test
    public void directoryGetChildrenTest() {
        List<FSElement> children = umbcs680.getChildren();
        assertEquals(2, children.size());  // hw01 and readme.md
    }

    @Test
    public void directoryGetSubDirectoriesTest() {
        List<Directory> subDirs = umbcs680.getSubDirectories();
        assertEquals(1, subDirs.size());
        assertEquals("hw01", subDirs.get(0).getName());
    }

    @Test
    public void directoryGetFilesTest() {
        List<File> files = umbcs680.getFiles();
        assertEquals(1, files.size());
        assertEquals("readme.md", files.get(0).getName());
    }

    @Test
    public void directoryGetLinksTest() {
        List<Link> links = hw01.getLinks();
        assertEquals(1, links.size());
        assertEquals("rm.md", links.get(0).getName());
    }

    @Test
    public void directoryCountChildrenTest() {
        assertEquals(2, umbcs680.countChildren());  // hw01 and readme.md
    }

    @Test
    public void directoryGetTotalSizeTest() {
        assertEquals(33, umbcs680.getTotalSize()); // A.java(10) + B.java(15) + build.xml(5) + readme.md(3)
    }

    @Test
    public void directoryIsDirectoryTest() {
        assertTrue(umbcs680.isDirectory());
    }

    @Test
    public void directoryIsLinkTest() {
        assertFalse(umbcs680.isLink());
    }
}
