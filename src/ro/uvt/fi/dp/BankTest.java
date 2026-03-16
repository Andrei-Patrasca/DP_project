package ro.uvt.fi.dp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BankTest {

    @Test
    void testAdd_andGetClient() {
        Bank bank = new Bank("BT-RO");
        Client client = new Client("Alina", "Cluj", Account.TYPE.RON, "ACL1", 400);

        bank.addClient(client);

        Client fetched = bank.getClient("Alina");
        assertNotNull(fetched);
        assertEquals("Alina", fetched.getName());
    }

    @Test
    void testGetNotExistentClient() {
        Bank bank = new Bank("BT-RO");
        assertNull(bank.getClient("Bobita"), "Should return null ,client missing");
    }
}