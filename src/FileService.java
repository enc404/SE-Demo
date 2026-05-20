import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Generic Utility class for handling File I/O using Java Serialization.
 * Updated to save data into a specific 'SaveData' directory.
 */
public class FileService {

    // Define the directory path
    private static final String DATA_DIR = "SaveData";

    public static <T> void saveData(String filename, List<T> data) {
        // Create a File object for the directory
        File directory = new File(DATA_DIR);

        // Check if directory exists, if not, create it
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("Created directory: " + DATA_DIR);
            }
        }

        // Create the file object inside the SaveData directory
        File file = new File(directory, filename);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(data);
            System.out.println("Data saved to " + file.getPath());
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> loadData(String filename) {
        // Construct the full path using the directory constant
        File file = new File(DATA_DIR, filename);

        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data from " + file.getPath() + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
}