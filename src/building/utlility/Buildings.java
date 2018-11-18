package building.utlility;

import building.dwelling.Dwelling;
import building.dwelling.DwellingFloor;
import building.dwelling.Flat;
import building.interfaces.Building;
import building.interfaces.Floor;
import building.interfaces.Space;

import java.io.*;
import java.util.Formatter;
import java.util.Scanner;

public class Buildings {
    /**
     * Запись данных о здании в байтовый поток
     * public static void outputBuilding (Building building, OutputStream out);
     */
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
                flats[j] = new Flat(iin.readInt(), iin.readInt());
            }
            bufFloor[i] = new DwellingFloor(flats);
        }
        Building building = new Dwelling(bufFloor);
        return building;
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
        out.close();
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
                flats[j] = new Flat(buf, (int) tmpToken.nval);
            }
            bufFloor[i] = new DwellingFloor(flats);
        }
        return new Dwelling(bufFloor);
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
        out.write(f.toString());
        out.close();
    }

    public static Building readBuilding(Scanner scanner) {
        System.out.println("Enter count of floors: ");
        int floorsCount = scanner.nextInt();
        Floor[] floors = new Floor[floorsCount];
        for (int i = 0; i < floorsCount; i++) {
            System.out.println("Enter count of flats on floor #" + i + ": ");
            int spacesCount = scanner.nextInt();
            Space[] spaces = new Space[spacesCount];
            for (int j = 0; j < spacesCount; j++) {
                System.out.println("Enter count of rooms: ");
                int rooms = scanner.nextInt();
                System.out.println("Enter square: ");
                int area = Integer.parseInt(scanner.next());
                spaces[j] = new Flat(rooms, area);
            }
            floors[i] = new DwellingFloor(spaces);
        }
        return new Dwelling(floors);
    }

}
