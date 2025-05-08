package umbcs680.FileSystem;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.*;

public class FileTest {

    private static FileSystem fs;

    @BeforeAll
    public static void setUpFS() {
        fs = TestFixtureInitializer.createFS();
    }

    // Helper method to convert a File into String[] (name, size, creationTime)
    private String[] fileToStringArray(File file) {
        String[] fileInfo = {
                file.getName(),
                String.valueOf(file.getSize()),
                file.getCreationTime().toString()
        };
        return fileInfo;
    }

    @Test
    public void verifyFileEquality() {
        Directory root = fs.getRootDirs().get(0); // umbcs680
        File readme = null;
        for (FSElement e : root.getChildren()) {
            if (e instanceof File && e.getName().equals("readme.md")) {
                readme = (File) e;
                break;
            }
        }
        assertNotNull(readme);

        String[] expected = { "readme.md", "3", readme.getCreationTime().toString() };
        assertArrayEquals(expected, fileToStringArray(readme));
    }

    @Test
    public void getNameTest() {
        Directory root = fs.getRootDirs().get(0);
        File readme = (File) root.getFiles().get(0);
        assertEquals("readme.md", readme.getName());
    }

    @Test
    public void getSizeTest() {
        Directory root = fs.getRootDirs().get(0);
        File readme = (File) root.getFiles().get(0);
        assertEquals(3, readme.getSize());
    }

    @Test
    public void getCreationTimeTest() {
        Directory root = fs.getRootDirs().get(0);
        File readme = (File) root.getFiles().get(0);
        assertNotNull(readme.getCreationTime());
    }

    @Test
    public void getParentTest() {
        Directory root = fs.getRootDirs().get(0);
        File readme = (File) root.getFiles().get(0);
        assertEquals(root, readme.getParent());
    }
}
