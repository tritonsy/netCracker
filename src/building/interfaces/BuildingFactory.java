package building.interfaces;

public interface BuildingFactory {

    Space createSpace(int area);

    Space createSpace(int roomCount, int area);

    Floor createFloor(int spaceCount);

    Floor createFloor(Space... spaces);

    Building createBuilding(int floorCount, int... spaceCounts);

    Building createBuilding(Floor... floors);

}
