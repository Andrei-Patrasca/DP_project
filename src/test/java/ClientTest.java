package test.java;
import org.junit.jupiter.api.Test;
import ro.uvt.fi.dp.Account;
import ro.uvt.fi.dp.Client;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    @Test
    void shouldaddAccount()
    {
        Client client = new Client("Ionel Ionan", "Timis", Account.TYPE.EUR, "EUR124", 206);
        client.addAccount(Account.TYPE.EUR, "EUR124", 20);
    }

}

public class Main {
    public static void main(String[] args) {

        Account acc = new Account("123", 100);

        // Test deposit
        acc.deposit(50);
        if (acc.getBalance() == 150) {
            System.out.println("deposit test passed");
        } else {
            System.out.println("deposit test FAILED");
        }

        // Test withdraw
        acc.withdraw(40);
        if (acc.getBalance() == 110) {
            System.out.println("withdraw test passed");
        } else {
            System.out.println("withdraw test FAILED");
        }
    }
}

