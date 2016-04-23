package assig4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Bank implements BankI {
	private Map<Person, Set<Account>> records;
	private Serialize ser;

	public Bank() {
		ser=new Serialize();
		records = new HashMap<Person, Set<Account>>();
		records=ser.readBank();
	}

	public void addAccountPerson(Person p, Account acc) {
		assert isWellFormed() : "The bank is not consistent señor";
		assert p != null : "The person must not be NULL";
		int preSize;
		acc.addObserver(p);
		if (records.containsKey(p)) {
			Set<Account> accounts = records.get(p);
			preSize = accounts.size();
			accounts.add(acc);
		} else {
			Set<Account> accounts = new HashSet<Account>();
			preSize = 0;
			accounts.add(acc);
			records.put(p, accounts);
		}
		int postSize = records.get(p).size();
		assert preSize == postSize - 1 : "Account not added";
		assert isWellFormed() : "The bank is not consistent señor";
		ser.updateBank(records);
	}

	public void depositMoney(double sum, int accID, Person p) {
		assert isWellFormed() : "Bank is not consistent";
		assert sum > 0 && accID > 0 && p != null : "Money cannot be added to account";
		double preSum = 0;
		double postSum = 0;
		if (records.containsKey(p)) {
			Set<Account> accounts = records.get(p);
			for (Account a : accounts) {
				if (a.getAccountID() == accID) {
					preSum = a.getMoney();
					a.addMoney(sum);
					postSum = a.getMoney();
				}
			}
		} else
			System.out.println("No such account");
		assert preSum < postSum : "Money has not been added";
		assert isWellFormed() : "Bank is not consistent";
		ser.updateBank(records);
	}

	@Override
	public String toString() {
		return "Bank [records=" + records + "]";
	}

	@Override
	public boolean isWellFormed() {
		for (Entry<Person, Set<Account>> entry : records.entrySet()) {
			if (entry.getValue() == null || entry.getValue().isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void withdrawMoney(double sum, int accID, Person p) {
		assert isWellFormed() : "Bank is not consistent";
		assert sum > 0 && accID > 0 && p != null : "Money cannot be withdrawn to account";
		double preSum = 0;
		double postSum = 0;
		if (records.containsKey(p)) {
			Set<Account> accounts = records.get(p);
			for (Account a : accounts) {
				if (a.getAccountID() == accID && a.getMoney()>=sum) {
					preSum = a.getMoney();
					a.withdrawMoney(sum);
					postSum = a.getMoney();
				}
			}
		} else
			System.out.println("No such account");
		assert preSum > postSum : "Money has not been added";
		assert isWellFormed() : "Bank is not consistent";
		ser.updateBank(records);
	}

	@Override
	public void deleteAccount(int accID, Person p) {
		assert isWellFormed() : "The bank is not consistent señor";
		assert p != null && accID != 0 : "Cannot delete Account";
		int preNr = 0;
		int postNr = 0;
		if (records.containsKey(p)) {
			Set<Account> accounts = records.get(p);
			for (Account a : accounts) {
				if (a.getAccountID() == accID) {
					preNr = accounts.size();
					accounts.remove(a);
					if (accounts.size() == 0) {
						deletePerson(p);
					}
					postNr = accounts.size();
					break;
				}
			}
		}
		assert preNr > postNr : "Account has not been deleted";
		assert isWellFormed() : "The bank is not consistent señor";
		ser.updateBank(records);
	}

	@Override
	public void deletePerson(Person p) {
		assert isWellFormed() : "The bank is not consistent señor";
		assert p != null : "Delete Person has failed";
		int preSize = 0;
		int postSize = 0;
		if (records.containsKey(p)) {
			preSize = records.size();
			records.remove(p, records.get(p));
			postSize = records.size();
		}
		assert postSize == preSize - 1 : "Did not delete person";
		assert isWellFormed() : "The bank is not consistent señor";
		ser.updateBank(records);
	}

	@Override
	public ArrayList<Person> findAllPerson() {
		ArrayList<Person> persons = new ArrayList<Person>(records.keySet());
		return persons;
	}

	@Override
	public Set<Account> findAllAccounts(Person p) {
		assert p != null : "Cannot find all accounts";
		if (records.containsKey(p)) {
			Set<Account> accounts = records.get(p);
			return accounts;
		} else {
			System.out.println("There is no such person to find");
			return null;
		}
		
	}

	public Map<Person, Set<Account>> getRecords() {
		return records;
	}

	public void setRecords(Map<Person, Set<Account>> records) {
		this.records = records;
	}

}
