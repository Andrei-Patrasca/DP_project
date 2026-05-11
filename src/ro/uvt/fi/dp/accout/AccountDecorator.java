package ro.uvt.fi.dp.accout;

import ro.uvt.fi.dp.Operations;

/**
 * Decorator pattern base class for Account.
 * Wraps an Account and delegates all Operations to it by default.
 * Concrete decorators override only the behaviour they want to change.
 */
public abstract class AccountDecorator implements Operations {

    protected final Account decoratedAccount;

    public AccountDecorator(Account account) {
        this.decoratedAccount = account;
    }

    @Override
    public double getTotalAmount() {
        return decoratedAccount.getTotalAmount();
    }

    @Override
    public double getInterest() {
        return decoratedAccount.getInterest(); // delegate by default
    }

    @Override
    public void depose(double amount) {
        decoratedAccount.depose(amount);
    }

    @Override
    public void retrieve(double amount) {
        decoratedAccount.retrieve(amount);
    }

    @Override
    public void accept(AccountVisitor visitor) {
        decoratedAccount.accept(visitor); // visitor sees the real account
    }
}
