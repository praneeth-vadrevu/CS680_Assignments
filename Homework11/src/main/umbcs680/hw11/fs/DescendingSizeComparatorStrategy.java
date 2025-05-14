package umbcs680.hw11.fs;

import java.util.Collections;
import java.util.List;

public class DescendingSizeComparatorStrategy implements FSElementComparatorStrategy {

    @Override
    public List<FSElement> sort(List<FSElement> elements) {
        Collections.sort(elements, (e1, e2) -> Integer.compare(e2.getSize(), e1.getSize()));
        return elements;
    }
}


