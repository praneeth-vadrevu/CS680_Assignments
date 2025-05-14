package umbcs680.hw11.util;

import umbcs680.hw11.fs.*;
import umbcs680.hw11.util.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileCrawlingVisitorTest {

    private static FileSystem fs;

    @BeforeAll
    public static void setUp() {
        fs = FileSystem.getFileSystem();
        fs.clearRootDirs(); // Reset the state
        TestFixtureInitializer.createFS();
    }

    @Test
    public void testFileCrawlingVisitor() {
        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        fs.accept(visitor);

        List<File> collectedFiles = visitor.getFiles();

        // Expecting 4 files: readme.md, build.xml, A.java, B.java
        assertEquals(4, collectedFiles.size(), "Should collect 4 files");

        // Validate some file names to confirm correctness
        boolean hasReadme = collectedFiles.stream().anyMatch(f -> f.getName().equals("readme.md"));
        boolean hasBuildXml = collectedFiles.stream().anyMatch(f -> f.getName().equals("build.xml"));
        boolean hasAJava = collectedFiles.stream().anyMatch(f -> f.getName().equals("A.java"));
        boolean hasBJava = collectedFiles.stream().anyMatch(f -> f.getName().equals("B.java"));

        assertTrue(hasReadme, "Should include readme.md");
        assertTrue(hasBuildXml, "Should include build.xml");
        assertTrue(hasAJava, "Should include A.java");
        assertTrue(hasBJava, "Should include B.java");
    }
}
