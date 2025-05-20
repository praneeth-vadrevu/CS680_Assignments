package umbcs680.hw11.fs;

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
    public void verifyFileEqualityReadmeTest() {
        String[] expected = { "readme.md", "75", readme.getCreationTime().toString() };
        assertArrayEquals(expected, fileToStringArray(readme));
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
    public void testAscendingSizeComparator() {
        List<FSElement> files = new LinkedList<>(umbcs680.getFiles());
        files.sort(new AscendingSizeComparatorStrategy());
        for (int i = 1; i < files.size(); i++) {
            assertTrue(files.get(i - 1).getSize() <= files.get(i).getSize());
        }
    }

    @Test
    public void testDescendingSizeComparator() {
        List<FSElement> files = new LinkedList<>(umbcs680.getFiles());
        files.sort(new DescendingSizeComparatorStrategy());
        for (int i = 1; i < files.size(); i++) {
            assertTrue(files.get(i - 1).getSize() >= files.get(i).getSize());
        }
    }

    @Test
    public void testAlphabeticalComparator() {
        List<FSElement> files = new LinkedList<>(umbcs680.getFiles());
        files.sort(new AlphabeticalNameComparatorStrategy());
        for (int i = 1; i < files.size(); i++) {
            assertTrue(files.get(i - 1).getName().compareToIgnoreCase(files.get(i).getName()) <= 0);
        }
    }

    @Test
    public void testReverseAlphabeticalComparator() {
        List<FSElement> files = new LinkedList<>(umbcs680.getFiles());
        files.sort(new ReverseAlphabeticalComparatorStrategy());
        for (int i = 1; i < files.size(); i++) {
            assertTrue(files.get(i - 1).getName().compareToIgnoreCase(files.get(i).getName()) >= 0);
        }
    }

    @Test
    public void testCreationTimeComparator() {
        List<FSElement> files = new LinkedList<>(umbcs680.getFiles());
        files.sort(new CreationTimeComparatorStrategy());
        for (int i = 1; i < files.size(); i++) {
            assertFalse(files.get(i - 1).getCreationTime().isAfter(files.get(i).getCreationTime()));
        }
    }
}
