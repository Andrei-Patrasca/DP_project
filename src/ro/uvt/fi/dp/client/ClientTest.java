package ro.uvt.fi.dp.client;

import org.junit.jupiter.api.Test;
import ro.uvt.fi.dp.accout.Account;
import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void testAddAndGetAccount() {
        Client client = new Client("Ion Pop", "Timisoara", Account.TYPE.RON, "IBAN-1", 100.0);
        assertNotNull(client.getAccount("IBAN-1"));

        client.addAccount(Account.TYPE.EUR, "IBAN-2", 50.0);
        assertNotNull(client.getAccount("IBAN-2"));
        assertEquals("IBAN-2", client.getAccount("IBAN-2").getAccountCode());
    }

    @Test
    void testGetNonExistentAccountReturnsNull() {
        Client client = new Client("Ion Pop", "Timisoara", Account.TYPE.RON, "IBAN-1", 100.0);
        assertNull(client.getAccount("IBAN-MISSING"));
    }

    @Test
    void testMaxAccountsLimit() {
        Client client = new Client("Max Test", "X", Account.TYPE.RON, "A1", 10);
        client.addAccount(Account.TYPE.RON, "A2", 10);
        client.addAccount(Account.TYPE.RON, "A3", 10);
        client.addAccount(Account.TYPE.RON, "A4", 10);
        client.addAccount(Account.TYPE.RON, "A5", 10);
        // 6th account should be silently ignored
        client.addAccount(Account.TYPE.RON, "A6", 10);
        assertNull(client.getAccount("A6"), "6th account must not be added (max=5)");
    }

    @Test
    void testGetAccounts() {
        Client client = new Client("Ada", "Iasi", Account.TYPE.RON, "R1", 200.0);
        client.addAccount(Account.TYPE.EUR, "E1", 100.0);
        assertEquals(2, client.getAccounts().length);
    }
}
