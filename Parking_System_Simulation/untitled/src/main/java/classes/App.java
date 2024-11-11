package classes;


import java.util.ArrayList;

public class App
{
    public static void main(String[] args) {
        System.out.println("* Welcome to Multithreaded Parking System Simulation *");
        System.out.println("______________________________________________________\n");

        ParkingLot parkingLot = new ParkingLot(4);

        Parser parser = new Parser("Parking_System_Simulation/untitled/InputFile.txt");
        ArrayList<Car> cars = parser.parse(parkingLot);

        for (Car car : cars) {
            // car.print();
            // System.out.println(car.getCarID());

            car.start();

        }

        // Wait for each car thread to finish
        for (Car car : cars) {
            try {
//                System.out.println(car.getCarID());
                car.join();
//                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        parkingLot.details();
        ArrayList<Gate> gates = parser.getGates();
        System.out.println("Details:");
        for (Gate gate: gates) {
            System.out.println("- Gate " + gate.getGateId() + " served " + gate.getCarsServed() + " cars.");
        }
    }
}
