package assig4;

public class SpendingAccount extends Account {

	private static final long serialVersionUID = 1L;
	protected static final double COMISION = 0.09;

	public SpendingAccount(int accountID, int money) {
		super(accountID, money,2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addMoney(double money) {
		super.money = super.money + money;
		setChanged();
		notifyObservers(money + " were added to Spending Account: " + super.accountID);

	}

	@Override
	public void withdrawMoney(double money) {
		assert (super.money>=money): "Not enough money to be withdrawn";
		money=money-money*COMISION;
		super.money = super.money - money;
		setChanged();
		notifyObservers(money + " were withdrawn to Spending Account: " + super.accountID);

	}

}
