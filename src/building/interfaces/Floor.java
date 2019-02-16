package building.interfaces;

import building.exceptions.SpaceIndexOutOfBoundsException;

public interface Floor extends Comparable<Floor>, Iterable<Space> {

    int getSpaceCount();

    int getSquare();

    int getRoomCount();

    Space[] getSpaces();

    Space getSpace(int spacePosition) throws SpaceIndexOutOfBoundsException;

    void setSpace(int spacePosition, Space space) throws SpaceIndexOutOfBoundsException;

    void addSpace(int spacePosition, Space space) throws SpaceIndexOutOfBoundsException;

    void delSpace(int spacePosition) throws SpaceIndexOutOfBoundsException;

    Space getBestSpace();

    Object clone() throws CloneNotSupportedException;
}
