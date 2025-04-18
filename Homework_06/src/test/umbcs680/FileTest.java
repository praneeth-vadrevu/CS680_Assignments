import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class FileTest {

    @Test
    public void testFileAttributes() {
        Directory parent = new Directory(null, "docs", 0, LocalDateTime.now());
        File file = new File(parent, "notes.txt", 100, LocalDateTime.now());

        assertEquals("notes.txt", file.getName());
        assertEquals(100, file.getSize());
        assertFalse(file.isDirectory());
        assertEquals(parent, file.getParent());
    }
}
