package umbcs680.FileSystem;
import java.time.LocalDateTime;
import java.time.*;

public class TestFixtureInitializer {

    public static FileSystem createFS() {
        FileSystem fs = FileSystem.getFileSystem();
        fs.getRootDirs().clear();

        Directory umbcs680 = new Directory(null, "umbcs680", 0, LocalDateTime.now());
        fs.appendRootDir(umbcs680);

        Directory hw01 = new Directory(umbcs680, "hw01", 0, LocalDateTime.now());
        umbcs680.appendChild(hw01);

        File readme = new File(umbcs680, "readme.md", 3, LocalDateTime.now());
        umbcs680.appendChild(readme);

        Directory src = new Directory(hw01, "src", 0, LocalDateTime.now());
        hw01.appendChild(src);

        File aJava = new File(src, "A.java", 10, LocalDateTime.now());
        File bJava = new File(src, "B.java", 15, LocalDateTime.now());
        src.appendChild(aJava);
        src.appendChild(bJava);

        File buildXml = new File(hw01, "build.xml", 5, LocalDateTime.now());
        hw01.appendChild(buildXml);

        return fs;
    }
}
