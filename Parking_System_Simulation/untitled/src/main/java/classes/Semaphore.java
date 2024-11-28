package classes;

public class Semaphore {
    protected int value = 0 ;

    // Default constructor sets the semaphore's value to 0 (no available resources)
    protected Semaphore() {
        value = 0 ;
    }

    // Default constructor sets the semaphore's value to 0 (no available resources)
    protected Semaphore(int initial) {
        value = initial ;
    }

    // The Wait method is called by a car when it tries to acquire a parking spot
    public synchronized void Wait(Car car) throws InterruptedException {
        value-- ;
        // If no spots are available (value < 0), the car has to wait
        if (value < 0)
            try {
                System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " waiting for a spot.");
                wait(); // Make the car wait until a spot is available (value becomes positive)
            } catch(InterruptedException e) {}
    }

    // The Notify method is called when a car leaves the parking lot and a spot becomes available
    public synchronized void Notify() {
        value++ ;
        // If there are any cars waiting, notify one of them that a spot is available
        if (value <= 0) notify();
    }
}