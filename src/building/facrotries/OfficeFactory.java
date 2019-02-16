package building.facrotries;

import building.interfaces.Building;
import building.interfaces.BuildingFactory;
import building.interfaces.Floor;
import building.interfaces.Space;
import building.office.Office;
import building.office.OfficeBuilding;
import building.office.OfficeFloor;

public class OfficeFactory implements BuildingFactory {
    @Override
    public Space createSpace(int square) {
        return new Office(square);
    }

    @Override
    public Space createSpace(int roomsCount, int square) {
        return new Office(roomsCount, square);
    }

    @Override
    public Floor createFloor(int spaceCount) {
        return new OfficeFloor(spaceCount);
    }

    @Override
    public Floor createFloor(Space... spaces) {
        return new OfficeFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsCount, int... spacesCount) {
        return new OfficeBuilding(floorsCount, spacesCount);
    }

    @Override
    public Building createBuilding(Floor... floors) {
        return new OfficeBuilding(floors);
    }

}
