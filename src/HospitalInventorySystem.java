import java.util.Scanner;
public class HospitalInventorySystem {
    public void runInventoryMenu(Scanner scanner) {
        InventoryManager manager = new InventoryManager();
        while (true) {
            System.out.println("\n=== HOSPITAL INVENTORY SYSTEM ===");
            System.out.println("1. Add Medicine");
            System.out.println("2. Add Equipment");
            System.out.println("3. Update Stock Quantity");
            System.out.println("4. View All Inventory");
            System.out.println("5. Check Low Stock");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter choice: ");
            int choice;
            try {
                String input = scanner.nextLine();
                if (input.isEmpty()) continue;
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    String mId = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String mName = scanner.nextLine();
                    System.out.print("Enter Quantity: ");
                    int mQty = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Price: ");
                    double mPrice = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter Low Stock Threshold: ");
                    int mThresh = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Expiry Date (YYYY-MM-DD): ");
                    String mExpiry = scanner.nextLine();
                    System.out.print("Enter Dosage: ");
                    String mDose = scanner.nextLine();
                    manager.addItem(new Medicine(mId, mName, mQty, mPrice, mThresh, mExpiry, mDose));
                    break;
                case 2:
                    System.out.print("Enter ID: ");
                    String eId = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String eName = scanner.nextLine();
                    System.out.print("Enter Quantity: ");
                    int eQty = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Price: ");
                    double ePrice = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter Low Stock Threshold: ");
                    int eThresh = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Manufacturer: ");
                    String eMan = scanner.nextLine();
                    System.out.print("Requires Maintenance? (true/false): ");
                    boolean eMaint = Boolean.parseBoolean(scanner.nextLine());
                    manager.addItem(new Equipment(eId, eName, eQty, ePrice, eThresh, eMan, eMaint));
                    break;
                case 3:
                    System.out.print("Enter Item ID to update: ");
                    String uId = scanner.nextLine();
                    System.out.print("Enter new quantity: ");
                    try {
                        int uQty = Integer.parseInt(scanner.nextLine());
                        manager.updateStock(uId, uQty);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid quantity.");
                    }
                    break;
                case 4:
                    manager.displayAllItems();
                    break;
                case 5:
                    manager.checkLowStock();
                    break;
                case 6:
                    System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}