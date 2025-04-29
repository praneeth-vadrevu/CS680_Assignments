import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class DirectoryTest {

    private static FileSystem fs;
    private static Directory umbcs680;
    private static Directory hw01;

    @BeforeAll
    public static void setUp() {
        fs = TestFixtureInitializer.createFS();
        umbcs680 = fs.getRootDirs().get(0);
        hw01 = umbcs680.getSubDirectories().get(0);
    }

    @Test
    public void directoryConstructorTest() {
        LocalDateTime now = LocalDateTime.now();
        Directory testDir = new Directory(null, "testDir", 0, now);

        assertEquals("testDir", testDir.getName());
        assertEquals(0, testDir.getSize());
        assertEquals(now, testDir.getCreationTime());
        assertNull(testDir.getParent());
    }

    private String[] dirToStringArray(Directory d) {
        return new String[] { d.getName(), String.valueOf(d.getSize()), d.getCreationTime().toString() };
    }

    @Test
    public void verifyDirectoryEqualityUmbcs680Test() {
        String[] expected = { "umbcs680", "0", umbcs680.getCreationTime().toString() };
        assertArrayEquals(expected, dirToStringArray(umbcs680));
    }

    @Test
    public void verifyDirectoryEqualityHw01Test() {
        String[] expected = { "hw01", "0", hw01.getCreationTime().toString() };
        assertArrayEquals(expected, dirToStringArray(hw01));
    }

    @Test
    public void directoryGetNameTest() {
        assertEquals("umbcs680", umbcs680.getName());
    }

    @Test
    public void directoryGetSizeTest() {
        assertEquals(0, umbcs680.getSize());
    }

    @Test
    public void directoryGetCreationTimeTest() {
        assertNotNull(umbcs680.getCreationTime());
    }
}
