package building.utlility;

import building.exceptions.InexchangeableFloorsException;
import building.exceptions.InexchangeableSpacesException;
import building.interfaces.Building;
import building.interfaces.Floor;
import building.interfaces.Space;

public class PlacementExchanger {

    /**
     * Метод проверки возможности обмена помещениями.
     */
    public static boolean spaceEqual(Space space2, Space space1) {
        return ((space1.getSquare() == space2.getSquare()) && (space1.getRoomAmount() == space2.getRoomAmount()));
    }

    /**
     * Метод проверки возможности обмена этажами.
     */
    public static boolean floorEqual(Floor floor1, Floor floor2) {
        return ((floor1.getSquare() == floor2.getSquare()) && (floor1.getRoomCount() == floor2.getRoomCount()));
    }

    /**
     * Метод обмена помещениями двух этажей
     * public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2).
     */
    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InexchangeableSpacesException {
        if (index1 > floor2.getSpaceCount() || index2 > floor1.getSpaceCount())
            throw new InexchangeableSpacesException("Invalid space index");
        else if (spaceEqual(floor1.getSpace(index1), floor2.getSpace(index2))) {
            Space bufSpace = floor1.getSpace(index1);
            floor1.setSpace(index1, floor2.getSpace(index2));
            floor2.setSpace(index2, bufSpace);
        }
    }

    /**
     * Метод обмена этажами двух зданий
     * public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2).
     */
    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws InexchangeableFloorsException {
        if (index2 > building1.getFloorCount() || index1 > building2.getFloorCount())
            throw new InexchangeableFloorsException("Invalid floor index");
        else if (floorEqual(building1.getFloor(index1), building2.getFloor(index2))) {
            Floor bufFloor = building1.getFloor(index1);
            building1.setFloor(index1, building2.getFloor(index2));
            building2.setFloor(index2, bufFloor);
        }
    }
}