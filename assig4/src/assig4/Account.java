package assig4;

import java.io.Serializable;
import java.util.Observable;

public abstract class Account extends Observable implements Serializable{
	private static final long serialVersionUID = 1L;
	protected int accountID;
	protected double money;
	private int type;
	
	public Account(int accountID, int money,int type){
		this.accountID=accountID;
		this.money=money;
		this.type=type;
	}
	
	public abstract void addMoney(double money);
	
	public abstract void withdrawMoney(double money);
	
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountID;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountID != other.accountID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", money=" + money + "]";
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
	
}
