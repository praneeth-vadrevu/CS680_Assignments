package umbcs680.hw11.fs;

import java.util.Collections;
import java.util.List;

public class CreationTimeComparatorStrategy implements FSElementComparatorStrategy {

    @Override
    public List<FSElement> sort(List<FSElement> elements) {
        Collections.sort(elements, (e1, e2) -> e1.getCreationTime().compareTo(e2.getCreationTime()));
        return elements;
    }
}
