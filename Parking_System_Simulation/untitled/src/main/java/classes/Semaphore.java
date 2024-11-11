package classes;

public class Semaphore {
    protected int value = 0 ;
    protected Semaphore() {
        value = 0 ;
    }
    protected Semaphore(int initial) {
        value = initial ;
    }
    public synchronized void Wait(Car car) throws InterruptedException {
        value-- ;
        if (value < 0)
            try {
                System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " waiting for a spot.");
                wait();
            } catch(InterruptedException e) {}
    }
    public synchronized void Notify() {
        value++ ;
        if (value <= 0) notify();
    }
}