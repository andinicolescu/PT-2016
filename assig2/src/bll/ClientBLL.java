package bll;

import data.access.CustomerAccess;
import model.Client;

public class ClientBLL {
	private CustomerAccess database = new CustomerAccess();
	private int logedID;
	
	public String login(int id) {
		Client client = database.read(id);
		if (client.getId() != 0) {
			logedID = id;
			return client.getName();
		} else
			System.out.println("Invalid ID");
		return null;
	}
	
	public boolean update(Client clientul) {
		if (logedID != 0 && ((clientul.getName()!=null && !clientul.getName().isEmpty()) || (clientul.getAddress()!=null && !clientul.getAddress().isEmpty()))) {
			database.update(clientul,logedID);
			return true;
		} else
			return false;
	}

	public boolean insert(Client client) {

		if (client.getName() == null || client.getAddress() == null || client.getName().isEmpty()
				|| client.getAddress().isEmpty()) {
			return false;
		} else {
			database.insert(client);
			return true;
		}
	}

	public boolean delete() {
		Client clientu = database.read(logedID);
		if (clientu.getId() != 0)
			database.delete(logedID);
		else
			System.out.println("Invalid ID");
		return true;
	}

	public Client read(int id) {

		Client clientu = database.read(id);
		return clientu;
	}

	public Client[] readAll() {
		Client clientii[] = database.readAll();
		return clientii;
	}

	public int getLogedID() {
		return logedID;
	}

	public boolean setLogedID(int logedID) {
		Client clientu = database.read(logedID);
		if (clientu.getId() != 0) {
			this.logedID = logedID;
			return true;
		} else
			return false;
	}
}
