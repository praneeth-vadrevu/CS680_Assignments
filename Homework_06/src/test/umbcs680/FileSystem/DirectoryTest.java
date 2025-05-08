package umbcs680.FileSystem;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class DirectoryTest {

    private static FileSystem fs;

    @BeforeAll
    public static void setUpFS() {
        fs = TestFixtureInitializer.createFS();
    }

    // Helper method to convert a Directory into String[]
    private String[] dirToStringArray(Directory d) {
        return new String[] {
                d.getName(),
                String.valueOf(d.getSize()),
                d.getCreationTime().toString()
        };
    }

    @Test
    public void verifyDirectoryEqualityRoot() {
        Directory root = fs.getRootDirs().get(0);
        String[] expected = { "umbcs680", "0", root.getCreationTime().toString() };
        assertArrayEquals(expected, dirToStringArray(root));
    }

    @Test
    public void verifyDirectoryEqualityHome() {
        Directory root = fs.getRootDirs().get(0);
        Directory home = null;
        for (FSElement e : root.getChildren()) {
            if (e instanceof Directory && e.getName().equals("hw01")) {
                home = (Directory) e;
                break;
            }
        }
        assertNotNull(home);
        String[] expected = { "hw01", "0", home.getCreationTime().toString() };
        assertArrayEquals(expected, dirToStringArray(home));
    }





    @Test
    public void getChildrenTest() {
        fs = TestFixtureInitializer.createFS(); // 🔧 Reset state
        Directory root = fs.getRootDirs().get(0);
        List<FSElement> children = root.getChildren();
        assertEquals(2, children.size()); // hw01, readme.md
    }


    @Test
    public void countChildrenTest() {
        fs = TestFixtureInitializer.createFS(); // 🔧 Reset state
        Directory root = fs.getRootDirs().get(0);
        assertEquals(2, root.countChildren());
    }


    @Test
    public void appendChildTest() {

        fs = TestFixtureInitializer.createFS();

        Directory root = fs.getRootDirs().get(0);
        Directory newDir = new Directory(root, "newDir", 0, LocalDateTime.now());

        int before = root.countChildren();
        root.appendChild(newDir);
        int after = root.countChildren();

        assertEquals(before + 1, after);
        assertTrue(root.getChildren().contains(newDir));
    }

    @Test
    public void getSubDirectoriesTest() {
        Directory root = fs.getRootDirs().get(0);
        List<Directory> subDirs = root.getSubDirectories();
        assertEquals(1, subDirs.size());
        assertEquals("hw01", subDirs.get(0).getName());
    }

    @Test
    public void getFilesTest() {
        Directory root = fs.getRootDirs().get(0);
        List<File> files = root.getFiles();
        assertEquals(1, files.size());
        assertEquals("readme.md", files.get(0).getName());
    }

    @Test
    public void getTotalSizeTest() {
        Directory root = fs.getRootDirs().get(0);
        int expectedSize = 10 + 15 + 5 + 3;  // A.java + B.java + build.xml + readme.md
        assertEquals(expectedSize, root.getTotalSize());
    }
}

