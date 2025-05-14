package umbcs680.hw08.fs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class FileTest {

    private static FileSystem fs;
    private static Directory umbcs680;
    private static File readme;

    @BeforeAll
    public static void setUp() {
        fs = FileSystem.getFileSystem();
        fs.clearRootDirs();  // Clear first
        fs = TestFixtureInitializer.createFS();  // Then initialize properly
        umbcs680 = fs.getRootDirs().getFirst();  // Safely fetch umbcs680
        readme = umbcs680.getFiles().getFirst(); // Safely fetch readme.md
    }


    // Utility method to convert file to string array for equality check
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
}
