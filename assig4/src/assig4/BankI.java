package assig4;

import java.util.List;
import java.util.Set;

/**
 * @author Andi
 *
 */
public interface BankI {
	/**
	 * @preCondition p!=null && account!=null
	 * @postCondition presize accounts=postsize accounts+1
	 * @param p
	 * @param acc
	 */
	public void addAccountPerson(Person p, Account acc);

	/**
	 * @preCond sum>0 && accID>0 && p!=null
	 * @param sum
	 * @param accID
	 * @param p
	 * @postCord presum<postsum
	 */
	public void depositMoney(double sum, int accID, Person p);
	/**
	 * @preCond sum>0 && accID>0 && p!=null
	 * @param sum
	 * @param accID
	 * @param p
	 * @postCord presum>postsum
	 */
	public void withdrawMoney(double sum, int accID, Person p);
	/**
	 * @preCond accID>0 && p!=null
	 * @param accID
	 * @param p
	 * @postCord preNrAcc<postNrAcc
	 */
	public void deleteAccount(int accID, Person p);
	/**
	 * @preCond p!=null
	 * @param p
	 * @postCond preNrPerson<postNrPerson
	 */
	public void deletePerson(Person p);
	/**
	 * 
	 * @return
	 */
	public List<Person> findAllPerson();
	/**
	 * p!=null
	 * @param p
	 * @return
	 */
	public Set<Account> findAllAccounts(Person p);
	/**
	 * 
	 * @return
	 */
	public boolean isWellFormed();
}
