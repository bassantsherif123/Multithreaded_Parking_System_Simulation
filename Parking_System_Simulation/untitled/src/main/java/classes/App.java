package classes;


import java.util.ArrayList;

public class App
{
    public static void main(String[] args) {
        System.out.println("* Welcome to Multithreaded Parking System Simulation *");
        System.out.println("______________________________________________________\n");

        // Initialize the parking lot with a maximum of 4 parking spots
        ParkingLot parkingLot = new ParkingLot(4);

        // Create a parser to read the input file for car and gate information
        Parser parser = new Parser("Parking_System_Simulation/untitled/InputFile.txt");

        // Parse the cars data from the input file and assign them to the parking lot
        ArrayList<Car> cars = parser.parse(parkingLot);

        // Start each car as a separate thread
        for (Car car : cars) {
            car.start();
        }

        // Wait for each car thread to finish
        for (Car car : cars) {
            try {
                car.join(); // Wait for the car thread to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print the details of the parking lot after all cars have finished
        parkingLot.details();

        // Get the list of gates from the parser
        ArrayList<Gate> gates = parser.getGates();

        // Print the details of each gate (how many cars it has served)
        System.out.println("Details:");
        for (Gate gate: gates) {
            System.out.println("- Gate " + gate.getGateId() + " served " + gate.getCarsServed() + " cars.");
        }
    }
}
