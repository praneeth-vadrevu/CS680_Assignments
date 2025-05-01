package umbcs680.FileSystem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkTest {

    private static FileSystem fs;

    @BeforeAll
    public static void setUpFS() {
        fs = TestFixtureInitializer.createFS();
    }

    @Test
    public void verifyLinkTarget() {
        Directory root = fs.getRootDirs().getFirst();
        Directory hw01 = (Directory) root.getChildren().getFirst();
        Link linkToReadme = (Link) hw01.getChildren().stream()
                .filter(f -> f instanceof Link)
                .findFirst().orElse(null);

        assertNotNull(linkToReadme);
        assertEquals("linkToReadme", linkToReadme.getName());
        assertEquals("readme.md", linkToReadme.getTarget().getName());
    }

    @Test
    public void isLinkTest() {
        Directory hw01 = (Directory) fs.getRootDirs().getFirst().getChildren().getFirst();
        Link linkToReadme = (Link) hw01.getChildren().stream()
                .filter(f -> f instanceof Link)
                .findFirst().orElse(null);

        assertNotNull(linkToReadme);
        assertTrue(linkToReadme.isLink());
    }
}
