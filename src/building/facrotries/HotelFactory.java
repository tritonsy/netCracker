package building.facrotries;

import building.dwelling.Flat;
import building.dwelling.hotel.Hotel;
import building.dwelling.hotel.HotelFloor;
import building.interfaces.Building;
import building.interfaces.BuildingFactory;
import building.interfaces.Floor;
import building.interfaces.Space;

public class HotelFactory implements BuildingFactory {

    @Override
    public Space createSpace(int square) {
        return new Flat(square);
    }

    @Override
    public Space createSpace(int roomsCount, int square) {
        return new Flat(roomsCount, square);
    }

    @Override
    public Floor createFloor(int spaceCount) {
        return new HotelFloor(spaceCount);
    }

    @Override
    public Floor createFloor(Space... spaces) {
        return new HotelFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsCount, int... spacesCount) {
        return new Hotel(floorsCount, spacesCount);
    }

    @Override
    public Building createBuilding(Floor... floors) {
        return new Hotel(floors);
    }

}
