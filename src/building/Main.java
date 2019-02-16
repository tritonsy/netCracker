package building;

import building.dwelling.Dwelling;
import building.dwelling.DwellingFloor;
import building.interfaces.Building;
import building.threads.Semaphore;
import building.threads.SequentalCleaner;
import building.threads.SequentalRepairer;
import building.utlility.Buildings;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
/**         Flat[] flats = new Flat[5];
 for (int i = 0; i < 5; i++) flats[i] = new Flat();
 System.out.println("--------------Exercise 1--------------");
 Flat[] flats1 = new Flat[5];
 flats1[0] = new Flat(1, 30);
 flats1[1] = new Flat(2, 45);
 flats1[2] = new Flat(60);
 flats1[3] = new Flat();
 flats1[4] = new Flat();
 for(int i = 0; i < 4; i++) {
 System.out.println("#" + i + " " + flats1[i].toString());
 }
 System.out.println("Amount of rooms in flat #3 before change = " + flats1[3].getRoomAmount());
 flats1[3].setRoomAmount(7);
 System.out.println("Amount of rooms in flat #3 after change = " + flats1[3].getRoomAmount());
 System.out.println("Square of flat #3 before change = " + flats1[3].getSquare());
 flats1[3].setSquare(130);
 System.out.println("Square of flat #3 after change = " + flats1[3].getSquare());
 System.out.println();
 System.out.println("--------------Exercise 2--------------");
 DwellingFloor[] floors = new DwellingFloor[3];
 floors[0] = new DwellingFloor(flats);
 floors[1] = new DwellingFloor(flats1);
 floors[2] = new DwellingFloor(3);
 for(int i = 0; i < 3; i++) {
 System.out.println("#" + i +" " + floors[i]);
 }
 System.out.println("Amount of flats on dwelling floor #2 = " + floors[2].getSpaceCount());
 System.out.println("Total square on floor #2 = " + floors[2].getSquare());
 System.out.println("Amount of rooms on dwelling floor #2 = " + floors[2].getRoomCount());
 System.out.println("Array of rooms on floor #2 = " + floors[2].toString());
 System.out.println("Info about flat #1 on floor #1 before change = " + floors[1].getSpace(1));
 floors[1].setSpace(1,new Flat(5,160));
 System.out.println("Info about flat #1 on floor #1 after change = " + floors[1].getSpace(1));
 floors[1].addSpace(3, new Flat(6,250));
 System.out.println("Array of rooms on floor #2 after adding = " + floors[1].toString());
 floors[1].delSpace(0);
 System.out.println("Array of rooms on floor #2 after deleting = " + floors[1].toString());
 System.out.println("The best flat on floor #1 = " + floors[1].getBestSpace());
 System.out.println();
 System.out.println("--------------Exercise 3--------------");
 Dwelling[] dwellings = new Dwelling[3];
 dwellings[0] = new Dwelling(4,3,4,5,2);
 //dwellings[2] = new Dwelling(4);
 System.out.println(dwellings[0]);
 dwellings[1] = new Dwelling(floors);
 System.out.println(dwellings[1]);
 dwellings[1].delSpace(7);
 System.out.println(dwellings[1]);
 System.out.println("Amount of flats in Dwelling #0 = "+dwellings[0].getSpaceCount());
 dwellings[1].getSortedSpaces(); **/
        /**     System.out.println("--------------Exercise 1--------------");
         Office[] offices = new Office[4];
         offices[0] = new Office(1, 30);
         offices[1] = new Office(2, 45);
         offices[2] = new Office(60);
         offices[3] = new Office();
         OfficeFloor[] officeFloors = new OfficeFloor[3];
         officeFloors[0] = new OfficeFloor(offices);
         officeFloors[1] = new OfficeFloor(5);
         officeFloors[2] = new OfficeFloor(3);
         OfficeBuilding[] officeDwellings = new OfficeBuilding[1];
         officeDwellings[0] = new OfficeBuilding(officeFloors);
         System.out.println(officeDwellings[0]);**/
        Building Test = new Dwelling(3, 3, 4, 5, 2);
        /**try {
         FileWriter TestWriter = new FileWriter("1.txt");
         Buildings.writeBuilding(Test, TestWriter);
         System.out.println(Test.toString());
         } catch (IOException e) {
         e.printStackTrace();
         }**/
        /**   try {
         ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("out.ser"));
         Buildings.serializeBuilding(Test, out);
         out.close();
         }
         catch (IOException e){
         e.printStackTrace();
         }
         try {
         ObjectInputStream in = new ObjectInputStream(new FileInputStream("out.ser"));
         System.out.println(Buildings.deserializeBuilding(in).toString());
         in.close();
         }

         catch (IOException e){
         e.printStackTrace();
         }
         try {
         FileWriter TestWriter = new FileWriter("2.txt");
         Buildings.writeBuildingFormat(Test,TestWriter);
         System.out.println(Test.toString());
         }

         catch (IOException e){
         e.printStackTrace();
         }**/
        //    try {
        //        FileReader TestWriter = new FileReader("1.txt");
        //        System.out.println(Buildings.readBuilding(TestWriter));
        //    }

        //    catch (IOException e){
        //        e.printStackTrace();
        //    }
        //       Scanner in = new Scanner(System.in);
        //     System.out.println(Buildings.readBuilding(in).toString());
        // BuildingFactory dwlFac = new OfficeFactory();
        // Buildings.setFactory(dwlFac);
        DwellingFloor testD = new DwellingFloor(9);
        System.out.println(testD.toString());
        try {
            DwellingFloor testClone = (DwellingFloor) testD.clone();
            System.out.println(testClone.toString());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        int[] a = {1, 2, 3};
        int[] a11 = {3, 4, 5};
        Building a1 = Buildings.createBuilding(3, a);
        System.out.println(a1.toString());
        Building a3 = Buildings.createBuilding(3, a11);
        Semaphore semaphore = new Semaphore();
        //SynchronizedFloor sflr = new SynchronizedFloor(a1.getFloor(2));
        //Floor sflr =(a3.getFloor(2));
        SequentalRepairer r1 = new SequentalRepairer(a1.getFloor(2), semaphore);
        SequentalCleaner l1 = new SequentalCleaner(a1.getFloor(2), semaphore);
        // SequentalRepairer r2 = new SequentalRepairer(a3.getFloor(2), semaphore);
        new Thread(r1).start();
        new Thread(l1).start();


    }
}
