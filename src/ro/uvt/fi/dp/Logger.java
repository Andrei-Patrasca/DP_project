package ro.uvt.fi.dp;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class

public class Logger {
    // Private static instance of the logger
    private static Logger instance;

    // Private constructor to prevent external instantiation
    private Logger() {
        // Initialization code, if any
    }

    // Static method to provide access to the instance
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }

        return instance;
    }

    // Logging method
    public void log(String message) {
        System.out.println("Log: " + message);

        try {
            FileWriter myWriter = new FileWriter("filename.txt", true);
            myWriter.write("Log: " + message + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
