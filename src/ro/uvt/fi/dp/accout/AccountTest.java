package ro.uvt.fi.dp.accout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account ronLow;   // balance < 500 -> 3% interest
    private Account ronHigh;  // balance >= 500 -> 8% interest
    private Account eur;

    @BeforeEach
    void setUp() {
        ronLow  = AccountFactory.createAccount("RON-LOW",  200.0, Account.TYPE.RON);
        ronHigh = AccountFactory.createAccount("RON-HIGH", 800.0, Account.TYPE.RON);
        eur     = AccountFactory.createAccount("EUR-001",  300.0, Account.TYPE.EUR);
    }

    @Test
    void testDepose() {
        ronLow.depose(100.0);
        assertEquals(300.0, ronLow.getAmount(), 0.001);
    }

    @Test
    void testRetrieve() {
        ronLow.retrieve(50.0);
        assertEquals(150.0, ronLow.getAmount(), 0.001);
    }

    @Test
    void testInterestLowRON() {
        assertEquals(0.03, ronLow.getInterest(), 0.001);
    }

    @Test
    void testInterestHighRON() {
        assertEquals(0.08, ronHigh.getInterest(), 0.001);
    }

    @Test
    void testInterestEUR() {
        assertEquals(0.01, eur.getInterest(), 0.001);
    }

    @Test
    void testGetTotalAmount() {
        // 200 + 200*0.03 = 206
        assertEquals(206.0, ronLow.getTotalAmount(), 0.001);
    }

    @Test
    void testTransferRON() {
        Account src  = AccountFactory.createAccount("SRC",  300.0, Account.TYPE.RON);
        Account dest = AccountFactory.createAccount("DEST", 100.0, Account.TYPE.RON);
        src.transfer(dest, 100.0);
        assertEquals(200.0, src.getAmount(),  0.001, "Source should decrease");
        assertEquals(200.0, dest.getAmount(), 0.001, "Destination should increase");
    }

    @Test
    void testTransferEURDoesNothing() {
        // EUR accounts cannot initiate transfers
        Account eurSrc  = AccountFactory.createAccount("ESRC",  500.0, Account.TYPE.EUR);
        Account eurDest = AccountFactory.createAccount("EDEST", 100.0, Account.TYPE.EUR);
        eurSrc.transfer(eurDest, 100.0);
        assertEquals(500.0, eurSrc.getAmount(),  0.001, "EUR source unchanged");
        assertEquals(100.0, eurDest.getAmount(), 0.001, "EUR dest unchanged");
    }
}
