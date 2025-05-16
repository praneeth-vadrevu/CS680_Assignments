package umbcs680.hw16.fs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class LinkTest {

    private static FileSystem fs;
    private static Link linkToReadme;

    @BeforeAll
    public static void setUp() {
        fs = FileSystem.getFileSystem();
        fs.clearRootDirs();               // Clear first
        fs = TestFixtureInitializer.createFS();  // Properly initialize

        Directory hw01 = fs.getRootDirs().getFirst().getSubDirectories().getFirst();
        linkToReadme = hw01.getLinks().getFirst();
    }


    @Test
    public void testLinkConstructor() {
        File target = new File(null, "dummy.txt", 100, LocalDateTime.now());
        LocalDateTime now = LocalDateTime.now();
        Link testLink = new Link(null, "dummyLink", 0, now, target);

        assertEquals("dummyLink", testLink.getName());
        assertEquals(target, testLink.getTarget());
        assertEquals(100, testLink.getSize());  // Delegates to target
    }

    @Test
    public void testGetName() {
        assertEquals("linkToReadme", linkToReadme.getName());
    }

    @Test
    public void testGetTarget() {
        assertEquals("readme.md", linkToReadme.getTarget().getName());
    }

    @Test
    public void testIsDirectoryDelegation() {
        assertFalse(linkToReadme.isDirectory());  // Target is a file, so false
    }

    @Test
    public void testIsLink() {
        assertTrue(linkToReadme.isLink());
    }

    @Test
    public void testDelegatedSize() {
        assertEquals(75, linkToReadme.getSize());  // Should match readme.md size
    }
}
