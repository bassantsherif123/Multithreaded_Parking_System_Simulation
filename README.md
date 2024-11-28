# Multithreaded Parking System Simulation

## Project Overview

This is a **multithreaded parking system simulation** that models cars entering and leaving a parking lot with a fixed number of parking spots. The system handles concurrency using threads for each car and semaphores for managing parking spots. Gates are used for car entry, and each car is simulated with a unique arrival time and parking duration.

### Key Features:
- **Multithreading**: Each car is handled in its own thread.
- **Concurrency Control**: Semaphore is used to manage parking spots availability.
- **Gate Management**: Multiple gates serve cars arriving at the parking lot.
- **Car Details**: Tracks each car’s gate, arrival time, and parking duration.
- **Parking Lot Details**: Tracks the total number of cars parked and served.

---
## Class Descriptions

### **App**
The entry point of the program. It initializes a parking lot with 4 spots, reads the input file, creates cars, and simulates the parking process.

### **Gate**
Represents a gate where cars arrive. Each gate manages the cars that pass through and tracks how many cars it has served.

### **Car**
Represents a car that arrives at a gate, parks, and leaves after a certain duration. Each car operates in its own thread.

### **ParkingLot**
Manages the parking spots and ensures that cars can park and leave based on available spots. Uses semaphores for concurrency control.

### **Parser**
Reads and parses the input file (`InputFile.txt`). It creates cars, assigns gates to them, and prepares the data for the simulation.

### **Semaphore**
Controls access to parking spots. It uses the `Wait` method to make cars wait if no spots are available and the `Notify` method to signal when a spot is freed.
