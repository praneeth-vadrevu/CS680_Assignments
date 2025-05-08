package umbcs680.FileSystem;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.*;
import java.time.*;

public class FileSystemTest {

    private static FileSystem fs;

    @BeforeAll
    public static void setUpFS() {
        fs = TestFixtureInitializer.createFS();
    }

    @Test
    public void verifySingletonTest() {
        FileSystem fs2 = FileSystem.getFileSystem();
        assertSame(fs, fs2); // Singleton test
    }

    @Test
    public void getRootDirsTest() {
        List<Directory> roots = fs.getRootDirs();
        assertEquals(1, roots.size());
        assertEquals("umbcs680", roots.get(0).getName());
    }

    @Test
    public void appendRootDirTest() {
        Directory newRoot = new Directory(null, "newDrive", 0, LocalDateTime.now());
        int before = fs.getRootDirs().size();
        fs.appendRootDir(newRoot);
        int after = fs.getRootDirs().size();

        assertEquals(before + 1, after);
        assertTrue(fs.getRootDirs().contains(newRoot));
    }
}
