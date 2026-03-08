package ro.uvt.fi.dp;

import java.util.Arrays;

import ro.uvt.fi.dp.Account.TYPE;

public class Client {
	public static final int MAX_ACCOUNTS_NO = 5;

	private String name;
	private String address;
	private Account accounts[];
	private int accountsNo = 0;

	public Client(String nume, String adresa, Account.TYPE tip, String numarCont, double suma) {
		this.name = nume;
		this.address = adresa;
		accounts = new Account[MAX_ACCOUNTS_NO];
		addAccount(tip, numarCont, suma);
		Logger.getInstance().log("Client created");
	}

	public void addAccount(Account.TYPE type, String numarCont, double suma) {
		if (MAX_ACCOUNTS_NO > accountsNo)
			accounts[accountsNo++] = new Account(numarCont, suma, type);
		Logger.getInstance().log("Account added");
	}

	public Account getAccount(String accountCode) {
		Logger.getInstance().log("getAccount from Client");
		for (int i = 0; i < accountsNo; i++) {
			if (accounts[i].getAccountCode().equals(accountCode)) {
				return accounts[i];
			}
		}
		return null;
	}


	@Override
	public String toString() {
		Logger.getInstance().log("toString Client");
		return "\n\tClient [name=" + name + ", address=" + address + ", acounts=" + Arrays.toString(accounts) + "]";
	}

	public String getName() {
		Logger.getInstance().log("getName Client");
		return name;
	}

	public void setName(String name) {
		Logger.getInstance().log("setName Client");
		this.name = name;
	}
}
