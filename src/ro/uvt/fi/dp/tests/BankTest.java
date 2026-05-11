package ro.uvt.fi.dp.tests;

import org.junit.jupiter.api.Test;
import ro.uvt.fi.dp.Bank;
import ro.uvt.fi.dp.accout.Account;
import ro.uvt.fi.dp.client.Client;
import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    @Test
    void testAddAndGetClient() {
        Bank bank = new Bank("TEST-BANK");
        Client client = new Client("Alina", "Cluj", Account.TYPE.RON, "ACL1", 400);
        bank.addClient(client);

        Client found = bank.getClient("Alina");
        assertNotNull(found);
        assertEquals("Alina", found.getName());
    }

    @Test
    void testGetNonExistentClientReturnsNull() {
        Bank bank = new Bank("TEST-BANK");
        assertNull(bank.getClient("Fantoma"));
    }

    @Test
    void testMultipleClients() {
        Bank bank = new Bank("TEST-BANK");
        bank.addClient(new Client("Alpha", "A", Account.TYPE.RON, "R1", 100));
        bank.addClient(new Client("Beta",  "B", Account.TYPE.EUR, "E1", 200));
        assertNotNull(bank.getClient("Alpha"));
        assertNotNull(bank.getClient("Beta"));
    }
}
