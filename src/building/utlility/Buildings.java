package building.utlility;

import building.decorators.SynchronizedFloor;
import building.facrotries.DwellingFactory;
import building.interfaces.Building;
import building.interfaces.BuildingFactory;
import building.interfaces.Floor;
import building.interfaces.Space;

import java.io.*;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Scanner;

public class Buildings {
    /**
     * Запись данных о здании в байтовый поток
     * public static void outputBuilding (Building building, OutputStream out);
     */
    private static BuildingFactory factory = new DwellingFactory();

    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        DataOutputStream oout = new DataOutputStream(out);
        oout.writeInt(building.getFloorCount());
        for (int i = 0; i < building.getFloorCount(); i++) {
            oout.writeInt(building.getFloor(i).getSpaceCount());
            for (int j = 0; j < building.getFloor(i).getSpaceCount(); j++) {
                oout.writeInt(building.getFloor(i).getSpace(j).getRoomAmount());
                oout.writeInt(building.getFloor(i).getSpace(j).getSquare());
            }
        }
    }

    /**
     * Чтение данных о здании из байтового потока
     * public static Building inputBuilding (InputStream in);
     */
    public static Building inputBuilding(InputStream in) throws IOException {
        DataInputStream iin = new DataInputStream(in);
        int floorAmount = iin.readInt();
        Floor[] bufFloor = new Floor[floorAmount];
        int flatOnFloor = 0;
        for (int i = 0; i < floorAmount; i++) {
            flatOnFloor = iin.readInt();
            Space[] flats = new Space[flatOnFloor];
            for (int j = 0; j < flatOnFloor; j++) {
                flats[j] = createSpace(iin.readInt(), iin.readInt());
            }
            bufFloor[i] = createFloor(flats);
        }
        return createBuilding(bufFloor);
    }

    /**
     * Запись здания в символьный поток
     * public static void writeBuilding (Building building, Writer out);
     */

    public static void writeBuilding(Building building, Writer out) throws IOException {
        String bufString = building.getFloorCount() + " ";
        out.write(bufString);
        for (int i = 0; i < building.getFloorCount(); i++) {
            bufString = building.getFloor(i).getSpaceCount() + " ";
            out.append(bufString);
            for (int j = 0; j < building.getFloor(i).getSpaceCount(); j++) {
                bufString = building.getFloor(i).getSpace(j).getRoomAmount() + " " + building.getFloor(i).getSpace(j).getSquare() + " ";
                out.append(bufString);
            }
        }
    }

    /**
     * Чтение здания из символьного потока
     * Использовать Tokenizer
     * public static Building readBuilding (Reader in).
     */
    public static Building readBuilding(Reader in) throws IOException {
        StreamTokenizer tmpToken = new StreamTokenizer(in);
        tmpToken.nextToken();
        int floorAmount = (int) tmpToken.nval;
        Floor[] bufFloor = new Floor[floorAmount];
        int flatOnFloor = 0;
        for (int i = 0; i < floorAmount; i++) {
            tmpToken.nextToken();
            flatOnFloor = (int) tmpToken.nval;
            Space[] flats = new Space[flatOnFloor];
            for (int j = 0; j < flatOnFloor; j++) {
                tmpToken.nextToken();
                int buf = (int) tmpToken.nval;
                tmpToken.nextToken();
                flats[j] = createSpace(buf, (int) tmpToken.nval);
            }
            bufFloor[i] = createFloor(flats);
        }
        return createBuilding(bufFloor);
    }

    public static void serializeBuilding(Building building, OutputStream out) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(building);
    }

    public static Building deserializeBuilding(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream ios = new ObjectInputStream(in);
        return (Building) ios.readObject();
    }

    public static void writeBuildingFormat(Building building, Writer out) throws IOException {
        Formatter f = new Formatter();
        f.format("%d ", building.getFloorCount());
        for (int i = 0; i < building.getFloorCount(); i++) {
            f.format("%d ", building.getFloor(i).getSpaceCount());
            for (int j = 0; j < building.getFloor(i).getSpaceCount(); j++) {
                f.format("%d %d ", building.getFloor(i).getSpace(j).getRoomAmount(), building.getFloor(i).getSpace(j).getSquare());
            }
        }
    }

    public static Building readBuilding(Scanner scnr) {
        int floorAmount = scnr.nextInt();
        Floor[] bufFloor = new Floor[floorAmount];
        int flatOnFloor = 0;
        for (int i = 0; i < floorAmount; i++) {
            flatOnFloor = scnr.nextInt();
            Space[] flats = new Space[flatOnFloor];
            for (int j = 0; j < flatOnFloor; j++) {
                int buf = scnr.nextInt();
                flats[j] = createSpace(buf, scnr.nextInt());
            }
            bufFloor[i] = createFloor(flats);
        }
        return createBuilding(bufFloor);
    }

    public static <T extends Comparable<T>> T[] sort(T[] objs) {
        int i, j;
        T newValue;
        for (i = 1; i < objs.length; i++) {
            newValue = objs[i];
            j = 1;
            while (j > 0 && objs[j - 1].compareTo(newValue) < 0) {
                objs[j] = objs[j - 1];
                j--;
            }
            objs[j] = newValue;
        }
        return objs;
    }

    public static <T> T[] sort(T[] objs, Comparator<T> comparator) {
        int i, j;
        T newValue;
        for (i = 1; i < objs.length; i++) {
            newValue = objs[i];
            j = 1;
            while (j > 0 && comparator.compare(objs[j - 1], newValue) < 0) {
                objs[j] = objs[j - 1];
                j--;
            }
            objs[j] = newValue;
        }
        return objs;
    }


    public static void setFactory(BuildingFactory factory) {
        Buildings.factory = factory;
    }

    public static Space createSpace(int area) {
        return factory.createSpace(area);
    }

    public static Space createSpace(int roomsCount, int area) {
        return factory.createSpace(roomsCount, area);
    }

    public static Floor createFloor(int spaceCount) {
        return factory.createFloor(spaceCount);
    }

    public static Floor createFloor(Space[] spaces) {
        return factory.createFloor(spaces);
    }

    public static Building createBuilding(Floor[] floors) {
        return factory.createBuilding(floors);
    }

    public static Building createBuilding(int floorsCount, int[] spaceCounts) {
        return factory.createBuilding(floorsCount, spaceCounts);
    }

    public static Floor synchronizedFloor(Floor floor) {
        return new SynchronizedFloor(floor);
    }
}
