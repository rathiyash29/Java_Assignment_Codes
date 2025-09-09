// Assignment 1 (Final Library code)

package data;

import java.util.Scanner;

class Book {
    String title;
    String author;
    boolean isIssued;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public void issue() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }

    public String getStatus() {
        return isIssued ? "Issued" : "Available";
    }
}

public class LibraryManagement {
    static final int MAX_BOOKS = 100;
    static Book[] books = new Book[MAX_BOOKS];
    static int bookCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addBook(sc);
                case 2 -> listBooks();
                case 3 -> searchBook(sc);
                case 4 -> issueBook(sc);
                case 5 -> returnBook(sc);
                case 6 -> removeBook(sc);
                case 7 -> checkAvailability(sc);
                case 8 -> System.out.println("Exiting system...");
                default -> System.out.println("Invalid option. Try again.");
            }

        } while (choice != 8);

        sc.close();
    }

    static void displayMenu() {
        System.out.println("\n===== Library Menu =====");
        System.out.println("1. Add Book");
        System.out.println("2. List Books");
        System.out.println("3. Search Book");
        System.out.println("4. Issue Book");
        System.out.println("5. Return Book");
        System.out.println("6. Remove Book");
        System.out.println("7. Check Availability");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    static void addBook(Scanner sc) {
        if (bookCount >= MAX_BOOKS) {
            System.out.println("Library is full. Can't add more books.");
            return;
        }

        System.out.print("Enter book title: ");
        String title = sc.nextLine().trim();
        System.out.print("Enter book author: ");
        String author = sc.nextLine().trim();

        books[bookCount++] = new Book(title, author);
        System.out.println("Book added successfully.");
    }

    static void listBooks() {
        if (bookCount == 0) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\nList of Books:");
        for (int i = 0; i < bookCount; i++) {
            System.out.println((i + 1) + ". " + books[i].title + " by " + books[i].author + " [" + books[i].getStatus() + "]");
        }
    }

    static void searchBook(Scanner sc) {
        System.out.print("Enter title to search: ");
        String searchTitle = sc.nextLine().trim();
        boolean found = false;

        for (Book book : books) {
            if (book == null) continue;

            if (book.title.equalsIgnoreCase(searchTitle)) {
                System.out.println("Found: " + book.title + " by " + book.author + " [" + book.getStatus() + "]");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    static void issueBook(Scanner sc) {
        System.out.print("Enter title to issue: ");
        String issueTitle = sc.nextLine().trim();
        boolean found = false;

        for (Book book : books) {
            if (book == null) continue;

            if (book.title.equalsIgnoreCase(issueTitle)) {
                if (!book.isIssued) {
                    book.issue();
                    System.out.println("Book issued successfully.");
                } else {
                    System.out.println("Book is already issued.");
                }
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    static void returnBook(Scanner sc) {
        System.out.print("Enter title to return: ");
        String returnTitle = sc.nextLine().trim();
        boolean found = false;

        for (Book book : books) {
            if (book == null) continue;

            if (book.title.equalsIgnoreCase(returnTitle)) {
                if (book.isIssued) {
                    book.returnBook();
                    System.out.println("Book returned successfully.");
                } else {
                    System.out.println("Book was not issued.");
                }
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    static void removeBook(Scanner sc) {
        System.out.print("Enter title to remove: ");
        String removeTitle = sc.nextLine().trim();
        boolean removed = false;

        for (int i = 0; i < bookCount; i++) {
            if (books[i].title.equalsIgnoreCase(removeTitle)) {
                // Shift books to the left
                for (int j = i; j < bookCount - 1; j++) {
                    books[j] = books[j + 1];
                }
                books[bookCount - 1] = null;
                bookCount--;
                removed = true;
                System.out.println("Book removed successfully.");
                break;
            }
        }

        if (!removed) {
            System.out.println("Book not found.");
        }
    }

    static void checkAvailability(Scanner sc) {
        System.out.print("Enter title to check: ");
        String checkTitle = sc.nextLine().trim();
        boolean found = false;

        int i = 0;
        while (i < bookCount) {
            if (books[i].title.equalsIgnoreCase(checkTitle)) {
                System.out.println("Book is " + books[i].getStatus());
                found = true;
                break;
            }
            i++;
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }
}