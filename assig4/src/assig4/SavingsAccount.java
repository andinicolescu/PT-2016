package assig4;

public class SavingsAccount extends Account {
	private static final long serialVersionUID = 1L;
	protected static final double COMISION = 0.05;
	protected static final double MAX = 1500;
	protected static final double MAX2 = 1000;

	public SavingsAccount(int accountID, int money) {
		super(accountID, money, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addMoney(double money) {
		assert money > MAX2 : "Too much money to deposit";
		if (money <= MAX2) {
			super.money = super.money + money;
			setChanged();
			notifyObservers(money + " were added to Saving Account: " + super.accountID);
		}
	}

	@Override
	public void withdrawMoney(double money) {
		assert (super.money >= money) : "Not enough money to be withdrawn";
		assert (money > MAX) : "Too much money to be withdrawn";
		if (money <= MAX) {
			money = money - money * COMISION;
			super.money = super.money - money;
			setChanged();
			notifyObservers(money + " were withdrawn to Saving Account: " + super.accountID);
		}
	}

}
