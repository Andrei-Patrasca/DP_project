package ro.uvt.fi.dp;

import ro.uvt.fi.dp.accout.AccountVisitor;

public interface Operations {
    double getTotalAmount();
    double getInterest();
    void depose(double amount);
    void retrieve(double amount);
    void accept(AccountVisitor visitor);
}
