package umbcs680.hw16.util;

import umbcs680.hw16.fs.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class FileSearchVisitorTest {

    @Test
    public void testInitialFoundFilesListIsEmpty() {
        FileSearchVisitor visitor = new FileSearchVisitor("anyFile.txt");
        List<File> foundFiles = visitor.getFoundFiles();
        assertTrue(foundFiles.isEmpty(), "Initial found files list should be empty");
    }

    @Test
    public void testVisitFileMatchingNameAddsFile() {
        FileSearchVisitor visitor = new FileSearchVisitor("match.txt");
        File matchingFile = new File(null, "match.txt", 100, java.time.LocalDateTime.now());

        visitor.visit(matchingFile);

        List<File> foundFiles = visitor.getFoundFiles();
        assertEquals(1, foundFiles.size(), "Should find one matching file");
        assertSame(matchingFile, foundFiles.get(0), "Found file should be the visited file");
    }

    @Test
    public void testVisitFileNonMatchingNameDoesNotAddFile() {
        FileSearchVisitor visitor = new FileSearchVisitor("target.txt");
        File nonMatchingFile = new File(null, "other.txt", 100, java.time.LocalDateTime.now());

        visitor.visit(nonMatchingFile);

        List<File> foundFiles = visitor.getFoundFiles();
        assertTrue(foundFiles.isEmpty(), "Non-matching file should not be added");
    }

    @Test
    public void testVisitLinkMatchingTargetFileAddsFile() {
        FileSearchVisitor visitor = new FileSearchVisitor("linkTarget.txt");
        File targetFile = new File(null, "linkTarget.txt", 100, java.time.LocalDateTime.now());
        Link link = new Link(null, "link", 0, java.time.LocalDateTime.now(), targetFile);

        visitor.visit(link);

        List<File> foundFiles = visitor.getFoundFiles();
        assertEquals(1, foundFiles.size(), "Should find file through link target");
        assertSame(targetFile, foundFiles.get(0), "Found file should be the link target file");
    }

    @Test
    public void testVisitLinkNonMatchingTargetDoesNotAddFile() {
        FileSearchVisitor visitor = new FileSearchVisitor("target.txt");
        File nonMatchingTarget = new File(null, "different.txt", 100, java.time.LocalDateTime.now());
        Link link = new Link(null, "link", 0, java.time.LocalDateTime.now(), nonMatchingTarget);

        visitor.visit(link);

        List<File> foundFiles = visitor.getFoundFiles();
        assertTrue(foundFiles.isEmpty(), "Non-matching link target should not be added");
    }

    @Test
    public void testVisitDirectoryDoesNotAffectFoundFiles() {
        FileSearchVisitor visitor = new FileSearchVisitor("anyFile.txt");
        Directory directory = new Directory(null, "dir", 0, java.time.LocalDateTime.now());

        visitor.visit(directory);

        List<File> foundFiles = visitor.getFoundFiles();
        assertTrue(foundFiles.isEmpty(), "Visiting directory should not affect found files");
    }
}
