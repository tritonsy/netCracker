package building.threads;

import building.interfaces.Floor;
import building.interfaces.Space;

public class SequentalRepairer implements Runnable {
    private Floor floor;
    private Semaphore semaphore;

    public SequentalRepairer(Floor floor, Semaphore semaphore) {
        this.floor = floor;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        Space[] spaces = floor.getSpaces();
        System.out.println(floor.getSpaceCount());
        for (int i = 0; i < floor.getSpaceCount(); i++) {
            try {
                semaphore.beginRepair();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Repairing space number " + i + " with total area "
                    + spaces[i].getSquare() + " square meters");
            semaphore.endRepair();
        }
    }
}
