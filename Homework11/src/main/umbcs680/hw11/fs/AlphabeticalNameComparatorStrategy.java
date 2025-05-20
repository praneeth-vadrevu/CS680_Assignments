package umbcs680.hw11.fs;

import java.util.Comparator;

public class AlphabeticalNameComparatorStrategy implements Comparator<FSElement> {

    @Override
    public int compare(FSElement e1, FSElement e2) {
        return e1.getName().compareToIgnoreCase(e2.getName());
    }
}

