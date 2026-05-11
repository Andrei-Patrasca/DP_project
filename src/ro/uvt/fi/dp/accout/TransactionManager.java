package ro.uvt.fi.dp.accout;

import ro.uvt.fi.dp.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Command pattern: manages command execution and undo history.
 * history holds every command ever executed (for audit/display).
 * undoStack holds commands that can still be undone (LIFO order).
 */
public class TransactionManager {

    private final List<AccountCommand> history = new ArrayList<>();
    private final Stack<AccountCommand> undoStack = new Stack<>();

    public void executeCommand(AccountCommand command) {
        command.execute();
        history.add(command);
        undoStack.push(command);
        Logger.getInstance().log("Command executed: " + command.getLog());
    }

    public void undoLastCommand() {
        if (!undoStack.isEmpty()) {
            AccountCommand last = undoStack.pop();
            last.undo();
            Logger.getInstance().log("UNDO: " + last.getLog());
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    public void showHistory() {
        System.out.println("\n--- TRANSACTION HISTORY ---");
        if (history.isEmpty()) {
            System.out.println("(no transactions yet)");
        } else {
            for (int i = 0; i < history.size(); i++) {
                System.out.println((i + 1) + ". " + history.get(i).getLog());
            }
        }
        System.out.println("---------------------------\n");
    }

    public List<String> getHistoryLog() {
        List<String> logs = new ArrayList<>();
        for (AccountCommand cmd : history) {
            logs.add(cmd.getLog());
        }
        return logs;
    }
}
