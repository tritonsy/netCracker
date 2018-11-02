package building.interfaces;

public interface Floor{

    int getSpaceCount();

    int getSquare();

    int getRoomCount();

    Space[] getSpaces();

    Space getSpace(int spacePosition);

    void setSpace(int spacePosition, Space space);

    void addSpace(int spacePosition, Space space);

    void delSpace(int spacePosition);

    Space getBestSpace();
}
