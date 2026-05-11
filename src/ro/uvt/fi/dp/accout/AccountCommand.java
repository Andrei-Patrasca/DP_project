package ro.uvt.fi.dp.accout;

/**
 * Command pattern interface.
 * Each concrete command encapsulates one operation on an Account
 * and knows how to reverse itself (undo).
 */
public interface AccountCommand {
    void execute();
    void undo();
    String getLog();
}
