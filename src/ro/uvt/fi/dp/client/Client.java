package ro.uvt.fi.dp.client;

import ro.uvt.fi.dp.Logger;
import ro.uvt.fi.dp.accout.Account;
import ro.uvt.fi.dp.accout.AccountFactory;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Represents a bank client.
 *
 * Builder pattern: use Client.Builder for flexible construction with optional fields.
 * Factory pattern: account creation is delegated to AccountFactory.
 */
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int MAX_ACCOUNTS_NO = 5;

    private String name;
    private String address;
    private final Account[] accounts;
    private int accountsNo = 0;

    /** Standard constructor — used when all fields are known upfront. */
    public Client(String name, String address, Account.TYPE type, String accountCode, double amount) {
        this.name = name;
        this.address = address;
        this.accounts = new Account[MAX_ACCOUNTS_NO];
        addAccount(type, accountCode, amount);
        Logger.getInstance().log("Client created: " + name);
    }

    /** Builder constructor — used internally by Client.Builder. */
    protected Client(Builder builder) {
        this.name = builder.name;
        this.address = builder.address;
        this.accounts = new Account[MAX_ACCOUNTS_NO];
        Logger.getInstance().log("Client created via Builder: " + this.name);
    }

    // -------------------------------------------------------------------------
    // Builder pattern
    // -------------------------------------------------------------------------

    /**
     * Builder for Client. Name is mandatory; address and initial account are optional.
     */
    public static class Builder {
        private final String name; // mandatory
        private String address;

        public Builder(String name) {
            this.name = name;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }

    // -------------------------------------------------------------------------
    // Account management
    // -------------------------------------------------------------------------

    public void addAccount(Account.TYPE type, String accountCode, double amount) {
        if (accountsNo < MAX_ACCOUNTS_NO) {
            accounts[accountsNo++] = AccountFactory.createAccount(accountCode, amount, type);
            Logger.getInstance().log("Account " + accountCode + " added for client " + name);
        }
    }

    public Account getAccount(String accountCode) {
        for (int i = 0; i < accountsNo; i++) {
            if (accounts[i].getAccountCode().equals(accountCode)) {
                return accounts[i];
            }
        }
        return null;
    }

    /** Returns a copy-safe view of the active accounts (no nulls). */
    public Account[] getAccounts() {
        return Arrays.copyOf(accounts, accountsNo);
    }

    // -------------------------------------------------------------------------
    // Accessors
    // -------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "\n\tClient [name=" + name + ", address=" + address +
                ", accounts=" + Arrays.toString(Arrays.copyOf(accounts, accountsNo)) + "]";
    }
}
