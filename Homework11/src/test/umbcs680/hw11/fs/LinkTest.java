package umbcs680.hw11.fs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class LinkTest {

    private static FileSystem fs;
    private static Link linkToReadme;
    private static List<FSElement> links;

    @BeforeAll
    public static void setUp() {
        fs = TestFixtureInitializer.createFS();
        Directory hw01 = fs.getRootDirs().getFirst().getSubDirectories().getFirst();
        linkToReadme = hw01.getLinks().getFirst();
        links = new LinkedList<>(hw01.getLinks());
    }

    @Test
    public void testLinkConstructor() {
        File target = new File(null, "dummy.txt", 100, LocalDateTime.now());
        LocalDateTime now = LocalDateTime.now();
        Link testLink = new Link(null, "dummyLink", 0, now, target);

        assertEquals("dummyLink", testLink.getName());
        assertEquals(target, testLink.getTarget());
        assertEquals(100, testLink.getSize());
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
        assertFalse(linkToReadme.isDirectory());
    }

    @Test
    public void testIsLink() {
        assertTrue(linkToReadme.isLink());
    }

    @Test
    public void testDelegatedSize() {
        assertEquals(75, linkToReadme.getSize());
    }

    @Test
    public void testAlphabeticalComparator() {
        links.sort(new AlphabeticalNameComparatorStrategy());
        for (int i = 1; i < links.size(); i++) {
            assertTrue(links.get(i - 1).getName().compareToIgnoreCase(links.get(i).getName()) <= 0);
        }
    }

    @Test
    public void testReverseAlphabeticalComparator() {
        links.sort(new ReverseAlphabeticalComparatorStrategy());
        for (int i = 1; i < links.size(); i++) {
            assertTrue(links.get(i - 1).getName().compareToIgnoreCase(links.get(i).getName()) >= 0);
        }
    }

    @Test
    public void testCreationTimeComparator() {
        links.sort(new CreationTimeComparatorStrategy());
        for (int i = 1; i < links.size(); i++) {
            assertFalse(links.get(i - 1).getCreationTime().isAfter(links.get(i).getCreationTime()));
        }
    }
}

