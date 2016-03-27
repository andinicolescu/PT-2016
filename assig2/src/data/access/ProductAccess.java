package data.access;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import model.Product;

public class ProductAccess {

	Connection myConn = Connect.getConnection();

	public void insert(Product prod) {
		try {
			String nume = prod.getName();
			int quantity = prod.getQuantity();
			int price = prod.getPrice();
			myConn = Connect.getConnection();
			Statement myStat = myConn.createStatement();

			String sql = "insert into products" + "(name, numberOf,price)" + "values ('" + nume + "'," + quantity + ","
					+ price + ")";
			myStat.executeUpdate(sql);
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			if (myConn != null)
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public Product[] readAll() {
		Product prod[] = new Product[50];
		try {
			myConn = Connect.getConnection();
			Statement myStat = myConn.createStatement();

			ResultSet myRs = myStat.executeQuery("select * from products");
			int i = 0;
			while (myRs.next()) {
				prod[i] = new Product(myRs.getInt("productID"), myRs.getString("name"), myRs.getInt("numberOf"),
						myRs.getInt("price"));
				i++;
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			if (myConn != null)
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return prod;
	}

	public Product read(int id) {
		Product prod = new Product();

		try {
			myConn = Connect.getConnection();
			Statement myStat = myConn.createStatement();

			ResultSet myRs = myStat.executeQuery("select * from products where productID=" + id);
			while (myRs.next()) {
				prod = new Product(myRs.getInt("productID"), myRs.getString("name"), myRs.getInt("numberOf"),
						myRs.getInt("price"));
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			if (myConn != null)
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return prod;
	}

	public void delete(int id) {
		try {
			myConn = Connect.getConnection();
			Statement myStat = myConn.createStatement();

			String sql = "delete from products where productID=" + id;

			try {
				myStat.executeUpdate(sql);
			} catch (MySQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Product cannot be removed because is part of an order");
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			if (myConn != null)
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public void update(Product prod, int id) {
		String name = prod.getName();
		int quantity = prod.getQuantity();
		int price = prod.getPrice();
		try {
			myConn = Connect.getConnection();
			Statement myStat = myConn.createStatement();
			if (quantity == 0 && price == 0) {
				String sql = "update products " + "set name='" + name + "' " + "where productID=" + id;
				myStat.executeUpdate(sql);
			} else if ((prod.getName() == null || prod.getName().isEmpty()) && price == 0) {

				String sql = "update products " + "set numberOf=" + quantity + " " + "where productID=" + id;
				myStat.executeUpdate(sql);
			} else if ((prod.getName() == null || prod.getName().isEmpty()) && quantity == 0) {

				String sql = "update products " + "set price=" + price + " " + "where productID=" + id;
				myStat.executeUpdate(sql);
			} else if (quantity == 0) {

				String sql = "update products " + "set name='" + name + "' ,price=" + price + " " + "where productID="
						+ id;
				myStat.executeUpdate(sql);
			} else if (price == 0) {

				String sql = "update products " + "set name='" + name + "' ,numberOf=" + quantity + " "
						+ "where productID=" + id;
				myStat.executeUpdate(sql);
			} else if (prod.getName() != null && !prod.getName().isEmpty()) {

				String sql = "update products " + "set numberOf=" + quantity + " ,price=" + price + " "
						+ "where productID=" + id;
				myStat.executeUpdate(sql);
			} else if (prod.getName() != null && !prod.getName().isEmpty() && price != 0 && quantity != 0) {
				String sql = "update products " + "set name='" + name + "',numberOf=" + quantity + " ,price=" + price
						+ " " + "where productID=" + id;
				myStat.executeUpdate(sql);
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			if (myConn != null)
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public void updateQuantity(Product prod, int id) {
		int quantity = prod.getQuantity();
		try {
			myConn = Connect.getConnection();
			Statement myStat = myConn.createStatement();

			String sql = "update products " + "set numberOf=" + quantity + " " + "where productID=" + id;
			myStat.executeUpdate(sql);

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			if (myConn != null)
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
