package umbcs680.hw11.fs;

import java.util.Comparator;

public class ReverseAlphabeticalComparatorStrategy implements Comparator<FSElement> {

    @Override
    public int compare(FSElement e1, FSElement e2) {
        return e2.getName().compareToIgnoreCase(e1.getName());
    }
}
