package classes;
//import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

class ParkingLot {
    private final Semaphore spots;
    private final int totalSpots;
    private int totalParked = 0;
    private int totalServed = 0;
//    Semaphore spots;

    public ParkingLot(int totalSpots) {
        this.spots = new Semaphore(totalSpots);
        this.totalSpots = totalSpots;
    }


    public void enter(Car car) throws InterruptedException {
        long waitStartTime = System.currentTimeMillis();
        boolean hadToWait = false;
        System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " arrived at time " + car.getArriveTime());
        if (spots.value <= 0) {  // Check if a spot is unavailable before waiting
            hadToWait = true;
//            System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " waiting for a spot.");
        }
        spots.Wait(car);
        synchronized (this) {
            totalParked++;
            long waitedTime = (System.currentTimeMillis() - waitStartTime) / 1000;
            if (waitedTime == 0) {
                waitedTime++;
            }
            if (hadToWait) {
                System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " parked after waiting " + waitedTime + " units of time. (Parking Status: " + totalParked + " spots occupied)");
            } else {
                System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " parked. (Parking Status: " + totalParked + " spots occupied)");
            }
        }
//        if (spots.tryAcquire()) {
//            totalParked.incrementAndGet();
//            System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " parked. (Parking Status: " + totalParked.get() + " spots occupied)");
//        } else {
//            System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " waiting for a spot.");
//            spots.acquire();  // Wait until a spot is available
//            totalParked.incrementAndGet();
//            long waitedTime = (System.currentTimeMillis() - waitStartTime) / 1000;
//            System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " parked after waiting " + waitedTime + " units of time. (Parking Status: " + totalParked.get() + " spots occupied)");
//        }

        totalServed++;
        Thread.sleep((car.getParkDuration()) * 1000 + 100);

        leaveCar(car);
    }
    public synchronized void leaveCar(Car car) {
            totalParked--;
            System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " left after " + car.getParkDuration() + " units of time. (Parking Status: " + totalParked + " spots occupied)");
            spots.Notify();
    }



    public int getTotalCarsParked() {
        return totalParked;
    }

    public int getTotalCarsServed() {
        return totalServed;
    }

    public void details() {
        System.out.println("...\nTotal Cars Served: " + getTotalCarsServed());
        System.out.println("Current Cars in Parking: " + getTotalCarsParked());
    }
}
