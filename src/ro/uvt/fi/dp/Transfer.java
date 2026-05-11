package ro.uvt.fi.dp;

import ro.uvt.fi.dp.accout.Account;

public interface Transfer {
    void transfer(Account account, double amount);
}
