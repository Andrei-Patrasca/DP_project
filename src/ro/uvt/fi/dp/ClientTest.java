package ro.uvt.fi.dp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ClientTest {

    @Test
    void testAddAccount_andGetAccount() {
        Client client = new Client("Andrei Patrasca", "Maramures", Account.TYPE.RON, "IBAN1", 100.0);

        // retrieval
        assertNotNull(client.getAccount("IBAN1"));

        // adding 2nd account
        client.addAccount(Account.TYPE.EUR, "IBAN2", 50.0);
        assertNotNull(client.getAccount("IBAN2"));
        assertEquals("IBAN2", client.getAccount("IBAN2").getAccountCode());
    }

    @Test
    void testMaxLimit_forAccounts() {
        Client client = new Client("Limited Mihai", "Addre_nonim", Account.TYPE.RON, "A1", 10);
        // Add 4 , limit is 5
        client.addAccount(Account.TYPE.RON, "A2", 10);
        client.addAccount(Account.TYPE.RON, "A3", 10);
        client.addAccount(Account.TYPE.RON, "A4", 10);
        client.addAccount(Account.TYPE.RON, "A5", 10);

        // Try add  6th
        client.addAccount(Account.TYPE.RON, "A6", 10);

        assertNull(client.getAccount("A6"), "Should not be able to add more");
    }
}