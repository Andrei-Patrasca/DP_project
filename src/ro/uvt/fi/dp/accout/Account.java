package ro.uvt.fi.dp.accout;

import ro.uvt.fi.dp.Logger;
import ro.uvt.fi.dp.Operations;
import ro.uvt.fi.dp.Transfer;

import java.io.Serializable;

/**
 * Core domain class representing a bank account.
 * Implements Operations (deposit, retrieve, interest) and Transfer.
 *
 * Factory pattern: use AccountFactory.createAccount() instead of direct construction.
 */
public class Account implements Operations, Transfer, Serializable {

    private static final long serialVersionUID = 1L;

    public enum TYPE { EUR, RON }

    String accountCode;
    double amount = 0;
    TYPE type;

    // Package-private: callers should use AccountFactory
    Account(String accountCode, double amount, TYPE type) {
        this.accountCode = accountCode;
        this.type = type;
        this.amount = amount; // direct assignment; depose() logging would fire in constructor
        Logger.getInstance().log("Account created: " + accountCode + " [" + type + "] amount=" + amount);
    }

    @Override
    public double getTotalAmount() {
        return amount + amount * getInterest();
    }

    @Override
    public void depose(double amount) {
        this.amount += amount;
        Logger.getInstance().log("Deposit: +" + amount + " on " + accountCode + " -> balance=" + this.amount);
    }

    @Override
    public void retrieve(double amount) {
        this.amount -= amount;
        Logger.getInstance().log("Retrieve: -" + amount + " on " + accountCode + " -> balance=" + this.amount);
    }

    @Override
    public void accept(AccountVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void transfer(Account target, double amount) {
        // Withdraws from 'this', deposits into 'target' — only for RON accounts
        if (TYPE.RON == this.type) {
            this.retrieve(amount);
            target.depose(amount);
            Logger.getInstance().log("Transfer: " + amount + " from " + accountCode + " to " + target.accountCode);
        } else {
            Logger.getInstance().log("Transfer skipped: only RON accounts can initiate a transfer.");
        }
    }

    @Override
    public double getInterest() {
        if (TYPE.RON == this.type) {
            return amount < 500 ? 0.03 : 0.08;
        } else {
            return 0.01;
        }
    }

    public String getAccountCode() {
        return accountCode;
    }

    public double getAmount() {
        return amount;
    }

    public TYPE getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Account " + type + ": code=" + accountCode + ", amount=" + amount;
    }
}
