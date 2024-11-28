package classes;

public class Car extends Thread{
    private Gate gate;
    private final int carID; // Unique identifier for each car
    private final int arriveTime;
    private final int parkingDuration;
    private final ParkingLot parkingLot;

    // Constructor
    public Car(Gate gate, int car, int arrive, int duration, ParkingLot parkingLot){
        this.gate = gate;
        this.carID = car;
        this.arriveTime = arrive;
        this.parkingDuration = duration;
        this.parkingLot = parkingLot;
    }

    @Override
    public void run(){
        // Simulate the car arriving at the parking lot after a delay (arriveTime)
        try {
            Thread.sleep(arriveTime * 1000); // Sleep to simulate the car's arrival time
            parkingLot.enter(this); // Once the car arrives, it enters the parking lot
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Display data
    public void print(){
        System.out.println("Gate "+ getGateID() +", Car " + carID + ", Arrive " + arriveTime + ", Parks " + parkingDuration);
    }

    // getters
    public int getGateID(){
        return gate.getGateId();
    }

    public int getCarID(){
        return carID;
    }

    public int getArriveTime(){
        return arriveTime;
    }

    public int getParkDuration(){
        return parkingDuration;
    }
}
