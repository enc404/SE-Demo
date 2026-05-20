import java.io.Serial;
import java.io.Serializable;

/**
 * Patient class with encapsulated medical history.
 */
public class Patient implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String patientID;
    private String name;
    private int age;
    private String bloodType;
    private String medicalHistory; // Private
    private String admissionStatus; // e.g., "Admitted", "Discharged"

    public Patient(String patientID, String name, int age, String bloodType, String medicalHistory, String admissionStatus) {
        this.patientID = patientID;
        this.name = name;
        this.age = age;
        this.bloodType = bloodType;
        this.medicalHistory = medicalHistory;
        this.admissionStatus = admissionStatus;
    }

    public void displayInfo() {
        System.out.println("--- Patient Record ---");
        System.out.println("ID: " + patientID + " | Name: " + name);
        System.out.println("Age: " + age + " | Blood Type: " + bloodType);
        System.out.println("Status: " + admissionStatus);
        // Note: Medical history is usually private, but we display it here for the record view
        System.out.println("History: " + medicalHistory);
    }

    // Encapsulated update for medical history
    public void updateMedicalHistory(String newDetails) {
        if (newDetails != null && !newDetails.isEmpty()) {
            this.medicalHistory += "; " + newDetails;
        }
    }

    // Getters and Setters
    public String getPatientID() { return patientID; }
    public void setPatientID(String patientID) { this.patientID = patientID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getBloodType() { return bloodType; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }

    public String getMedicalHistory() { return medicalHistory; }

    public String getAdmissionStatus() { return admissionStatus; }
    public void setAdmissionStatus(String admissionStatus) { this.admissionStatus = admissionStatus; }
}