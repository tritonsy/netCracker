package building.interfaces;

public interface Space extends Comparable<Space> {

    int getRoomAmount();

    void setRoomAmount(int roomCount);

    int getSquare();

    void setSquare(int square);

    Object clone() throws CloneNotSupportedException;
}
