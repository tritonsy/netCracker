package building.threads;

public class Semaphore {

    private boolean repaired = false;

    public synchronized void beginClean()
            throws InterruptedException {
        while (!repaired)
            wait();
    }

    public synchronized void endClean() {
        repaired = false;
        notifyAll();
    }

    public synchronized void beginRepair()
            throws InterruptedException {
        while (repaired)
            wait();
    }

    public synchronized void endRepair() {
        repaired = true;
        notifyAll();
    }
}
