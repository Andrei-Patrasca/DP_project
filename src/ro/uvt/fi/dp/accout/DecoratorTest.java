package ro.uvt.fi.dp.accout;

import org.junit.jupiter.api.Test;
import ro.uvt.fi.dp.client.Client;
import ro.uvt.fi.dp.client.ClientIsVerifiedDecorator;

import static org.junit.jupiter.api.Assertions.*;

class DecoratorTest {

    // --- Account Decorator (HolidayDecorator) ---

    @Test
    void testHolidayDecoratorIncreasesTotal() {
        Account acc = AccountFactory.createAccount("RON-H", 1000.0, Account.TYPE.RON);
        double baseTotal = acc.getTotalAmount(); // 1000 + 1000*0.08 = 1080

        HolidayDecorator festive = new HolidayDecorator(acc);
        double festiveTotal = festive.getTotalAmount(); // 1080 + 1080*0.05 = 1134

        assertTrue(festiveTotal > baseTotal, "Holiday total must exceed regular total");
        assertEquals(baseTotal * 1.05, festiveTotal, 0.001);
    }

    @Test
    void testHolidayDecoratorDelegatesInterest() {
        Account acc = AccountFactory.createAccount("RON-H2", 200.0, Account.TYPE.RON);
        HolidayDecorator festive = new HolidayDecorator(acc);
        // Decorator must delegate getInterest() to the wrapped account
        assertEquals(acc.getInterest(), festive.getInterest(), 0.001);
    }

    @Test
    void testHolidayDecoratorVisitorDelegates() {
        Account acc = AccountFactory.createAccount("RON-H3", 500.0, Account.TYPE.RON);
        HolidayDecorator festive = new HolidayDecorator(acc);
        MonthlyStatementGenerator gen = new MonthlyStatementGenerator();
        // accept() on decorator must forward to the wrapped account's visit
        assertDoesNotThrow(() -> festive.accept(gen));
        assertTrue(gen.getTotalInterestCalculated() > 0, "Visitor should have processed the account");
    }

    // --- Client Decorator (ClientIsVerifiedDecorator) ---

    @Test
    void testVerifiedDecoratorAddsLabel() {
        Client client = new Client.Builder("Ana Pop").setAddress("Timisoara").build();
        ClientIsVerifiedDecorator verified = new ClientIsVerifiedDecorator(client);
        assertTrue(verified.getName().contains("Ana Pop"));
        assertTrue(verified.getName().contains("VERIFIED"));
    }

    @Test
    void testVerifiedDecoratorToString() {
        Client client = new Client.Builder("Dan Marin").build();
        ClientIsVerifiedDecorator verified = new ClientIsVerifiedDecorator(client);
        assertTrue(verified.toString().contains("Verified"));
    }

    @Test
    void testVerifiedDecoratorDelegatesAccount() {
        Client client = new Client("Radu B", "Cluj", Account.TYPE.RON, "RON-99", 400.0);
        ClientIsVerifiedDecorator verified = new ClientIsVerifiedDecorator(client);
        assertNotNull(verified.getAccount("RON-99"), "Decorator must delegate getAccount to wrapped client");
    }
}
