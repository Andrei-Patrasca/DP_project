package ro.uvt.fi.dp;
import java.io.FileWriter;
import java.io.IOException;
public class Account implements Operations, Transfer {

	public static enum TYPE {
		EUR, RON
	};

	String accountCode = null; //IBAN
	double amount = 0;
	TYPE type = TYPE.RON;

	public Account(String accountCode, double amount, TYPE type) {
		this.accountCode = accountCode;
		this.type = type;
		depose(amount);

		Logger.getInstance().log("Deposited " + amount + " into " + accountCode);
	}

	@Override
	public double getTotalAmount() {
		Logger.getInstance().log("Total amount " + amount + " into " + accountCode);
		return amount + amount * getInterest();

	}

	@Override
	public void depose(double amount) {
		Logger.getInstance().log("Deposited " + amount + " into " + accountCode);

		this.amount += amount;
	}

	@Override
	public void retrieve(double amount) {
		Logger.getInstance().log("Retrieved " + amount + " into " + accountCode);

		this.amount -= amount;
	}

	@Override
	public String toString() {
		Logger.getInstance().log("A to string studd");
		if (TYPE.RON == this.type)
			return "Account RON: code=" + accountCode + ", amount=" + amount;
		else
			return "Account EUR: code=" + accountCode + ", amount=" + amount;
    }

	public String getAccountCode() {
		Logger.getInstance().log("Account code " + accountCode);
		return accountCode;

	}

	public double getInterest() {
		Logger.getInstance().log("Interest " + amount);
		if (TYPE.RON == this.type) {
			if (amount < 500)
				return 0.03;
			else
				return 0.08;
		} else {
			return 0.01;
		}
    }

	@Override
	public void transfer(Account c, double s) {
		if (TYPE.RON == this.type) {
			c.retrieve(s);
			depose(s);
		}
		Logger.getInstance().log("Transfer " + s + " to " + c.accountCode);
	}

}
