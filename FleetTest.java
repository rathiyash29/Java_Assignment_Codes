// Assignment 4 (maintenance method)
package fleet;

import java.util.Scanner;

// 1. Interface: Vehicle
interface Vehicle {
    void start();
    void stop();
    int getSpeed();
    String getFuelType();
}

// 2. Interface: Maintenance
interface Maintenance {
    void performMaintenance();
}

// 3. Class: Car
// Implements: Vehicle, Maintenance
class Car implements Vehicle, Maintenance {
    // Instance Variables
    int speed;
    String fuelType;

    // Constructor
    Car(int speed, String fuelType) {
        this.speed = speed;
        this.fuelType = fuelType;
    }

    // Methods from Vehicle
    public void start() {
        System.out.println("Car started.");
    }

    public void stop() {
        System.out.println("Car stopped.");
    }

    public int getSpeed() {
        return speed;
    }

    public String getFuelType() {
        return fuelType;
    }

    // Methods from Maintenance
    public void performMaintenance() {
        System.out.println("Car maintenance completed.");
    }
}

// 4. Class: Bus
// Implements: Vehicle, Maintenance
class Bus implements Vehicle, Maintenance {
    // Instance Variables
    int speed;
    String fuelType;

    // Constructor
    Bus(int speed, String fuelType) {
        this.speed = speed;
        this.fuelType = fuelType;
    }

    // Methods from Vehicle
    public void start() {
        System.out.println("Bus started.");
    }

    public void stop() {
        System.out.println("Bus stopped.");
    }

    public int getSpeed() {
        return speed;
    }

    public String getFuelType() {
        return fuelType;
    }

    // Methods from Maintenance
    public void performMaintenance() {
        System.out.println("Bus maintenance completed.");
    }
}

// 5. Class: Motorcycle
// Implements: Vehicle
class Motorcycle implements Vehicle {
    // Instance Variables
    int speed;
    String fuelType;

    // Constructor
    Motorcycle(int speed, String fuelType) {
        this.speed = speed;
        this.fuelType = fuelType;
    }

    // Methods from Vehicle
    public void start() {
        System.out.println("Motorcycle started.");
    }

    public void stop() {
        System.out.println("Motorcycle stopped.");
    }

    public int getSpeed() {
        return speed;
    }

    public String getFuelType() {
        return fuelType;
    }
}

// 6. Class: FleetTest (Test class)
public class FleetTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create array of Vehicles
        Vehicle[] vehicles = new Vehicle[3];

        // Input vehicle details
        for (int i = 0; i < vehicles.length; i++) {
            System.out.println("\nEnter vehicle type (car/bus/motorcycle): ");
            String type = sc.nextLine().trim().toLowerCase();

            System.out.print("Enter speed: ");
            int speed = sc.nextInt();
            sc.nextLine(); // consume newline

            System.out.print("Enter fuel type: ");
            String fuelType = sc.nextLine();

            // Object creation using polymorphism
            switch (type) {
                case "car":
                    vehicles[i] = new Car(speed, fuelType);
                    break;
                case "bus":
                    vehicles[i] = new Bus(speed, fuelType);
                    break;
                case "motorcycle":
                    vehicles[i] = new Motorcycle(speed, fuelType);
                    break;
                default:
                    System.out.println("Invalid type, assigning default Motorcycle.");
                    vehicles[i] = new Motorcycle(50, "Petrol");
            }
        }

        // Demonstrate polymorphism & maintenance functionality
        System.out.println("\n--- Fleet Actions ---");
        for (Vehicle v : vehicles) {
            v.start();
            System.out.println("Speed: " + v.getSpeed() + " km/h");
            System.out.println("Fuel Type: " + v.getFuelType());
            v.stop();

            if (v instanceof Maintenance) {
                ((Maintenance) v).performMaintenance();
            }
            System.out.println("--------------------");
        }

        sc.close();
    }
}