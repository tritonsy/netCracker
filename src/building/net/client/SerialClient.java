package building.net.client;

import building.facrotries.DwellingFactory;
import building.facrotries.HotelFactory;
import building.facrotries.OfficeFactory;
import building.interfaces.Building;
import building.utlility.Buildings;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SerialClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 4444);
             Scanner buildingScanner = new Scanner(new FileReader(args[0]));
             Scanner typeScanner = new Scanner(new FileReader(args[1]));
             Scanner scanner = new Scanner(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             PrintWriter costWriter = new PrintWriter(new FileWriter(args[2]))) {
            while (buildingScanner.hasNextLine()) {
                String type = typeScanner.nextLine();
                System.out.println(type);
                switch (type) {
                    case "Dwelling":
                        Buildings.setFactory(new DwellingFactory());
                        break;
                    case "Office":
                        Buildings.setFactory(new OfficeFactory());
                        break;
                    case "Hotel":
                        Buildings.setFactory(new HotelFactory());
                        break;
                }
                Building building = Buildings.readBuilding(buildingScanner);
                out.writeObject(building);
                System.out.println(building);
                out.flush();
                Thread.sleep(1000);
                String cost;
                if (scanner.hasNextLine()) {
                    cost = scanner.nextLine();
                    System.out.println(cost);
                    costWriter.println(cost);
                    costWriter.flush();
                } else {
                    System.out.println("Building is under arrest");
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
