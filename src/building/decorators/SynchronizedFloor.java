package building.decorators;

import building.interfaces.Floor;
import building.interfaces.Space;

import java.util.Iterator;

public class SynchronizedFloor implements Floor {
    private Floor floor;

    public SynchronizedFloor(Floor floor) {
        this.floor = floor;
    }

    @Override
    public synchronized int getSpaceCount() {
        return floor.getSpaceCount();
    }

    @Override
    public synchronized int getSquare() {
        return floor.getSquare();
    }

    @Override
    public synchronized int getRoomCount() {
        return floor.getRoomCount();
    }

    @Override
    public synchronized Space[] getSpaces() {
        return floor.getSpaces();
    }

    @Override
    public synchronized Space getSpace(int spacePosition) {
        return floor.getSpace(spacePosition);
    }

    @Override
    public synchronized void setSpace(int spacePosition, Space space) {
        floor.setSpace(spacePosition, space);
    }

    @Override
    public synchronized void addSpace(int spacePosition, Space space) {
        floor.addSpace(spacePosition, space);
    }

    @Override
    public synchronized void delSpace(int spacePosition) {
        floor.delSpace(spacePosition);
    }

    @Override
    public synchronized Space getBestSpace() {
        return floor.getBestSpace();
    }

    @Override
    public synchronized Object clone() throws CloneNotSupportedException {
        return new SynchronizedFloor((Floor) floor.clone());
    }

    @Override
    public synchronized int compareTo(Floor o) {
        return floor.compareTo(o);
    }

    @Override
    public Iterator<Space> iterator() {
        return new Iterator<Space>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index <= floor.getSpaceCount();
            }

            @Override
            public Space next() {
                return floor.getSpace(index);
            }
        };
    }
}
