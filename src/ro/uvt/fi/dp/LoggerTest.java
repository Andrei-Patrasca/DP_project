package ro.uvt.fi.dp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoggerTest {

    @Test
    void testSingletonReturnsSameInstance() {
        Logger a = Logger.getInstance();
        Logger b = Logger.getInstance();
        assertSame(a, b, "Both calls must return the identical singleton instance");
    }

    @Test
    void testInstanceIsNotNull() {
        assertNotNull(Logger.getInstance());
    }

    @Test
    void testLogDoesNotThrow() {
        assertDoesNotThrow(() -> Logger.getInstance().log("JUnit test log entry"));
    }
}
