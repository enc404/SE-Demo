import java.io.Serial;
import java.io.Serializable;

/**
 * Abstract base class representing a generic employee.
 * Implements Serializable for easy file persistence.
 */
public abstract class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String employeeID;
    private String name;
    private String jobTitle;
    private double salary; // Private as per requirements

    public Employee(String employeeID, String name, String jobTitle, double salary) {
        this.employeeID = employeeID;
        this.name = name;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    // Abstract method for polymorphism
    public abstract void displayInfo();

    // Getters and Setters with Encapsulation
    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        } else {
            System.out.println("Error: Salary cannot be negative.");
        }
    }
}