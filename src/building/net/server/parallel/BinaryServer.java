package building.net.server.parallel;

import building.exceptions.BuildingUnderArrestException;
import building.facrotries.DwellingFactory;
import building.facrotries.HotelFactory;
import building.facrotries.OfficeFactory;
import building.interfaces.Building;
import building.utlility.Buildings;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class BinaryServer {

    private ServerSocket server;

    private static int getCost(Building building, String buildType) {
        switch (buildType) {
            case "Dwelling":
                return building.getSpace() * 1000;
            case "OfficeBuilding":
                return building.getSpace() * 1500;
            case "Hotel":
                return building.getSpace() * 2000;
        }
        return -1;
    }

    private static void checkArrest() throws BuildingUnderArrestException {
        double k = Math.random();
        System.out.println(k);
        if (k < 0.1) {
            throw new BuildingUnderArrestException("Building is arrested");
        }
    }

    public static void main(String[] args) {
        System.out.println("Parallel server");
        try (ServerSocket server = new ServerSocket(4444)) {
            while (true) {
                Socket fromClient = server.accept();
                new Thread(new MonoServerThread(fromClient)).start();
            }
        } catch (IOException e) {

        }
    }

    static class MonoServerThread implements Runnable {
        Socket fromClient;

        public MonoServerThread(Socket accepted) {
            fromClient = accepted;
        }

        @Override
        public void run() {
            try {
                Building bufBuilding = null;
                System.out.println("Client connected");
                DataOutputStream socketWriter = new DataOutputStream(fromClient.getOutputStream());
                Scanner socketReader = new Scanner(fromClient.getInputStream());
                String buildType;
                while (!(buildType = socketReader.next()).contains("end")) {
                    System.out.println(buildType);
                    switch (buildType) {
                        case "Dwelling":
                            Buildings.setFactory(new DwellingFactory());
                            break;
                        case "OfficeBuilding":
                            Buildings.setFactory(new OfficeFactory());
                            break;
                        case "Hotel":
                            Buildings.setFactory(new HotelFactory());
                            break;
                    }
                    if (!buildType.contains("end")) {
                        bufBuilding = Buildings.readBuilding(socketReader);
                        try {
                            checkArrest();
                            socketWriter.writeInt(getCost(bufBuilding, buildType));
                        } catch (BuildingUnderArrestException e) {
                            System.out.println("Building got arrested");
                            socketWriter.writeInt(-1);
                        }
                        System.out.println(bufBuilding);
                        System.out.println(getCost(bufBuilding, buildType));
                    }
                }
                socketWriter.writeInt(-255);
                //System.out.println(-255);
                socketWriter.flush();
                System.out.println("Info sent to client");
                socketReader.close();
                socketWriter.close();
                fromClient.close();
                System.out.println("Client disconnected");

            } catch (IOException e) {

            }
        }
    }

}