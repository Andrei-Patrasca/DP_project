package ro.uvt.fi.dp.accout;

/**
 * Visitor pattern: generates a monthly statement by visiting each Account.
 * Separates the reporting responsibility from the Account class itself.
 */
public class MonthlyStatementGenerator implements AccountVisitor {

    private double totalInterest = 0;

    @Override
    public void visit(Account account) {
        double interest = account.getAmount() * account.getInterest();
        totalInterest += interest;

        System.out.println("Statement for account: " + account.getAccountCode());
        System.out.println("  Type            : " + account.getType());
        System.out.println("  Current balance : " + account.getAmount());
        System.out.println("  Interest rate   : " + (account.getInterest() * 100) + "%");
        System.out.println("  Interest earned : " + interest);
        System.out.println("  Total with int. : " + account.getTotalAmount());
        System.out.println("------------------------------------");
    }

    public double getTotalInterestCalculated() {
        return totalInterest;
    }

    public void reset() {
        totalInterest = 0;
    }
}
