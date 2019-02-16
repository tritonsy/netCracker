package building.interfaces;

import building.exceptions.FloorIndexOutOfBoundsException;
import building.exceptions.SpaceIndexOutOfBoundsException;

public interface Building extends Iterable<Floor> {

    int getFloorCount();

    int getSpaceCount();

    int getSpace();

    int getRoomCount();

    Floor[] getFloors();

    Floor getFloor(int floorPosition) throws FloorIndexOutOfBoundsException;

    void setFloor(int floorPosition, Floor floor) throws FloorIndexOutOfBoundsException;

    Space getSpace(int spacePosition) throws SpaceIndexOutOfBoundsException;

    void setSpace(int spacePosition, Space space) throws SpaceIndexOutOfBoundsException;

    void addSpace(int spacePosition, Space space) throws SpaceIndexOutOfBoundsException;

    void delSpace(int spacePosition) throws SpaceIndexOutOfBoundsException;

    Space getBestSpace();

    Space[] getSortedSpaces();

    Object clone() throws CloneNotSupportedException;


}