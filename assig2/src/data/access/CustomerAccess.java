package data.access;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import model.Client;

public class CustomerAccess {

	Connection myConn = Connect.getConnection();
	
	public void insert(Client c) {
		try {
			String nume = c.getName();
			String adresa = c.getAddress();
			myConn = Connect.getConnection();
			Statement myStat = myConn.createStatement();

			String sql = "insert into customers" + "(name, address)" + "values ('" + nume + "','" + adresa + "')";
			myStat.executeUpdate(sql);
		} catch (Exception exc) {
			exc.printStackTrace();
		}finally {
			if (myConn != null)
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public Client[] readAll() {
		Client clients[] = new Client[50];
		try {
			myConn = Connect.getConnection();
			Statement myStat = myConn.createStatement();

			ResultSet myRs = myStat.executeQuery("select * from customers");
			int i = 0;
			while (myRs.next()) {
				clients[i] = new Client(myRs.getInt("customerID"), myRs.getString("name"), myRs.getString("address"));
				i++;
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}finally {
			if (myConn != null)
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return clients;
	}

	public Client read(int id) {
		Client clients = new Client();

		try {
			myConn = Connect.getConnection();
			Statement myStat = myConn.createStatement();

			ResultSet myRs = myStat.executeQuery("select * from customers where customerID=" + id);

			while (myRs.next()) {

				clients = new Client(myRs.getInt("customerID"), myRs.getString("name"), myRs.getString("address"));
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}finally {
			if (myConn != null)
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return clients;
	}

	public void delete(int id) {
		try {
			myConn = Connect.getConnection();
			Statement myStat = myConn.createStatement();

			String sql = "delete from customers where customerID='" + id + "'";

			try {
				myStat.executeUpdate(sql);
			} catch (MySQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Product cannot be removed because is part of an order");
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}finally {
			if (myConn != null)
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public void update(Client clientul, int id) {
		String name = clientul.getName();
		String address = clientul.getAddress();
		try {
			myConn = Connect.getConnection();
			Statement myStat = myConn.createStatement();
			if (name != null && !name.isEmpty() && (address==null || address.isEmpty())) {
				String sql = "update customers " + "set name='" + name + "' "
						+ "where customerID=" + id;
				myStat.executeUpdate(sql);
			} else if (!address.isEmpty() && address != null && (name==null || name.isEmpty())) {
				String sql = "update customers " + "set address='" + address + "' "
						+ "where customerID=" + id;
				myStat.executeUpdate(sql);
			}else{
				String sql = "update customers " + "set name='" + name + "',address='" + address + "' "
						+ "where customerID=" + id;
				myStat.executeUpdate(sql);
			}
			

		} catch (Exception exc) {
			exc.printStackTrace();
		}finally {
			if (myConn != null)
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
