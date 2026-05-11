package ro.uvt.fi.dp.accout;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VisitorTest {

    @Test
    void testVisitorCalculatesInterestForEUR() {
        Account eur = AccountFactory.createAccount("EUR-V", 1000.0, Account.TYPE.EUR);
        MonthlyStatementGenerator gen = new MonthlyStatementGenerator();
        eur.accept(gen);
        // EUR interest = 1000 * 0.01 = 10
        assertEquals(10.0, gen.getTotalInterestCalculated(), 0.001);
    }

    @Test
    void testVisitorCalculatesInterestForRON() {
        Account ron = AccountFactory.createAccount("RON-V", 5000.0, Account.TYPE.RON);
        MonthlyStatementGenerator gen = new MonthlyStatementGenerator();
        ron.accept(gen);
        // RON >= 500 -> 8%: 5000 * 0.08 = 400
        assertEquals(400.0, gen.getTotalInterestCalculated(), 0.001);
    }

    @Test
    void testVisitorAccumulatesAcrossMultipleAccounts() {
        Account eur = AccountFactory.createAccount("EUR-V2", 1000.0, Account.TYPE.EUR);  // 10
        Account ron = AccountFactory.createAccount("RON-V2", 5000.0, Account.TYPE.RON);  // 400
        MonthlyStatementGenerator gen = new MonthlyStatementGenerator();
        eur.accept(gen);
        ron.accept(gen);
        assertEquals(410.0, gen.getTotalInterestCalculated(), 0.001);
    }

    @Test
    void testVisitorReset() {
        Account acc = AccountFactory.createAccount("RON-V3", 1000.0, Account.TYPE.RON);
        MonthlyStatementGenerator gen = new MonthlyStatementGenerator();
        acc.accept(gen);
        gen.reset();
        assertEquals(0.0, gen.getTotalInterestCalculated(), 0.001, "After reset, total must be 0");
    }
}
