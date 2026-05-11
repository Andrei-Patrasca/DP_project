package ro.uvt.fi.dp.accout;

import ro.uvt.fi.dp.Logger;

/**
 * Factory pattern: centralises account creation.
 * Client code calls AccountFactory.createAccount() rather than new Account().
 */
public class AccountFactory {

    private AccountFactory() {} // utility class — no instances

    public static Account createAccount(String accountCode, double initialAmount, Account.TYPE type) {
        Logger.getInstance().log("AccountFactory: creating " + type + " account [" + accountCode + "]");
        return new Account(accountCode, initialAmount, type);
    }
}
