package umbcs680.hw11.fs;

import java.util.Comparator;

public class AscendingSizeComparatorStrategy implements Comparator<FSElement> {

    @Override
    public int compare(FSElement e1, FSElement e2) {
        return Integer.compare(e1.getSize(), e2.getSize());
    }
}

