package assig4;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BankTest {

	Bank bank;
	Person testPerson = new Person("Ionut", 1);
	Account testAccount1 = new SavingsAccount(1, 540);
	Account testAccount2 = new SpendingAccount(2, 800);

	@Before
	public void setUp() throws Exception {
		bank = new Bank();
	}

	@After
	public void tearDown() throws Exception {
		bank.deletePerson(testPerson);
		bank = null;
	}

	@Test
	public void testAddAccountPerson() {
		bank.addAccountPerson(testPerson, testAccount1);
		if (bank.getRecords().containsKey(testPerson)) {
			for (Account acc : bank.getRecords().get(testPerson)) {
				assertEquals(acc, testAccount1);
			}
			bank.addAccountPerson(testPerson, testAccount2);
			for (Account acc : bank.getRecords().get(testPerson)) {
				if (acc.getAccountID() == testAccount2.getAccountID())
					assertEquals(acc, testAccount2);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testDepositMoney() {
		bank.addAccountPerson(testPerson, testAccount1);
		double preSum = testAccount1.getMoney();
		bank.depositMoney(200, testAccount1.getAccountID(), testPerson);
		for (Account acc : bank.getRecords().get(testPerson)) {
			if (acc.getAccountID() == testAccount1.getAccountID()) {
				assertEquals(acc.getMoney(), preSum + 200);
			}
		}
	}

	@Test
	public void testWithdrawMoney() {
		bank.addAccountPerson(testPerson, testAccount1);
		double preSum = testAccount1.getMoney();
		bank.withdrawMoney(200, testAccount1.getAccountID(), testPerson);
		for (Account acc : bank.getRecords().get(testPerson)) {
			if (acc.getAccountID() == testAccount1.getAccountID()) {
				assertEquals(acc.getMoney(), preSum - (200 - SavingsAccount.COMISION * 200));
			}
		}
	}

	@Test
	public void testDeleteAccount() {
		bank.addAccountPerson(testPerson, testAccount1);
		bank.addAccountPerson(testPerson, testAccount2);
		bank.deleteAccount(testAccount1.getAccountID(), testPerson);
		Set<Account> account2=new HashSet<Account>();
		account2.add(testAccount2);
		System.out.println(account2.toString());
		System.out.println(bank.getRecords().get(testPerson).toString());
		assertEquals(bank.getRecords().get(testPerson),account2);
	}

	@Test
	public void testDeletePerson() {
		bank.addAccountPerson(testPerson, testAccount1);
		bank.addAccountPerson(testPerson, testAccount2);
		bank.deletePerson(testPerson);
		assertEquals(bank.getRecords().containsKey(testPerson),false);
	}

	@Test
	public void testFindAllPerson() {
		assertEquals(bank.findAllPerson(),new ArrayList<Person>(bank.getRecords().keySet()));
	}

	@Test
	public void testFindAllAccounts() {
		bank.addAccountPerson(testPerson, testAccount1);
		assertEquals(bank.findAllAccounts(testPerson),bank.getRecords().get(testPerson));
	}

}
