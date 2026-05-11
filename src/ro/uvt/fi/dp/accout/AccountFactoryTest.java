package ro.uvt.fi.dp.accout;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountFactoryTest {

    @Test
    void testCreateRONAccount() {
        Account acc = AccountFactory.createAccount("RON-X", 100.0, Account.TYPE.RON);
        assertNotNull(acc);
        assertEquals(Account.TYPE.RON, acc.getType());
        assertEquals("RON-X", acc.getAccountCode());
        assertEquals(100.0, acc.getAmount(), 0.001);
    }

    @Test
    void testCreateEURAccount() {
        Account acc = AccountFactory.createAccount("EUR-X", 250.0, Account.TYPE.EUR);
        assertNotNull(acc);
        assertEquals(Account.TYPE.EUR, acc.getType());
        assertEquals(0.01, acc.getInterest(), 0.001);
    }

    @Test
    void testFactoryProducesUniqueInstances() {
        Account a = AccountFactory.createAccount("A1", 100, Account.TYPE.RON);
        Account b = AccountFactory.createAccount("A1", 100, Account.TYPE.RON);
        assertNotSame(a, b, "Factory must return a new instance each call");
    }
}
