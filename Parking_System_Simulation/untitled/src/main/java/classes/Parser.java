package classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    String inputFile;  // Path to the input file that contains car and gate data
    ArrayList<Gate> gates;

    // Constructor to initialize the parser with the input file path
    public Parser(String file) {
        this.inputFile = file;
        gates = new ArrayList<>();
    }

    public ArrayList<Car> parse(ParkingLot parkingLot) {
        // Create cars ArrayList to store car data in
        ArrayList<Car> cars = new ArrayList<>();
        Gate g1 = new Gate(1, parkingLot);
        Gate g2 = new Gate(2, parkingLot);
        Gate g3 = new Gate(3, parkingLot);

        // Add Gates to list
        gates.add(g1);
        gates.add(g2);
        gates.add(g3);

        // Create a buffer reader
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.trim().split(", ");

                // Parse the values for gate ID, car ID, arrival time, and parking duration
                int gateID = Integer.parseInt(parts[0].split(" ")[1]);
                int carID = Integer.parseInt(parts[1].split(" ")[1]);
                int arriveTime = Integer.parseInt(parts[2].split(" ")[1]);
                int parkingDuration = Integer.parseInt(parts[3].split(" ")[1]);

                Gate gate = null;
                // Assign the gate object based on the gate ID
                switch (gateID) {
                    case 1:
                        gate = g1;
                        break;
                    case 2:
                        gate = g2;
                        break;
                    case 3:
                        gate = g3;
                        break;
                }

                // If a valid gate is found, create a Car object and add it to the gates and cars list
                if (gate != null) {
                    Car car = new Car(gate, carID, arriveTime, parkingDuration, parkingLot);
                    gate.add(car);
                    cars.add(car);
                }

            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        catch (IOException e) {
            System.out.println(e);
        }

        return cars;
    }

    // getter
    public String getInputFilePath() {
        return inputFile;
    }

    public ArrayList<Gate> getGates() {
        return gates;
    }
}
