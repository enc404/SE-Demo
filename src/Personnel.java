import java.io.Serial;

/**
 * Personnel class representing all hospital staff (Doctors, Nurses, etc.).
 * Combines previous Doctor and Nurse functionality into one flexible class.
 */
public class Personnel extends Employee {
    @Serial
    private static final long serialVersionUID = 1L;

    // New variables as requested
    private String staffCategory; // e.g., "Doctor", "Nurse", "Admin"
    private String specificRole;  // e.g., "Chief Surgeon", "Head Nurse"

    // Integrated fields from Doctor
    private String specialization;
    private String licenseNumber;

    // Integrated fields from Nurse
    private String department;
    private String shiftTiming;

    /**
     * Comprehensive constructor for all personnel types.
     * Fields not applicable to a specific category can be passed as null or "N/A".
     */
    public Personnel(String employeeID, String name, double salary,
                     String staffCategory, String specificRole,
                     String specialization, String licenseNumber,
                     String department, String shiftTiming) {

        // Pass staffCategory as jobTitle to parent Employee class for compatibility,
        // or we could use specificRole depending on preference.
        super(employeeID, name, staffCategory, salary);

        this.staffCategory = staffCategory;
        this.specificRole = specificRole;
        this.specialization = (specialization == null) ? "N/A" : specialization;
        this.licenseNumber = (licenseNumber == null) ? "N/A" : licenseNumber;
        this.department = (department == null) ? "N/A" : department;
        this.shiftTiming = (shiftTiming == null) ? "N/A" : shiftTiming;
    }

    @Override
    public void displayInfo() {
        System.out.println("=== Personnel Details ===");
        System.out.println("ID: " + getEmployeeID());
        System.out.println("Name: " + getName());
        System.out.println("Category: " + staffCategory);
        System.out.println("Role: " + specificRole);
        System.out.println("Salary: $" + getSalary());

        // Conditionally display details based on category or content
        if (!"N/A".equals(specialization) || !"N/A".equals(licenseNumber)) {
            System.out.println("Specialization: " + specialization);
            System.out.println("License #: " + licenseNumber);
        }

        if (!"N/A".equals(department) || !"N/A".equals(shiftTiming)) {
            System.out.println("Department: " + department);
            System.out.println("Shift: " + shiftTiming);
        }
    }

    // Getters and Setters for all fields
    public String getStaffCategory() { return staffCategory; }
    public void setStaffCategory(String staffCategory) { this.staffCategory = staffCategory; }

    public String getSpecificRole() { return specificRole; }
    public void setSpecificRole(String specificRole) { this.specificRole = specificRole; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getShiftTiming() { return shiftTiming; }
    public void setShiftTiming(String shiftTiming) { this.shiftTiming = shiftTiming; }
}