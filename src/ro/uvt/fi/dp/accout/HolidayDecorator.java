package ro.uvt.fi.dp.accout;

import ro.uvt.fi.dp.Logger;

/**
 * Decorator pattern: wraps an Account and applies a seasonal holiday bonus
 * on top of the existing interest, without modifying Account itself.
 */
public class HolidayDecorator extends AccountDecorator {

    private static final double HOLIDAY_BONUS = 0.05; // 5% Christmas bonus

    public HolidayDecorator(Account account) {
        super(account);
    }

    /**
     * Returns total amount with the regular interest PLUS the holiday bonus.
     * The base interest is still calculated by the wrapped account normally.
     */
    @Override
    public double getTotalAmount() {
        double baseTotal = super.getTotalAmount(); // includes regular interest
        double bonus = baseTotal * HOLIDAY_BONUS;
        Logger.getInstance().log("HolidayDecorator: applying " + (HOLIDAY_BONUS * 100) + "% bonus on " +
                decoratedAccount.getAccountCode());
        return baseTotal + bonus;
    }

    // getInterest(), depose(), retrieve(), accept() all delegate to super (AccountDecorator)
    // which in turn delegates to the wrapped Account — no need to override them.
}
