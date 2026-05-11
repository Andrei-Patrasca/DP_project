package ro.uvt.fi.dp.accout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommandPatternTest {

    private Account account;
    private TransactionManager manager;

    @BeforeEach
    void setUp() {
        account = AccountFactory.createAccount("CMD-ACC", 500.0, Account.TYPE.RON);
        manager = new TransactionManager();
    }

    @Test
    void testDeposeCommandExecute() {
        manager.executeCommand(new DeposeCommand(account, 200.0));
        assertEquals(700.0, account.getAmount(), 0.001);
    }

    @Test
    void testDeposeCommandUndo() {
        manager.executeCommand(new DeposeCommand(account, 200.0));
        manager.undoLastCommand();
        assertEquals(500.0, account.getAmount(), 0.001, "Undo should restore original balance");
    }

    @Test
    void testRetrieveCommandExecute() {
        manager.executeCommand(new RetrieveCommand(account, 100.0));
        assertEquals(400.0, account.getAmount(), 0.001);
    }

    @Test
    void testRetrieveCommandUndo() {
        manager.executeCommand(new RetrieveCommand(account, 100.0));
        manager.undoLastCommand();
        assertEquals(500.0, account.getAmount(), 0.001, "Undo should restore original balance");
    }

    @Test
    void testMultipleCommandsAndUndo() {
        manager.executeCommand(new DeposeCommand(account, 200.0));   // 700
        manager.executeCommand(new RetrieveCommand(account, 100.0)); // 600
        manager.undoLastCommand(); // undo retrieve -> back to 700
        assertEquals(700.0, account.getAmount(), 0.001);
    }

    @Test
    void testUndoOnEmptyStackDoesNotThrow() {
        assertDoesNotThrow(() -> manager.undoLastCommand());
    }

    @Test
    void testHistoryLog() {
        manager.executeCommand(new DeposeCommand(account, 50.0));
        manager.executeCommand(new RetrieveCommand(account, 20.0));
        assertEquals(2, manager.getHistoryLog().size());
    }
}
