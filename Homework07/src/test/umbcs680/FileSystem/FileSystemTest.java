import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class FileSystemTest {

    private static FileSystem fs;

    @BeforeAll
    public static void setUp() {
        fs = TestFixtureInitializer.createFS();
    }

    @Test
    public void singletonFileSystemTest() {
        FileSystem anotherInstance = FileSystem.getFileSystem();
        assertSame(fs, anotherInstance);
    }

    @Test
    public void appendRootDirectoryTest() {
        Directory newRoot = new Directory(null, "newRoot", 0, LocalDateTime.now());
        int before = fs.getRootDirs().size();
        fs.appendRootDir(newRoot);
        int after = fs.getRootDirs().size();

        assertEquals(before + 1, after);
        assertTrue(fs.getRootDirs().contains(newRoot));
    }
}
