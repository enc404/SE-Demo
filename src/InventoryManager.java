import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class InventoryManager {
    private static final String FILE_NAME = "inventory_data.ser";
    private List<InventoryItem> inventory;
    public InventoryManager() {
        this.inventory = loadInventory();
    }
    public void addItem(InventoryItem item) {
        inventory.add(item);
        System.out.println("Item added successfully: " + item.getName());
        saveInventory();
    }
    public void updateStock(String itemId, int newQuantity) {
        boolean found = false;
        for (InventoryItem item : inventory) {
            if (item.getItemId().equalsIgnoreCase(itemId)) {
                item.setQuantity(newQuantity);
                System.out.println("Stock updated for " + item.getName());
                found = true;
                break;
            }
        }
        if (found) {
            saveInventory();
        } else {
            System.out.println("Item not found!");
        }
    }
    public void displayAllItems() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("\n--- Current Inventory ---");
        for (InventoryItem item : inventory) {
            item.displayInfo();
            System.out.println("-------------------------");
        }
    }
    public void checkLowStock() {
        System.out.println("\n--- Low Stock Alerts ---");
        boolean found = false;
        for (InventoryItem item : inventory) {
            if (item.getQuantity() <= item.getLowStockThreshold()) {
                System.out.println("ALERT: " + item.getName() + " (ID: " + item.getItemId() + ") is below threshold! Current: " + item.getQuantity());
                found = true;
            }
        }
        if (!found) System.out.println("All stock levels are sufficient.");
    }
    private void saveInventory() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(inventory);
        } catch (IOException e) {
            System.out.println("Error saving inventory data: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    private List<InventoryItem> loadInventory() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<InventoryItem>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading inventory data: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}