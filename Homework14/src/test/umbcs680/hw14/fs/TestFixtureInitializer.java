package umbcs680.hw14.fs;

import java.time.LocalDateTime;
import java.util.*;

public class TestFixtureInitializer {
    public static FileSystem createFS() {
        FileSystem fs = FileSystem.getFileSystem();
        fs.clearRootDirs(); // Clear any previous state

        LocalDateTime now = LocalDateTime.now();

        Directory umbcs680 = new Directory(null,"umbcs680", 0, now);
        fs.appendRootDir(umbcs680);

        Directory hw01 = new Directory(umbcs680, "hw01", 0, now);
        umbcs680.appendChild(hw01);

        Directory src = new Directory(hw01, "src", 0, now);
        hw01.appendChild(src);

        File a = new File(src, "A.java", 100, now);
        File b = new File(src, "B.java", 200, now);
        src.appendChild(a);
        src.appendChild(b);

        File buildXml = new File(hw01, "build.xml", 50, now);
        hw01.appendChild(buildXml);

        File readme = new File(umbcs680, "readme.md", 75, now);
        umbcs680.appendChild(readme);

        Link linkToReadme = new Link(hw01, "linkToReadme", 0, now, readme);
        hw01.appendChild(linkToReadme);

        return fs;
    }
}
