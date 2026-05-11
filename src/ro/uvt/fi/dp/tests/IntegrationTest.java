package ro.uvt.fi.dp.tests;

import org.junit.jupiter.api.Test;
import ro.uvt.fi.dp.Bank;
import ro.uvt.fi.dp.accout.Account;
import ro.uvt.fi.dp.client.Client;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test: mirrors the original Test.java scenario but as proper JUnit.
 */
class IntegrationTest {

    @Test
    void testFullBankingScenario() {
        Bank bcr = new Bank("BCR Bank");

        Client cl1 = new Client("Ionescu Ion", "Timisoara", Account.TYPE.EUR, "EUR124", 200.9);
        bcr.addClient(cl1);
        cl1.addAccount(Account.TYPE.RON, "RON1234", 400);

        Client cl2 = new Client("Marinescu Marin", "Timisoara", Account.TYPE.RON, "RON126", 100);
        bcr.addClient(cl2);

        // Depose
        Client cl = bcr.getClient("Marinescu Marin");
        assertNotNull(cl);
        cl.getAccount("RON126").depose(400);
        assertEquals(500.0, cl.getAccount("RON126").getAmount(), 0.001);

        // Retrieve
        cl.getAccount("RON126").retrieve(67);
        assertEquals(433.0, cl.getAccount("RON126").getAmount(), 0.001);

        // Transfer RON126 -> RON1234
        Account a1 = cl.getAccount("RON126");
        Account a2 = bcr.getClient("Ionescu Ion").getAccount("RON1234");
        a1.transfer(a2, 40);
        assertEquals(393.0, a1.getAmount(), 0.001);
        assertEquals(440.0, a2.getAmount(), 0.001);
    }
}
