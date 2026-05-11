package ro.uvt.fi.dp.GUI;

import ro.uvt.fi.dp.Bank;
import ro.uvt.fi.dp.Logger;

import java.io.*;

/**
 * Serialization: handles saving and loading Bank state to/from disk.
 * Centralises all persistence so the domain model stays clean.
 */
public class BankRepository {

    private static final String FILE_NAME = "bank_data.ser";

    private BankRepository() {}

    public static void save(Bank bank) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(bank);
            Logger.getInstance().log("Bank state saved to " + FILE_NAME);
        } catch (IOException e) {
            Logger.getInstance().log("ERROR saving bank: " + e.getMessage());
        }
    }

    public static Bank load(String defaultBankCode) {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            Logger.getInstance().log("No saved data found, starting fresh.");
            return new Bank(defaultBankCode);
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Bank bank = (Bank) ois.readObject();
            Logger.getInstance().log("Bank state loaded from " + FILE_NAME);
            return bank;
        } catch (Exception e) {
            Logger.getInstance().log("ERROR loading bank, starting fresh: " + e.getMessage());
            return new Bank(defaultBankCode);
        }
    }
}
