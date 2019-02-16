package building.facrotries;

import building.dwelling.Dwelling;
import building.dwelling.DwellingFloor;
import building.dwelling.Flat;
import building.interfaces.Building;
import building.interfaces.BuildingFactory;
import building.interfaces.Floor;
import building.interfaces.Space;

public class DwellingFactory implements BuildingFactory {

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
        return new DwellingFloor(spaceCount);
    }

    @Override
    public Floor createFloor(Space... spaces) {
        return new DwellingFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsCount, int... spacesCount) {
        return new Dwelling(floorsCount, spacesCount);
    }

    @Override
    public Building createBuilding(Floor... floors) {
        return new Dwelling(floors);
    }
}
