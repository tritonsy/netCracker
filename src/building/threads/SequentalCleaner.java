package building.threads;

import building.interfaces.Floor;
import building.interfaces.Space;

public class SequentalCleaner implements Runnable {

    private Floor floor;
    private Semaphore semaphore;

    public SequentalCleaner(Floor floor, Semaphore semaphore) {
        this.floor = floor;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        Space[] spaces = floor.getSpaces();
        for (int i = 0; i < spaces.length; i++) {
            try {
                semaphore.beginClean();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Cleaning space number " + i + " with total area " + spaces[i]
                    .getSquare() + " square meters");
            semaphore.endClean();
        }
    }
}
