package assig4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Set;

public class Serialize {

	@SuppressWarnings("unchecked")
	public Map<Person, Set<Account>> readBank() {
		Map<Person, Set<Account>> bankRecords;
		try {
			FileInputStream fis = new FileInputStream("bank.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			bankRecords = (Map<Person, Set<Account>>) ois.readObject();
			ois.close();
			return bankRecords;
		} catch (IOException i) {
			i.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	public void updateBank(Map<Person, Set<Account>> bankRecords) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("bank.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(bankRecords);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}