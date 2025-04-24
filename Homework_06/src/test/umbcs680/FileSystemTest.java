import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FileSystemTest {

    @Test
    public void testSingletonBehavior() {
        FileSystem fs1 = FileSystem.getFileSystem();
        FileSystem fs2 = FileSystem.getFileSystem();
        assertSame(fs1, fs2);
    }

    @Test
    public void testAppendRootDir() {
        FileSystem fs = FileSystem.getFileSystem();
        int originalCount = fs.getRootDirs().size();

        Directory root = new Directory(null, "DriveX", 0, java.time.LocalDateTime.now());
        fs.appendRootDir(root);

        assertEquals(originalCount + 1, fs.getRootDirs().size());
        assertTrue(fs.getRootDirs().contains(root));
    }
}
