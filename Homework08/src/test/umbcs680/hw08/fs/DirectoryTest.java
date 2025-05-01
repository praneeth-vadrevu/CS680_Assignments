

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DirectoryTest {

    private static FileSystem fs;

    @BeforeAll
    public static void setUpFS() {
        fs = TestFixtureInitializer.createFS();
    }

    private String[] dirToStringArray(Directory d) {
        return new String[]{
                d.getName(),
                String.valueOf(d.getSize()),
                d.getCreationTime().toString()
        };
    }

    @Test
    public void verifyDirectoryEqualityUmbcs680() {
        Directory dir = fs.getRootDirs().getFirst();
        String[] expected = {"umbcs680", "0", dir.getCreationTime().toString()};
        assertArrayEquals(expected, dirToStringArray(dir));
    }

    @Test
    public void getChildrenTest() {
        Directory umbcs680 = fs.getRootDirs().getFirst();
        assertEquals(2, umbcs680.countChildren()); // hw01 and readme.md
    }

    @Test
    public void countChildrenTest() {
        Directory hw01 = (Directory) fs.getRootDirs().getFirst().getChildren().getFirst();
        assertEquals(3, hw01.countChildren()); // src, build.xml, link
    }

    @Test
    public void getTotalSizeTest() {
        Directory umbcs680 = fs.getRootDirs().getFirst();
        assertEquals(425, umbcs680.getTotalSize()); // A.java 100 + B.java 200 + build.xml 50 + readme.md 75
    }
}
