package model;

public class Client {
	private String name;
	private String address;
	private int id;

	public boolean equals(Client c1) {
		if (c1.getName().equals(this.getName()) && c1.getAddress().equals(this.getAddress())
				&& c1.getId() == this.getId())
			return true;
		return false;
	}

	public Client() {
	}

	public Client(String name) {
		this.name = name;
	}

	public Client(String name, String address) {
		if (!name.isEmpty() && !address.isEmpty()) {
			this.name = name;
			this.address = address;
		} else if (!name.isEmpty()) {
			this.name = name;
		} else if (!address.isEmpty()) {
			this.address = address;
		} else {
			System.out.println("No name entered!");
		}

	}

	public Client(int id, String name, String address) {
		if (!name.isEmpty() && !address.isEmpty()) {
			this.name = name;
			this.address = address;
			this.id = id;
		} else {
			System.out.println("No data entered!");
		}

	}

	public String getAddress() {
		return address;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

}
