import java.util.Scanner;

/**
 * Main application class.
 * Handles user interaction (Menu) and delegates logic to HospitalService.
 * Updated to use Automatic ID Generation.
 */
public class HospitalManagementApp {
    private static HospitalService service = new HospitalService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Initializing Hospital Management System...");

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1: handlePersonnelMenu();
                break;
                case 2: handlePatientMenu();
                break;
                case 3: handleInventoryMenu();
                break;
                case 4: handleVendorMenu();
                break;
                case 5:
                    service.showLogs();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Personnel Management");
        System.out.println("2. Patient Management");
        System.out.println("3. Inventory Management");
        System.out.println("4. Vendor Management");
        System.out.println("5. View System Logs");
        System.out.println("6. Exit & Save");
        System.out.print("Enter choice: ");
    }

    private static void handlePersonnelMenu() {
        System.out.println("\n--- Personnel Menu ---");
        System.out.println("1. Add New Staff Member");
        System.out.println("2. Display All Personnel");
        System.out.print("Choice: ");
        int choice = getIntInput();

        if (choice == 1) {
            System.out.println("Select Staff Type: 1. Doctor, 2. Nurse, 3. Other");
            int typeChoice = getIntInput();

            // Auto-generate ID based on type
            String id;
            String category = "Staff";
            String role = "";
            String specialization = "N/A";
            String license = "N/A";
            String dept = "N/A";
            String shift = "N/A";

            System.out.print("Name: "); String name = scanner.nextLine();
            System.out.print("Salary: "); double salary = getDoubleInput();

            if (typeChoice == 1) {
                id = service.getNextDoctorId();
                category = "Doctor";
                System.out.print("Specialization: "); specialization = scanner.nextLine();
                System.out.print("License #: "); license = scanner.nextLine();
                System.out.print("Specific Role (e.g., Surgeon): "); role = scanner.nextLine();
            } else if (typeChoice == 2) {
                id = service.getNextNurseId();
                category = "Nurse";
                System.out.print("Department: "); dept = scanner.nextLine();
                System.out.print("Shift Timing: "); shift = scanner.nextLine();
                System.out.print("Specific Role (e.g., Head Nurse): "); role = scanner.nextLine();
            } else {
                id = service.getNextStaffId();
                System.out.print("Category Name: "); category = scanner.nextLine();
                System.out.print("Role: "); role = scanner.nextLine();
            }

            Personnel p = new Personnel(id, name, salary, category, role, specialization, license, dept, shift);
            service.addPersonnel(p);
            System.out.println(">> Success! Assigned ID: " + id);
            service.saveEmployeeData();

        } else if (choice == 2) {
            service.displayAllPersonnel();
        }
    }

    private static void handlePatientMenu() {
        System.out.println("\n--- Patient Menu ---");
        System.out.println("1. Admit New Patient");
        System.out.println("2. Update Admission Status");
        System.out.println("3. Display All Patients");
        System.out.print("Choice: ");
        int choice = getIntInput();

        if (choice == 1) {
            // Auto-generate Patient ID
            String id = service.getNextPatientId();

            System.out.print("Name: "); String name = scanner.nextLine();
            System.out.print("Age: "); int age = getIntInput();
            System.out.print("Blood Type: "); String blood = scanner.nextLine();
            System.out.print("Initial History: "); String hist = scanner.nextLine();

            service.addPatient(new Patient(id, name, age, blood, hist, "Admitted"));
            System.out.println(">> Success! Assigned ID: " + id);
            service.savePatientData();

        } else if (choice == 2) {
            System.out.print("Patient ID: "); String id = scanner.nextLine();
            System.out.print("New Status (Admitted/Discharged): "); String st = scanner.nextLine();
            service.updatePatientStatus(id, st);
            service.savePatientData();
        } else if (choice == 3) {
            service.displayPatients();
        }
    }

    private static void handleInventoryMenu() {
        HospitalInventorySystem myInventoryModule = new HospitalInventorySystem();
        myInventoryModule.runInventoryMenu(scanner); 
    }

    private static void handleVendorMenu() {
        System.out.println("\n--- Vendor Menu ---");
        System.out.println("1. Add Vendor");
        System.out.println("2. Display Vendors");
        System.out.print("Choice: ");
        int choice = getIntInput();

        if (choice == 1) {
            // Auto-generate Vendor ID
            String id = service.getNextVendorId();

            System.out.print("Name: "); String name = scanner.nextLine();
            System.out.print("Contact Person: "); String cp = scanner.nextLine();
            System.out.print("Phone: "); String ph = scanner.nextLine();
            System.out.print("Address: "); String addr = scanner.nextLine();

            service.addVendor(new Vendor(id, name, cp, ph, addr));
            System.out.println(">> Success! Assigned ID: " + id);
            service.saveVendorData();

        } else if (choice == 2) {
            service.displayVendors();
        }
    }

    // Helper methods for robust input
    private static int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static double getDoubleInput() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }



}