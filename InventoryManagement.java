// Assignment 7 (Inventory Management)

package inventory;

import java.util.*;

class Product {
    private static int counter = 1; // auto-generate ID
    private int id;
    private String name;
    private int quantity;

    public Product(String name, int quantity) {
        this.id = counter++;
        this.name = name;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + name + " (Qty: " + quantity + ")";
    }
}

public class InventoryManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Product> inventoryArray = new ArrayList<>();
        LinkedList<Product> inventoryLinked = new LinkedList<>();
        HashSet<String> inventoryHash = new HashSet<>(); // product names only
        TreeSet<String> inventoryTree = new TreeSet<>(); // sorted names only

        int choice;
        do {
            System.out.println("\n=== Inventory Management System ===");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Show Inventory");
            System.out.println("4. Search Item");
            System.out.println("5. Update Quantity");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();

                    Product newProduct = new Product(newName, qty);
                    inventoryArray.add(newProduct);
                    inventoryLinked.add(newProduct);
                    inventoryHash.add(newName);
                    inventoryTree.add(newName);
                    System.out.println("✅ " + newName + " added with ID " + newProduct.getId());
                    break;

                case 2:
                    System.out.print("Enter product name to remove: ");
                    String removeName = sc.nextLine();

                    inventoryArray.removeIf(p -> p.getName().equalsIgnoreCase(removeName));
                    inventoryLinked.removeIf(p -> p.getName().equalsIgnoreCase(removeName));
                    inventoryHash.remove(removeName);
                    inventoryTree.remove(removeName);

                    System.out.println("🗑️ " + removeName + " removed from inventory (if existed).");
                    break;

                case 3:
                    System.out.println("\n--- Current Inventory ---");
                    System.out.println("ArrayList: " + inventoryArray);
                    System.out.println("LinkedList: " + inventoryLinked);
                    System.out.println("HashSet (names only): " + inventoryHash);
                    System.out.println("TreeSet (sorted names): " + inventoryTree);
                    break;

                case 4:
                    System.out.print("Enter product name to search: ");
                    String searchName = sc.nextLine();
                    boolean found = false;
                    for (Product p : inventoryArray) {
                        if (p.getName().equalsIgnoreCase(searchName)) {
                            System.out.println("🔍 Found: " + p);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("❌ Product not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter product name to update: ");
                    String updateName = sc.nextLine();
                    for (Product p : inventoryArray) {
                        if (p.getName().equalsIgnoreCase(updateName)) {
                            System.out.print("Enter new quantity: ");
                            int newQty = sc.nextInt();
                            p.setQuantity(newQty);
                            System.out.println("✅ Quantity updated: " + p);
                        }
                    }
                    break;

                case 6:
                    System.out.println("Exiting Inventory Management System...");
                    break;

                default:
                    System.out.println("⚠️ Invalid choice! Try again.");
            }
        } while (choice != 6);

        sc.close();
    }
}