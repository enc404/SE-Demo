import java.io.Serial;
import java.io.Serializable;

/**
 * Vendor class for supply chain management.
 */
public class Vendor implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String vendorID;
    private String vendorName; // Private
    private String contactPerson; // Private
    private String phoneNumber;
    private String address; // Private

    public Vendor(String vendorID, String vendorName, String contactPerson, String phoneNumber, String address) {
        this.vendorID = vendorID;
        this.vendorName = vendorName;
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public void displayInfo() {
        System.out.println("Vendor [" + vendorID + "] " + vendorName);
        System.out.println("Contact: " + contactPerson + " (" + phoneNumber + ")");
        System.out.println("Address: " + address);
    }

    // Getters and Setters
    public String getVendorID() { return vendorID; }

    public String getVendorName() { return vendorName; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }

    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}