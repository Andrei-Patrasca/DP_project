package ro.uvt.fi.dp.accout;

/**
 * Command pattern: encapsulates a deposit operation.
 * undo() reverses it by retrieving the same amount.
 */
public class DeposeCommand implements AccountCommand {

    private final Account account;
    private final double amount;

    public DeposeCommand(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.depose(amount);
    }

    @Override
    public void undo() {
        account.retrieve(amount);
    }

    @Override
    public String getLog() {
        return "DEPOSIT: +" + amount + " to " + account.getAccountCode();
    }
}
