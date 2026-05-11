package ro.uvt.fi.dp;

import ro.uvt.fi.dp.client.Client;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Represents a bank that holds a collection of clients.
 */
public class Bank implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int MAX_CLIENTS_NUMBER = 100;

    private Client[] clients;
    private int clientsNumber;
    private String bankCode;

    public Bank(String bankCode) {
        this.bankCode = bankCode;
        this.clients = new Client[MAX_CLIENTS_NUMBER];
        Logger.getInstance().log("Bank created: " + bankCode);
    }

    public void addClient(Client c) {
        clients[clientsNumber++] = c;
        Logger.getInstance().log("Client added to " + bankCode + ": " + c.getName());
    }

    public Client getClient(String name) {
        for (int i = 0; i < clientsNumber; i++) {
            if (clients[i].getName().equals(name)) {
                return clients[i];
            }
        }
        return null;
    }

    public String getBankCode() {
        return bankCode;
    }

    @Override
    public String toString() {
        return "Bank [code=" + bankCode + ", clients=" + Arrays.toString(clients) + "]";
    }
}
