package classes;

import java.util.ArrayList;
import java.util.List;

public class Gate extends Thread {
    private final int gateId;
    private final ParkingLot parkingLot;
    private List<Car> cars;
    private int carsServed = 0;

    // Constructor to initialize the gate with its ID and associated parking lot
    public Gate(int gateId, ParkingLot parkingLot) {
        this.gateId = gateId;
        this.parkingLot = parkingLot;
        this.cars = new ArrayList<Car>();
    }

    // Getter for the gate's ID
    public int getGateId() {
        return gateId;
    }

    // Method to add a car to the list of cars served by this gate
    public void add(Car car) {
        cars.add(car);
        carsServed++;
    }

    // Getter for the number of cars served by this gate
    public int getCarsServed() {
        return carsServed;
    }
}
