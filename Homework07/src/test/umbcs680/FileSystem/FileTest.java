package umbcs680.FileSystem;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class FileTest {

    private static FileSystem fs;
    private static File readme;

    @BeforeAll
    public static void setUp() {
        fs = TestFixtureInitializer.createFS();
        readme = fs.getRootDirs().get(0).getFiles().get(0); // readme.md
    }

    @Test
    public void fileConstructorTest() {
        LocalDateTime now = LocalDateTime.now();
        File testFile = new File(null, "test.txt", 100, now);

        assertEquals("test.txt", testFile.getName());
        assertEquals(100, testFile.getSize());
        assertEquals(now, testFile.getCreationTime());
        assertNull(testFile.getParent());
    }

    private String[] fileToStringArray(File file) {
        return new String[] { file.getName(), String.valueOf(file.getSize()), file.getCreationTime().toString() };
    }

    @Test
    public void verifyFileEqualityReadmeTest() {
        String[] expected = { "readme.md", "3", readme.getCreationTime().toString() };
        assertArrayEquals(expected, fileToStringArray(readme));
    }

    @Test
    public void fileGetNameTest() {
        assertEquals("readme.md", readme.getName());
    }

    @Test
    public void fileGetSizeTest() {
        assertEquals(3, readme.getSize());
    }

    @Test
    public void fileGetCreationTimeTest() {
        assertNotNull(readme.getCreationTime());
    }
}
