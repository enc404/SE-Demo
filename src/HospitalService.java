import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

/**
 * Central Service class handling business logic.
 * MODIFIED: Inventory logic commented out to delegate to the new Inventory Module.
 */
public class HospitalService {
    // In-memory storage
    private List<Employee> employees;
    private List<Patient> patients;
    // private List<InventoryItem> inventory; // DISABLED: Managed by InventoryManager
    private List<Vendor> vendors;
    private List<Logging> logs;

    // File constants
    private final String EMP_FILE = "employees.dat";
    private final String PAT_FILE = "patients.dat";
    // private final String INV_FILE = "inventory.dat"; // DISABLED
    private final String VEN_FILE = "vendors.dat";
    private final String LOG_FILE = "logs.dat";

    public HospitalService() {
        // Load data on startup
        this.employees = FileService.loadData(EMP_FILE);
        this.patients = FileService.loadData(PAT_FILE);
        // this.inventory = FileService.loadData(INV_FILE); // DISABLED
        this.vendors = FileService.loadData(VEN_FILE);
        this.logs = FileService.loadData(LOG_FILE);
    }

    public void saveEmployeeData() {
        FileService.saveData(EMP_FILE, employees);
        FileService.saveData(LOG_FILE, logs);
    }

    public void savePatientData() {
        FileService.saveData(PAT_FILE, patients);
        FileService.saveData(LOG_FILE, logs);
    }

    /* DISABLED: Inventory saving is now handled by InventoryManager inside your module
    public void saveInventoryData() {
        FileService.saveData(INV_FILE, inventory);
        FileService.saveData(LOG_FILE, logs);
    }
    */

    public void saveVendorData() {
        FileService.saveData(VEN_FILE, vendors);
        FileService.saveData(LOG_FILE, logs);
    }


    // --- ID GENERATION LOGIC ---

    private <T> String generateNextId(List<T> list, String prefix, Function<T, String> idExtractor) {
        int maxId = 0;

        for (T item : list) {
            String currentId = idExtractor.apply(item);
            if (currentId != null && currentId.startsWith(prefix)) {
                try {
                    String numericPart = currentId.substring(prefix.length());
                    int idNum = Integer.parseInt(numericPart);
                    if (idNum > maxId) {
                        maxId = idNum;
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
        return prefix + String.format("%03d", maxId + 1);
    }

    // Public wrappers for ID generation
    public String getNextDoctorId() {
        return generateNextId(employees, "D", Employee::getEmployeeID);
    }

    public String getNextNurseId() {
        return generateNextId(employees, "N", Employee::getEmployeeID);
    }

    public String getNextStaffId() {
        return generateNextId(employees, "S", Employee::getEmployeeID);
    }

    public String getNextPatientId() {
        return generateNextId(patients, "P", Patient::getPatientID);
    }

    /* DISABLED: Module currently handles IDs manually or internally
    public String getNextInventoryId() {
        return generateNextId(inventory, "I", InventoryItem::getItemID);
    }
    */

    public String getNextVendorId() {
        return generateNextId(vendors, "V", Vendor::getVendorID);
    }


    // --- LOGGING ---
    private void createLog(String action, String type, String id, String details) {
        String logId = UUID.randomUUID().toString().substring(0, 8);
        Logging log = new Logging(logId, action, type, id, details);
        logs.add(log);
        System.out.println("Log recorded.");
    }

    public void showLogs() {
        System.out.println("\n=== SYSTEM LOGS ===");
        for (Logging l : logs) l.displayLog();
    }

    // --- PERSONNEL MANAGEMENT ---
    public void addPersonnel(Personnel p) {
        employees.add(p);
        String desc = "Added " + p.getStaffCategory() + " " + p.getName();
        createLog("ADD", "Personnel", p.getEmployeeID(), desc);
    }

    public void displayAllPersonnel() {
        System.out.println("\n=== PERSONNEL LIST ===");
        for (Employee e : employees) {
            e.displayInfo();
            System.out.println("----------------");
        }
    }

    // --- PATIENTS ---
    public void addPatient(Patient p) {
        patients.add(p);
        createLog("ADD", "Patient", p.getPatientID(), "Admitted " + p.getName());
    }

    public Patient findPatient(String id) {
        return patients.stream().filter(p -> p.getPatientID().equals(id)).findFirst().orElse(null);
    }

    public void updatePatientStatus(String id, String status) {
        Patient p = findPatient(id);
        if (p != null) {
            p.setAdmissionStatus(status);
            createLog("UPDATE", "Patient", id, "Status changed to " + status);
        } else {
            System.out.println("Patient not found.");
        }
    }

    public void displayPatients() {
        System.out.println("\n=== PATIENT LIST ===");
        for (Patient p : patients) {
            p.displayInfo();
            System.out.println("----------------");
        }
    }

    // --- INVENTORY (ALL DISABLED/REMOVED) ---
    // These methods are removed because HospitalInventorySystem.java
    // and InventoryManager.java now handle all of this.
    
    /*
    public void addInventoryItem(InventoryItem item) {
        inventory.add(item);
        createLog("ADD", "Inventory", item.getItemID(), "Added " + item.getItemName());
    }

    public void updateStock(String itemId, int quantityChange) {
        InventoryItem item = inventory.stream().filter(i -> i.getItemID().equals(itemId)).findFirst().orElse(null);
        if (item != null) {
            item.updateQuantity(quantityChange);
            createLog("UPDATE", "Inventory", itemId, "Quantity changed by " + quantityChange);
        } else {
            System.out.println("Item not found.");
        }
    }

    public void checkLowStock() {
        System.out.println("\n=== LOW STOCK ALERTS ===");
        for (InventoryItem i : inventory) {
            if (i.isLowStock()) {
                System.out.println("ALERT: " + i.getItemName() + " is low on stock (" + i.getQuantity() + ")");
            }
        }
    }

    public void displayInventory() {
        System.out.println("\n=== INVENTORY STATUS ===");
        for (InventoryItem i : inventory) {
            i.displayInfo();
        }
    }
    */

    // --- VENDOR ---
    public void addVendor(Vendor v) {
        vendors.add(v);
        createLog("ADD", "Vendor", v.getVendorID(), "Added vendor " + v.getVendorName());
    }

    public void displayVendors() {
        System.out.println("\n=== VENDOR LIST ===");
        for(Vendor v : vendors) {
            v.displayInfo();
            System.out.println("----------------");
        }
    }
}