package building.threads;

import building.interfaces.Floor;
import building.interfaces.Space;

import java.util.stream.IntStream;

public class Repairer extends Thread {

    private final Floor floor;

    public Repairer(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void run() {
        synchronized (floor) {
            Space[] spaces = floor.getSpaces();
            IntStream.range(0, spaces.length).forEach(i -> System.out
                    .println("Repairing space number " + i + " with total area " + spaces[i]
                            .getSquare() + " square meters"));
        }
    }
}
