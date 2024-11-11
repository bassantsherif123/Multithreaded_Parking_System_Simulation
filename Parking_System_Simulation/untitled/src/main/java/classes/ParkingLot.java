package classes;
//import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

class ParkingLot {
    private final Semaphore spots;
    private final int totalSpots;
    private final AtomicInteger totalParked = new AtomicInteger(0);
    private final AtomicInteger totalServed = new AtomicInteger(0);
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
            totalParked.incrementAndGet();
            long waitedTime = (System.currentTimeMillis() - waitStartTime) / 1000;
            if (waitedTime == 0) {
                waitedTime++;
            }
            if (hadToWait) {
                System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " parked after waiting " + waitedTime + " units of time. (Parking Status: " + totalParked.get() + " spots occupied)");
            } else {
                System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " parked. (Parking Status: " + totalParked.get() + " spots occupied)");
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

        totalServed.incrementAndGet();
        Thread.sleep((car.getParkDuration()) * 1000 + 100);

        leaveCar(car);
    }
    public synchronized void leaveCar(Car car) {
            totalParked.decrementAndGet();
            System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " left after " + car.getParkDuration() + " units of time. (Parking Status: " + totalParked.get() + " spots occupied)");
            spots.Notify();
    }



    public int getTotalCarsParked() {
        return totalParked.get();
    }

    public int getTotalCarsServed() {
        return totalServed.get();
    }

    public void details() {
        System.out.println("...\nTotal Cars Served: " + getTotalCarsServed());
        System.out.println("Current Cars in Parking: " + getTotalCarsParked());
    }
}
