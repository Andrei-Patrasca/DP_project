package ro.uvt.fi.dp.GUI;

import ro.uvt.fi.dp.Bank;
import ro.uvt.fi.dp.accout.*;
import ro.uvt.fi.dp.client.Client;

/**
 * MVC — Controller layer.
 * Connects the View events to the Bank model.
 * Uses the Command pattern (TransactionManager) for deposit/withdraw/undo.
 * Saves state after every mutating operation via BankRepository.
 */
public class Client_Controller {

    private final Bank bank;
    private final Client_View view;
    private Client currentClient;
    private final TransactionManager txManager = new TransactionManager();

    public Client_Controller(Bank bank, Client_View view) {
        this.bank = bank;
        this.view = view;
        wireListeners();
    }

    private void wireListeners() {
        view.loginBtn.addActionListener(e -> handleLogin());
        view.depositBtn.addActionListener(e -> handleDeposit());
        view.withdrawBtn.addActionListener(e -> handleWithdraw());
        view.undoBtn.addActionListener(e -> handleUndo());
        view.statementBtn.addActionListener(e -> handleStatement());
    }

    private void handleLogin() {
        String name = view.nameField.getText().trim();
        currentClient = bank.getClient(name);

        if (currentClient != null) {
            view.showMessage("Welcome, " + currentClient.getName() + "\n" + currentClient);
            populateAccountSelector();
        } else {
            view.showMessage("Client not found: \"" + name + "\"");
            view.accountSelector.removeAllItems();
        }
    }

    private void populateAccountSelector() {
        view.accountSelector.removeAllItems();
        for (Account acc : currentClient.getAccounts()) {
            view.accountSelector.addItem(acc.getAccountCode());
        }
    }

    private Account getSelectedAccount() {
        String code = (String) view.accountSelector.getSelectedItem();
        if (code == null || currentClient == null) return null;
        return currentClient.getAccount(code);
    }

    private double parseAmount() {
        try {
            return Double.parseDouble(view.amountField.getText().trim());
        } catch (NumberFormatException ex) {
            view.showMessage("Invalid amount entered.");
            return -1;
        }
    }

    private void handleDeposit() {
        Account acc = getSelectedAccount();
        double amount = parseAmount();
        if (acc == null || amount <= 0) return;

        txManager.executeCommand(new DeposeCommand(acc, amount));
        BankRepository.save(bank);
        view.showMessage("Deposited " + amount + "\n" + currentClient);
    }

    private void handleWithdraw() {
        Account acc = getSelectedAccount();
        double amount = parseAmount();
        if (acc == null || amount <= 0) return;

        txManager.executeCommand(new RetrieveCommand(acc, amount));
        BankRepository.save(bank);
        view.showMessage("Withdrew " + amount + "\n" + currentClient);
    }

    private void handleUndo() {
        txManager.undoLastCommand();
        BankRepository.save(bank);
        if (currentClient != null) {
            view.showMessage("Undo performed.\n" + currentClient);
        }
    }

    private void handleStatement() {
        if (currentClient == null) {
            view.showMessage("Please login first.");
            return;
        }
        MonthlyStatementGenerator gen = new MonthlyStatementGenerator();
        StringBuilder sb = new StringBuilder("=== Monthly Statement: " + currentClient.getName() + " ===\n");
        for (Account acc : currentClient.getAccounts()) {
            double interest = acc.getAmount() * acc.getInterest();
            sb.append("\nAccount: ").append(acc.getAccountCode())
              .append("\n  Type    : ").append(acc.getType())
              .append("\n  Balance : ").append(acc.getAmount())
              .append("\n  Rate    : ").append(acc.getInterest() * 100).append("%")
              .append("\n  Interest: ").append(interest)
              .append("\n  Total   : ").append(acc.getTotalAmount())
              .append("\n--------------------");
            acc.accept(gen);
        }
        sb.append("\n\nTotal interest across all accounts: ").append(gen.getTotalInterestCalculated());
        view.showMessage(sb.toString());
    }
}
