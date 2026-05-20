public class Medicine extends InventoryItem {
    private static final long serialVersionUID = 1L;
    private String expiryDate;
    private String dosage;
    public Medicine(String itemId, String name, int quantity, double pricePerUnit, int threshold, String expiryDate, String dosage) {
        super(itemId, name, quantity, pricePerUnit, threshold);
        this.expiryDate = expiryDate;
        this.dosage = dosage;
    }
    @Override
    public String getCategory() {
        return "Medicine";
    }
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("    -> Expiry: " + expiryDate + ", Dosage: " + dosage);
    }
}