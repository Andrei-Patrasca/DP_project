package ro.uvt.fi.dp.client;

import ro.uvt.fi.dp.accout.Account;

/**
 * Decorator pattern for Client.
 *
 * FIX: The original version incorrectly extended Client (inheritance).
 * The proper Decorator pattern uses *composition*: we hold a reference
 * to the wrapped Client and delegate all calls to it, overriding only
 * what we want to change (getName, toString).
 *
 * This mirrors how HolidayDecorator wraps Account — same pattern, same shape.
 */
public class ClientIsVerifiedDecorator extends Client {

    private final Client decoratedClient;

    public ClientIsVerifiedDecorator(Client client) {
        // We must call super() because Client has no no-arg constructor.
        // We forward the real client's data so the parent is consistent,
        // but all actual behaviour is delegated to decoratedClient below.
        super(new Builder(client.getName()).setAddress(client.getAddress()));
        this.decoratedClient = client;
    }

    /** Appends the [VERIFIED] badge — the only thing this decorator changes. */
    @Override
    public String getName() {
        return decoratedClient.getName() + " ✓ [VERIFIED]";
    }

    @Override
    public Account getAccount(String accountCode) {
        return decoratedClient.getAccount(accountCode);
    }

    @Override
    public Account[] getAccounts() {
        return decoratedClient.getAccounts();
    }

    @Override
    public void addAccount(Account.TYPE type, String accountCode, double amount) {
        decoratedClient.addAccount(type, accountCode, amount);
    }

    @Override
    public String toString() {
        return "Verified " + decoratedClient.toString();
    }
}
