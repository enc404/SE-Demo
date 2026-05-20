public class Equipment extends InventoryItem {
    private static final long serialVersionUID = 1L;
    private String manufacturer;
    private boolean requiresMaintenance;
    public Equipment(String itemId, String name, int quantity, double pricePerUnit, int threshold, String manufacturer, boolean requiresMaintenance) {
        super(itemId, name, quantity, pricePerUnit, threshold);
        this.manufacturer = manufacturer;
        this.requiresMaintenance = requiresMaintenance;
    }
    @Override
    public String getCategory() {
        return "Equipment";
    }
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("    -> Mfr: " + manufacturer + ", Maintenance Req: " + (requiresMaintenance ? "Yes" : "No"));
    }
}