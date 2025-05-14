package umbcs680.hw11.fs;

import java.util.Collections;
import java.util.List;

public class ReverseAlphabeticalComparatorStrategy implements FSElementComparatorStrategy {

    @Override
    public List<FSElement> sort(List<FSElement> elements) {
        Collections.sort(elements, (e1, e2) -> e2.getName().compareToIgnoreCase(e1.getName()));
        return elements;
    }
}
