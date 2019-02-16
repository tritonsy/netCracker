package building.utlility;

import building.interfaces.Floor;

import java.util.Comparator;

public class FloorComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Floor floor1 = (Floor) o1;
        Floor floor2 = (Floor) o2;
        return Integer.compare(floor2.getSquare(), floor1.getSquare());
    }
}