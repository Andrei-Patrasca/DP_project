package ro.uvt.fi.dp.GUI;

import ro.uvt.fi.dp.Bank;
import ro.uvt.fi.dp.Logger;
import ro.uvt.fi.dp.accout.Account;
import ro.uvt.fi.dp.client.Client;

/**
 * Application entry point.
 * Loads bank state from disk (serialization), seeds test data on first run,
 * then launches the Swing GUI on the Event Dispatch Thread.
 */
public class MainApp {

    private static final String DEFAULT_BANK_CODE = "UVT_BANK_001";

    public static void main(String[] args) {
        // 1. Load persisted bank state (or create fresh one)
        Bank bank = BankRepository.load(DEFAULT_BANK_CODE);

        // 2. Seed data on first run
        if (bank.getClient("Ion Popescu") == null) {
            Logger.getInstance().log("First run — seeding test data.");
            Client c1 = new Client("Ion Popescu", "Timisoara", Account.TYPE.RON, "RO-RON-001", 1000.0);
            c1.addAccount(Account.TYPE.EUR, "RO-EUR-001", 500.0);
            bank.addClient(c1);

            Client c2 = new Client("Maria Ionescu", "Cluj", Account.TYPE.RON, "RO-RON-002", 300.0);
            bank.addClient(c2);

            BankRepository.save(bank);
        }

        // 3. Launch GUI on the Event Dispatch Thread (Swing requirement)
        final Bank finalBank = bank;
        javax.swing.SwingUtilities.invokeLater(() -> {
            Client_View view = new Client_View();
            new Client_Controller(finalBank, view);
        });
    }
}
