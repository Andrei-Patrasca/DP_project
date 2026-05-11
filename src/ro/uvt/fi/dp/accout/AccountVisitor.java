package ro.uvt.fi.dp.accout;

/**
 * Visitor pattern interface.
 * Implementations can inspect Account data without modifying Account itself.
 */
public interface AccountVisitor {
    void visit(Account account);
}
