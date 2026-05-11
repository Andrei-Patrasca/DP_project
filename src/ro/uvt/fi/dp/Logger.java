package ro.uvt.fi.dp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Singleton pattern: only one Logger instance exists in the application.
 * Writes log entries both to stdout and to a persistent log file.
 */
public class Logger implements Serializable {

    private static final long serialVersionUID = 1L;
    private static volatile Logger instance;
    private static final String LOG_FILE = "bank_app.log";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Logger() {}

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void log(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logEntry = String.format("[%s] %s", timestamp, message);
        System.out.println(logEntry);
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(logEntry + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Logger failed to write to file: " + e.getMessage());
        }
    }
}
