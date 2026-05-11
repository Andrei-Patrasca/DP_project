package ro.uvt.fi.dp.accout;

/**
 * Command pattern: encapsulates a withdrawal operation.
 * undo() reverses it by depositing the same amount back.
 */
public class RetrieveCommand implements AccountCommand {

    private final Account account;
    private final double amount;

    public RetrieveCommand(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.retrieve(amount);
    }

    @Override
    public void undo() {
        account.depose(amount);
    }

    @Override
    public String getLog() {
        return "WITHDRAWAL: -" + amount + " from " + account.getAccountCode();
    }
}
