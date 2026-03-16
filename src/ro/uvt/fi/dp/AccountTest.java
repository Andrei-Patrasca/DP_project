package ro.uvt.fi.dp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountTest {
    private Account ronAccount;
    private Account eurAccount;

    @BeforeEach
    void setUp() {
        ronAccount = new Account("RO122", 200.0, Account.TYPE.RON);
        eurAccount = new Account("EU555", 300.0, Account.TYPE.EUR);
    }

    @Test
    void testDepose() {
        ronAccount.depose(55.0);
        assertEquals(255.0, ronAccount.amount, "should be 255");
    }

    @Test
    void testRetrieve() {
        ronAccount.retrieve(30.0);
        assertEquals(170.0, ronAccount.amount, "Amount = 170 ");
    }

    @Test
    void testGetInterestLowRON() {
        // < 500 => 0.03
        assertEquals(0.03, ronAccount.getInterest());
    }

    @Test
    void testGetInterestHighRon() {
        //>= 500   0.08
        ronAccount.depose(500);
        assertEquals(0.08, ronAccount.getInterest());
    }

    @Test
    void testTransfer() {
        Account source= new Account("SRC",250.0, Account.TYPE.RON);
        Account destination= new Account("DEST",101.0, Account.TYPE.RON);

        destination.transfer(source, 50.0);

        assertEquals(200.0, source.amount, "Src should decrease");
        assertEquals(151.0, destination.amount, "Dest should increase");
    }
}