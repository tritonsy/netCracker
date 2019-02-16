package building.utlility;

import building.interfaces.Space;

import java.util.Comparator;

public class SpaceComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Space space1 = (Space) o1;
        Space space2 = (Space) o2;
        return Integer.compare(space2.getRoomAmount(), space1.getRoomAmount());
    }
}
