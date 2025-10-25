// LibraryManagement.java
import java.util.Scanner;

class Book {
    String title;
    String author;
    boolean isIssued;

    // Constructor
    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    // Method to issue a book
    void issueBook() {
        if (!isIssued) {
            isIssued = true;
            System.out.println("Book issued successfully!");
        } else {
            System.out.println("Sorry! This book is already issued.");
        }
    }

    // Method to return a book
    void returnBook() {
        if (isIssued) {
            isIssued = false;
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("This book was not issued.");
        }
    }

    // Method to display book details
    void displayBook() {
        System.out.println("Title: " + title + ", Author: " + author + ", Status: " + (isIssued ? "Issued" : "Available"));
    }
}

public class LibraryManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create an array of books
        Book[] library = new Book[100];
        int count = 0; // number of books
        int choice;

        do {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author Name: ");
                    String author = sc.nextLine();
                    library[count] = new Book(title, author);
                    count++;
                    System.out.println("Book added successfully!");
                }

                case 2 -> {
                    if (count == 0) {
                        System.out.println("No books available.");
                    } else {
                        System.out.println("\nBooks in Library:");
                        for (int i = 0; i < count; i++) {
                            library[i].displayBook();
                        }
                    }
                }

                case 3 -> {
                    System.out.print("Enter title of book to issue: ");
                    String title = sc.nextLine();
                    boolean found = false;
                    for (int i = 0; i < count; i++) {
                        if (library[i].title.equalsIgnoreCase(title)) {
                            library[i].issueBook();
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("Book not found.");
                }

                case 4 -> {
                    System.out.print("Enter title of book to return: ");
                    String title = sc.nextLine();
                    boolean found = false;
                    for (int i = 0; i < count; i++) {
                        if (library[i].title.equalsIgnoreCase(title)) {
                            library[i].returnBook();
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("Book not found.");
                }

                case 5 -> {
                    System.out.print("Enter title to search: ");
                    String title = sc.nextLine();
                    boolean found = false;
                    int i = 0;
                    // using while loop here
                    while (i < count) {
                        if (library[i].title.equalsIgnoreCase(title)) {
                            System.out.println("Book found!");
                            library[i].displayBook();
                            found = true;
                            break;
                        }
                        i++;
                    }
                    if (!found) System.out.println("Book not found.");
                }

                case 6 -> System.out.println("Exiting system...");

                default -> System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 6); // using do-while loop

        sc.close();
    }
}
