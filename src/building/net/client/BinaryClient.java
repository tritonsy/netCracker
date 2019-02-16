package building.net.client;

import building.facrotries.DwellingFactory;
import building.facrotries.HotelFactory;
import building.facrotries.OfficeFactory;
import building.interfaces.Building;
import building.utlility.Buildings;

import java.io.*;
import java.net.Socket;

public class BinaryClient {

    public static void main(String[] args) throws InterruptedException {
        Socket fromserver = null;

        System.out.println("Client");
        try {
            fromserver = new Socket("localhost", 4444);
        } catch (IOException e) {
            System.out.println("Could not connect to the server");
            System.exit(-1);
        }
        System.out.println("Client connected");
        try {
            Building bufBuilding = null;
            BufferedReader readerFile2 = new BufferedReader(new FileReader(args[1]));
            FileReader readerFile1 = new FileReader(args[0]);
            DataInputStream readerFromSocket = new DataInputStream(fromserver.getInputStream());
            PrintWriter writerToSocket = new PrintWriter(fromserver.getOutputStream());
            String buildingType;
            while ((buildingType = readerFile2.readLine()) != null) {
                boolean finished = false;
                switch (buildingType) {
                    case "Dwelling":
                        Buildings.setFactory(new DwellingFactory());
                        bufBuilding = Buildings.readBuilding(readerFile1);
                        writerToSocket.println("Dwelling");
                        break;
                    case "OfficeBuilding":
                        Buildings.setFactory(new OfficeFactory());
                        bufBuilding = Buildings.readBuilding(readerFile1);
                        writerToSocket.println("OfficeBuilding");
                        break;
                    case "Hotel":
                        Buildings.setFactory(new HotelFactory());
                        bufBuilding = Buildings.readBuilding(readerFile1);
                        writerToSocket.println("Hotel");
                        break;
                    default:
                        writerToSocket.print("end");
                        finished = true;
                        break;
                }
                if (!finished) Buildings.writeBuilding(bufBuilding, writerToSocket);
                writerToSocket.println();
            }
            writerToSocket.flush();
            System.out.println("Info sent to server");
            //Thread.sleep(1000);
            BufferedWriter writerFile3 = new BufferedWriter((new FileWriter(args[2])));
            int bufReader;
            while ((bufReader = readerFromSocket.readInt()) != -255) {
                System.out.println(bufReader);
                if (bufReader != -1) {
                    writerFile3.append("" + bufReader);
                    writerFile3.append('$');
                    writerFile3.append('\n');
                } else {
                    writerFile3.append("Building is arrested");
                    writerFile3.append('\n');
                }
            }
            writerFile3.close();
            System.out.println("Info got from the server");
            writerToSocket.close();
            readerFromSocket.close();
            fromserver.close();
        } catch (FileNotFoundException e) {
            System.out.println("File is missing");
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("File info is damaaged");
            System.exit(-1);
        }
    }


}
