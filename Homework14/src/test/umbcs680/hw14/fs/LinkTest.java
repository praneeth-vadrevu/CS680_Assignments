package umbcs680.hw14.fs;

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
    public void testLinkConstructorAndGetters() {
        File target = new File(null, "dummy.txt", 100, LocalDateTime.now());
        LocalDateTime now = LocalDateTime.now();
        Link testLink = new Link(null, "dummyLink", 0, now, target);

        assertEquals("dummyLink", testLink.getName());
        assertEquals(target, testLink.getTarget());
        assertEquals(100, testLink.getSize());  // Delegates to target size
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
    public void testSortLinksByAlphabeticalOrderWithLambda() {
        List<FSElement> sorted = new LinkedList<>(links);
        sorted.sort((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()));
        for (int i = 1; i < sorted.size(); i++) {
            assertTrue(sorted.get(i - 1).getName().compareToIgnoreCase(sorted.get(i).getName()) <= 0);
        }
    }

    @Test
    public void testSortLinksByReverseAlphabeticalOrderWithLambda() {
        List<FSElement> sorted = new LinkedList<>(links);
        sorted.sort((e1, e2) -> e2.getName().compareToIgnoreCase(e1.getName()));
        for (int i = 1; i < sorted.size(); i++) {
            assertTrue(sorted.get(i - 1).getName().compareToIgnoreCase(sorted.get(i).getName()) >= 0);
        }
    }

    @Test
    public void testSortLinksByCreationTimeWithLambda() {
        List<FSElement> sorted = new LinkedList<>(links);
        sorted.sort((e1, e2) -> e1.getCreationTime().compareTo(e2.getCreationTime()));
        for (int i = 1; i < sorted.size(); i++) {
            assertFalse(sorted.get(i - 1).getCreationTime().isAfter(sorted.get(i).getCreationTime()));
        }
    }
}

