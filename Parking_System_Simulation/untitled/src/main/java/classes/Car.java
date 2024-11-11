package classes;

public class Car extends Thread{
    private Gate gate;
    private final int carID;
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
        // “sleep” responds to an interrupt by exiting with an InterruptedException.
        try {
            Thread.sleep(arriveTime * 1000);
//            System.out.println("Car " + carID + " from Gate " + getGateID() + " arrived at time " + arriveTime);
            parkingLot.enter(this);

            // Simulate the time spent parked
//            Thread.sleep((parkingDuration) * 1000 - 300);
//
//            parkingLot.leaveCar(this);
            // Thread.sleep(parkingDuration * 1000);
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
