package building.net.server.sequental;

import building.dwelling.Dwelling;
import building.dwelling.hotel.Hotel;
import building.exceptions.BuildingUnderArrestException;
import building.interfaces.Building;
import building.office.OfficeBuilding;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SerialServer {
    private static String getCost(Building building) {
        if (building instanceof Dwelling) return "" + building.getSpace() * 1000;
        else if (building instanceof OfficeBuilding) return "" + building.getSpace() * 1500;
        else if (building instanceof Hotel) return "" + building.getSpace() * 2000;
        else return "" + -1;
    }

    private static void checkArrest() throws BuildingUnderArrestException {
        double k = Math.random();
        System.out.println(k);
        if (k < 0.1) {
            throw new BuildingUnderArrestException("Building is arrested");
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Serial sequental server");
        try (ServerSocket server = new ServerSocket(4444)) {
            while (true) {
                Socket socket = server.accept();
                System.out.println("Client accepted");
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                Building building;
                try {
                    while ((building = (Building) in.readObject()) != null) {
                        System.out.println(building);
                        String cost;
                        try {
                            checkArrest();
                            cost = getCost(building);
                        } catch (BuildingUnderArrestException e) {
                            cost = "Building under arrest";
                        }
                        System.out.println(cost);
                        writer.println(cost);
                        writer.flush();
                        Thread.sleep(1000);
                    }
                } catch (EOFException e) {
                    in.close();
                    writer.close();
                    socket.close();
                    System.out.println("Client disconnected");
                }
            }
        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }


}
