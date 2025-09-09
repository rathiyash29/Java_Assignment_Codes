// Assignment 5 (Final railway)
//User: yash
//Pass: yash123

package railway;

import java.util.*;

abstract class Booking {
    String from, to, trainName, quota;
    int passengers;
    double price;

    abstract void bookTicket();
}

class TrainBooking extends Booking {
    static Scanner sc = new Scanner(System.in);

    // Train database
    static class Train {
        int number;
        String name;
        String time;
        double baseFare;

        Train(int number, String name, String time, double baseFare) {
            this.number = number;
            this.name = name;
            this.time = time;
            this.baseFare = baseFare;
        }
    }

    static Train[] trains = {
            new Train(12951, "Rajdhani Express", "09:00 AM", 1800),
            new Train(12001, "Shatabdi Express", "01:30 PM", 1500),
            new Train(12245, "Duronto Express", "05:45 PM", 2000),
            new Train(12909, "Garib Rath Express", "08:00 PM", 900),
            new Train(22439, "Vande Bharat Express", "06:10 AM", 2200),
            new Train(22913, "Humsafar Express", "11:15 PM", 1600),
            new Train(22119, "Tejas Express", "07:00 AM", 2400),
            new Train(12051, "Jan Shatabdi Express", "02:45 PM", 1100)
    };

    public void login() {
        System.out.print("Enter Username: ");
        String user = sc.nextLine();
        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        if (user.equals("yash") && pass.equals("yash123")) {
            System.out.println("\nLogin Successful \n");
        } else {
            System.out.println("\nInvalid Credentials . Exiting...");
            System.exit(0);
        }
    }

    public void selectDestination() {
        System.out.print("Enter Departure Station: ");
        from = sc.nextLine();
        System.out.print("Enter Arrival Station: ");
        to = sc.nextLine();
    }

    public void selectTrain() {
        System.out.println("\nAvailable Trains:");
        for (int i = 0; i < trains.length; i++) {
            System.out.printf("%d. %d - %s (%s)  Base Fare: ₹%.2f\n",
                    i + 1, trains[i].number, trains[i].name, trains[i].time, trains[i].baseFare);
        }

        System.out.print("\nSelect Train Number: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice >= 1 && choice <= trains.length) {
            Train selected = trains[choice - 1];
            trainName = selected.number + " - " + selected.name + " (" + selected.time + ")";
            price = selected.baseFare;
        } else {
            System.out.println("Invalid Train Selection ❌");
            System.exit(0);
        }
    }

    public void selectQuota() {
        System.out.println("\nSelect Quota:");
        System.out.println("1. General (No extra charge)");
        System.out.println("2. Tatkaal (+₹400)");
        System.out.println("3. Ladies (-10% discount)");
        System.out.println("4. Senior Citizen (-20% discount)");
        System.out.println("5. Defence (-15% discount)");
        System.out.println("6. Premium Tatkaal (+₹800)");

        System.out.print("\nEnter Choice: ");
        int q = sc.nextInt();
        sc.nextLine();

        switch (q) {
            case 1 -> quota = "General";
            case 2 -> { quota = "Tatkaal"; price += 400; }
            case 3 -> { quota = "Ladies"; price *= 0.90; }
            case 4 -> { quota = "Senior Citizen"; price *= 0.80; }
            case 5 -> { quota = "Defence"; price *= 0.85; }
            case 6 -> { quota = "Premium Tatkaal"; price += 800; }
            default -> quota = "General";
        }
    }

    public void enterPassengers() {
        System.out.print("Enter Number of Passengers: ");
        passengers = sc.nextInt();
        sc.nextLine();
    }

    @Override
    public void bookTicket() {
        double totalFare = passengers * price;

        System.out.println("\n====== Booking Confirmed ======");
        System.out.println("From: " + from);
        System.out.println("To: " + to);
        System.out.println("Train: " + trainName);
        System.out.println("Quota: " + quota);
        System.out.println("Passengers: " + passengers);
        System.out.println("Total Fare: ₹" + totalFare);
        System.out.println("Status: CONFIRMED ✅");
        System.out.println("===============================");
    }
}

public class RailwayReservation {
    public static void main(String[] args) {
        TrainBooking tb = new TrainBooking();
        tb.login();
        tb.selectDestination();
        tb.selectTrain();
        tb.selectQuota();
        tb.enterPassengers();
        tb.bookTicket();
    }
}