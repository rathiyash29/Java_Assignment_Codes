// Assignment 6 (Movie Ticket Booking System)

package ticket;

import java.util.InputMismatchException;
import java.util.Scanner;

class TicketCounter {
    private int availableTickets;

    public TicketCounter(int totalTickets) {
        if (totalTickets < 0) throw new IllegalArgumentException("Total tickets cannot be negative");
        this.availableTickets = totalTickets;
    }

    // synchronized ensures only one thread books at a time (prevents overbooking)
    public synchronized void bookTicket(String userName, int numberOfTickets) {
        try {
            System.out.println(userName + " is trying to book " + numberOfTickets + " ticket(s).");

            // Simulate processing delay
            Thread.sleep(500);

            if (numberOfTickets <= 0) {
                System.out.println("❌ " + userName + ": number of tickets must be positive.");
                return;
            }

            if (numberOfTickets <= availableTickets) {
                availableTickets -= numberOfTickets;
                System.out.println("✅ " + userName + " successfully booked " + numberOfTickets + " ticket(s).");
                System.out.println("🎟 Tickets left: " + availableTickets);
            } else {
                System.out.println("❌ Sorry " + userName + ", not enough tickets available.");
            }

        } catch (InterruptedException e) {
            // Restore interrupt status and report gracefully
            Thread.currentThread().interrupt();
            System.out.println("⚠ " + userName + "'s booking was interrupted.");
        } catch (Exception e) {
            System.out.println("⚠ Unexpected error for " + userName + ": " + e.getMessage());
        }
    }
}

// Thread subclass example
class UserThread extends Thread {
    private final TicketCounter counter;
    private final String userName;
    private final int ticketsToBook;

    public UserThread(TicketCounter counter, String userName, int ticketsToBook) {
        this.counter = counter;
        this.userName = userName;
        this.ticketsToBook = ticketsToBook;
    }

    @Override
    public void run() {
        counter.bookTicket(userName, ticketsToBook);
    }
}

// Runnable implementation example
class UserRunnable implements Runnable {
    private final TicketCounter counter;
    private final String userName;
    private final int ticketsToBook;

    public UserRunnable(TicketCounter counter, String userName, int ticketsToBook) {
        this.counter = counter;
        this.userName = userName;
        this.ticketsToBook = ticketsToBook;
    }

    @Override
    public void run() {
        counter.bookTicket(userName, ticketsToBook);
    }
}

public class MovieTicketBookingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter total number of tickets available: ");
            int totalTickets = sc.nextInt();
            sc.nextLine(); // consume newline
            TicketCounter counter = new TicketCounter(totalTickets);

            System.out.print("Enter number of users: ");
            int numUsers = sc.nextInt();
            sc.nextLine();

            if (numUsers <= 0) {
                System.out.println("No users to process. Exiting.");
                return;
            }

            Thread[] threads = new Thread[numUsers];

            for (int i = 0; i < numUsers; i++) {
                System.out.print("Enter name of user " + (i + 1) + ": ");
                String name = sc.nextLine().trim();
                if (name.isEmpty()) name = "User" + (i + 1);

                System.out.print("Enter number of tickets " + name + " wants to book: ");
                int tickets = sc.nextInt();
                sc.nextLine();

                // Alternate between Thread and Runnable to demonstrate both
                if (i % 2 == 0) {
                    threads[i] = new UserThread(counter, name, tickets);
                } else {
                    threads[i] = new Thread(new UserRunnable(counter, name, tickets));
                }
            }

            for (Thread t : threads) t.start();

            for (Thread t : threads) {
                try {
                    t.join();
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    System.out.println("⚠ Main thread interrupted while waiting for bookings to complete.");
                }
            }

            System.out.println("✅ All booking attempts processed.");

        } catch (InputMismatchException e) {
            System.out.println("⚠ Invalid input! Please enter numeric values where required.");
        } catch (IllegalArgumentException e) {
            System.out.println("⚠ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("⚠ Unexpected error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}