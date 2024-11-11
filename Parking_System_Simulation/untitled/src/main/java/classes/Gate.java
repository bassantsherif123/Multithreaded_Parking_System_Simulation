package classes;

import java.util.ArrayList;
import java.util.List;

public class Gate extends Thread {
    private final int gateId;
    private final ParkingLot parkingLot;
    private List<Car> cars;
    private int carsServed = 0;

    public Gate(int gateId, ParkingLot parkingLot) {
        this.gateId = gateId;
        this.parkingLot = parkingLot;
        this.cars = new ArrayList<Car>();
    }

    public int getGateId() {
        return gateId;
    }

//    @Override
//    public void run() {
//        for (Car car : cars) {
//            System.out.println("Car " + car.getId() + " from Gate " + gateId + " is scheduled.");
//            car.start();
//
//            try {
//                car.join();
//                carsServed++;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void add(Car car) {
        cars.add(car);
        carsServed++;
    }

    public int getCarsServed() {
        return carsServed;
    }
}
