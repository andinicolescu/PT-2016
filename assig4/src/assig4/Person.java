package assig4;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class Person implements Observer, Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int personID;

	public Person(String name, int personID) {
		this.name = name;
		this.personID = personID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + personID;
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
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (personID != other.personID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", personID=" + personID + "]";
	}

	@Override
	public void update(Observable arg0, Object sum) {
		if (arg0 instanceof Account) {
			Account acc = (Account) arg0;
			System.out.println(acc.toString() + " " + sum);
		}
	}

}
