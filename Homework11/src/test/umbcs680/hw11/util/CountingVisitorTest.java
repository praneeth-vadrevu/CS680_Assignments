package umbcs680.hw11.util;

import umbcs680.hw11.fs.*;
import umbcs680.hw11.util.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CountingVisitorTest {

    private static FileSystem fs;

    @BeforeAll
    public static void setUp() {
        fs = FileSystem.getFileSystem();
        fs.clearRootDirs();                    // Ensure clean state
        fs = TestFixtureInitializer.createFS(); // Capture the initialized state
    }


    @Test
    public void directoryStructureDebugTest() {
        fs.getRootDirs().forEach(d -> {
            System.out.println("Root Dir: " + d.getName());
            d.getFiles().forEach(f -> System.out.println("  File: " + f.getName()));
        });
    }
    @Test
    public void testCountingVisitor() {
        CountingVisitor visitor = new CountingVisitor();
        fs.accept(visitor);


        assertEquals(4, visitor.getFileCount(), "Should count 4 files");
        assertEquals(3, visitor.getDirectoryCount(), "Should count 3 directories");

    }



}
