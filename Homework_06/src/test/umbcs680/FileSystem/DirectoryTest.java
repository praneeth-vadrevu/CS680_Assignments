import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class DirectoryTest {

    @Test
    public void testAppendAndGetChildren() {
        Directory root = new Directory(null, "root", 0, LocalDateTime.now());
        File file = new File(root, "file1.txt", 50, LocalDateTime.now());
        root.appendChild(file);

        assertEquals(1, root.countChildren());
        assertEquals(file, root.getChildren().getFirst());
    }

    @Test
    public void testGetTotalSize() {
        Directory root = new Directory(null, "root", 0, LocalDateTime.now());
        Directory subDir = new Directory(root, "sub", 0, LocalDateTime.now());
        File file1 = new File(subDir, "a.txt", 200, LocalDateTime.now());
        File file2 = new File(root, "b.txt", 300, LocalDateTime.now());

        subDir.appendChild(file1);
        root.appendChild(subDir);
        root.appendChild(file2);

        assertEquals(500, root.getTotalSize());
    }
}
