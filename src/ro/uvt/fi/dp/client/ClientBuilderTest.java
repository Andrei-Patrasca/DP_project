package ro.uvt.fi.dp.client;

import org.junit.jupiter.api.Test;
import ro.uvt.fi.dp.accout.Account;
import static org.junit.jupiter.api.Assertions.*;

class ClientBuilderTest {

    @Test
    void testBuilderWithAllFields() {
        Client client = new Client.Builder("Ana Ionescu")
                .setAddress("Timisoara, str. Revolutiei")
                .build();

        assertNotNull(client);
        assertEquals("Ana Ionescu", client.getName());
        assertEquals("Timisoara, str. Revolutiei", client.getAddress());
    }

    @Test
    void testBuilderWithNameOnly() {
        Client client = new Client.Builder("Mihai Eminescu").build();
        assertNotNull(client);
        assertEquals("Mihai Eminescu", client.getName());
    }

    @Test
    void testBuilderClientCanAddAccount() {
        Client client = new Client.Builder("Radu Pop")
                .setAddress("Cluj")
                .build();

        client.addAccount(Account.TYPE.RON, "RON-001", 500.0);
        assertNotNull(client.getAccount("RON-001"));
    }
}
