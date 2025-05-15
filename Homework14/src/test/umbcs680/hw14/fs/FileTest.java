package umbcs680.hw14.fs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class FileTest {

    private static FileSystem fs;
    private static Directory umbcs680;
    private static File readme;

    @BeforeAll
    public static void setUp() {
        fs = TestFixtureInitializer.createFS();
        umbcs680 = fs.getRootDirs().getFirst();
        readme = umbcs680.getFiles().getFirst();
    }

    private String[] fileToStringArray(File file) {
        return new String[] {
                file.getName(),
                String.valueOf(file.getSize()),
                file.getCreationTime().toString()
        };
    }



    @Test
    public void fileConstructorTest() {
        LocalDateTime now = LocalDateTime.now();
        File testFile = new File(null, "testFile.txt", 123, now);
        assertEquals("testFile.txt", testFile.getName());
        assertEquals(123, testFile.getSize());
        assertEquals(now, testFile.getCreationTime());
        assertNull(testFile.getParent());
    }

    @Test
    public void fileGetNameTest() {
        assertEquals("readme.md", readme.getName());
    }

    @Test
    public void fileGetSizeTest() {
        assertEquals(75, readme.getSize());
    }

    @Test
    public void fileGetCreationTimeTest() {
        assertNotNull(readme.getCreationTime());
    }

    @Test
    public void fileGetParentTest() {
        assertEquals(umbcs680, readme.getParent());
    }

    @Test
    public void fileIsDirectoryTest() {
        assertFalse(readme.isDirectory());
    }

    @Test
    public void fileIsLinkTest() {
        assertFalse(readme.isLink());
    }



    @Test
    public void testSortFilesByAscendingSizeWithLambda() {
        List<FSElement> files = new LinkedList<>(umbcs680.getFiles());
        List<FSElement> sorted = new LinkedList<>(files);
        sorted.sort((e1, e2) -> Integer.compare(e1.getSize(), e2.getSize()));
        for (int i = 1; i < sorted.size(); i++) {
            assertTrue(sorted.get(i - 1).getSize() <= sorted.get(i).getSize());
        }
    }

    @Test
    public void testSortFilesByDescendingSizeWithLambda() {
        List<FSElement> files = new LinkedList<>(umbcs680.getFiles());
        List<FSElement> sorted = new LinkedList<>(files);
        sorted.sort((e1, e2) -> Integer.compare(e2.getSize(), e1.getSize()));
        for (int i = 1; i < sorted.size(); i++) {
            assertTrue(sorted.get(i - 1).getSize() >= sorted.get(i).getSize());
        }
    }

    @Test
    public void testSortFilesByAlphabeticalOrderWithLambda() {
        List<FSElement> files = new LinkedList<>(umbcs680.getFiles());
        List<FSElement> sorted = new LinkedList<>(files);
        sorted.sort((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()));
        for (int i = 1; i < sorted.size(); i++) {
            assertTrue(sorted.get(i - 1).getName().compareToIgnoreCase(sorted.get(i).getName()) <= 0);
        }
    }

    @Test
    public void testSortFilesByReverseAlphabeticalOrderWithLambda() {
        List<FSElement> files = new LinkedList<>(umbcs680.getFiles());
        List<FSElement> sorted = new LinkedList<>(files);
        sorted.sort((e1, e2) -> e2.getName().compareToIgnoreCase(e1.getName()));
        for (int i = 1; i < sorted.size(); i++) {
            assertTrue(sorted.get(i - 1).getName().compareToIgnoreCase(sorted.get(i).getName()) >= 0);
        }
    }

    @Test
    public void testSortFilesByCreationTimeWithLambda() {
        List<FSElement> files = new LinkedList<>(umbcs680.getFiles());
        List<FSElement> sorted = new LinkedList<>(files);
        sorted.sort((e1, e2) -> e1.getCreationTime().compareTo(e2.getCreationTime()));
        for (int i = 1; i < sorted.size(); i++) {
            assertFalse(sorted.get(i - 1).getCreationTime().isAfter(sorted.get(i).getCreationTime()));
        }
    }
}

