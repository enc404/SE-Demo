import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logging class to record system activities.
 * Represents a single log entry.
 */
public class Logging implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String logID;
    private String timestamp; // Private
    private String action; // ADD, UPDATE, DELETE
    private String entityType; // Doctor, Patient, etc.
    private String entityID;
    private String details; // Private

    public Logging(String logID, String action, String entityType, String entityID, String details) {
        this.logID = logID;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.action = action;
        this.entityType = entityType;
        this.entityID = entityID;
        this.details = details;
    }

    public void displayLog() {
        System.out.println(String.format("[%s] %s on %s (%s): %s",
                timestamp, action, entityType, entityID, details));
    }

    public String getTimestamp() { return timestamp; }
    public String getEntityType() { return entityType; }
}