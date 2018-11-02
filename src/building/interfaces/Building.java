package building.interfaces;

public interface Building{

    int getFloorCount();

    int getSpaceCount();

    int getSpace();

    int getRoomCount();

    Floor[] getFloors();

    Floor getFloor(int floorPosition);

    void setFloor(int floorPosition, Floor floor);

    Space getSpace(int spacePosition);

    void setSpace(int spacePosition, Space space);

    void addSpace(int spacePosition, Space space);

    void delSpace(int spacePosition);

    Space getBestSpace();

    Space[] getSortedSpaces();

}