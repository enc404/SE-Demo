import java.io.Serializable;
public abstract class InventoryItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private String itemId;
    private String name;
    private int quantity;
    private double pricePerUnit;
    private int lowStockThreshold;
    public InventoryItem(String itemId, String name, int quantity, double pricePerUnit, int lowStockThreshold) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.lowStockThreshold = lowStockThreshold;
    }
    public String getItemId() {
        return itemId;
    }
    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            System.out.println("Error: Quantity cannot be negative.");
        } else {
            this.quantity = quantity;
        }
    }
    public double getPricePerUnit() {
        return pricePerUnit;
    }
    public int getLowStockThreshold() {
        return lowStockThreshold;
    }
    public abstract String getCategory();
    public void displayInfo() {
        System.out.printf("ID: %-5s | Name: %-20s | Qty: %-5d | Price: LKR %-8.2f | Type: %s%n", 
            itemId, name, quantity, pricePerUnit, getCategory());
    }
}