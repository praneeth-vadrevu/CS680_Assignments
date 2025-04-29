import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class LinkTest {

    private static FileSystem fs;
    private static Link rmLink;

    @BeforeAll
    public static void setUp() {
        fs = TestFixtureInitializer.createFS();
        Directory hw01 = fs.getRootDirs().get(0).getSubDirectories().get(0);
        rmLink = hw01.getLinks().get(0); // rm.md
    }

    @Test
    public void linkConstructorTest() {
        File dummyTarget = new File(null, "dummy", 50, LocalDateTime.now());
        LocalDateTime now = LocalDateTime.now();
        Link link = new Link(null, "dummyLink", 0, now, dummyTarget);

        assertEquals("dummyLink", link.getName());
        assertEquals(50, link.getSize());
        assertEquals(dummyTarget, link.getTarget());
    }

    @Test
    public void rmLinkNameTest() {
        assertEquals("rm.md", rmLink.getName());
    }

    @Test
    public void rmLinkTargetNameTest() {
        assertEquals("readme.md", rmLink.getTarget().getName());
    }

    @Test
    public void rmLinkIsLinkTest() {
        assertTrue(rmLink.isLink());
    }

    @Test
    public void rmLinkDelegatesSizeTest() {
        assertEquals(3, rmLink.getSize());
    }
}
