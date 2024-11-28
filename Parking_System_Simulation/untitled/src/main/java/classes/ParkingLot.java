package classes;
import java.util.concurrent.atomic.AtomicInteger;

class ParkingLot {
    private final Semaphore spots;  // Semaphore to manage the available parking spots
    private final int totalSpots;
    private int totalParked = 0;
    private int totalServed = 0;

    // Constructor to initialize the parking lot with a given number of total spots
    public ParkingLot(int totalSpots) {
        this.spots = new Semaphore(totalSpots);
        this.totalSpots = totalSpots;
    }

    // Method to handle the entry of a car into the parking lot
    public void enter(Car car) throws InterruptedException {
        long waitStartTime = System.currentTimeMillis();  // Record the time when the car arrives
        boolean hadToWait = false; // Flag to check if the car had to wait for a parking spot
        System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " arrived at time " + car.getArriveTime());


        if (spots.value <= 0) {  // Check if a spot is unavailable before waiting
            hadToWait = true;
        }

        // Wait for an available spot if needed
        spots.Wait(car);

        // Synchronize the block to ensure only one thread updates the parking status at a time
        synchronized (this) {
            totalParked++;
            long waitedTime = (System.currentTimeMillis() - waitStartTime) / 1000; // Calculate the wait time in seconds
            if (waitedTime == 0) {
                waitedTime++;  // Ensure that cars don't show zero wait time if they arrive and park immediately
            }
            // Print the parking status after the car parks
            if (hadToWait) {
                System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " parked after waiting " + waitedTime + " units of time. (Parking Status: " + totalParked + " spots occupied)");
            } else {
                System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " parked. (Parking Status: " + totalParked + " spots occupied)");
            }
        }

        totalServed++;
        Thread.sleep((car.getParkDuration()) * 1000 + 100);

        // Call the method to handle the car leaving the parking lot after its parking duration
        leaveCar(car);
    }

    // Method to handle the car leaving the parking lot
    public synchronized void leaveCar(Car car) {
            totalParked--;
            System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " left after " + car.getParkDuration() + " units of time. (Parking Status: " + totalParked + " spots occupied)");
            spots.Notify();  // Notify that a parking spot is now available
    }



    public int getTotalCarsParked() {
        return totalParked;
    }

    public int getTotalCarsServed() {
        return totalServed;
    }

    // Method to print the current status of the parking lot
    public void details() {
        System.out.println("...\nTotal Cars Served: " + getTotalCarsServed());
        System.out.println("Current Cars in Parking: " + getTotalCarsParked());
    }
}
