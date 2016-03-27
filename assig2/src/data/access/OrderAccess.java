package data.access;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.Connection;

import model.Order;

public class OrderAccess {

	Connection myConn = Connect.getConnection();

	public void insert(Order o) {
		try {
			myConn = Connect.getConnection();
			Statement myStat = myConn.createStatement();

			String sql = "insert into orders" + "(orderID, productID,customerID,quantity,price)" + "values ("
					+ o.getOrderID() + "," + o.getProductID() + "," + o.getCustomerID() + "," + o.getQuantity() + ","
					+ o.getPrice() + ")";
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

	public void delete(int id) {
		try {
			myConn = Connect.getConnection();
			Statement myStat = myConn.createStatement();

			String sql = "delete from orders where orderID=" + id;

			try {
				myStat.executeUpdate(sql);
			} catch (MySQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Customer has placed and order and cannot be removed");
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

	public Order[] readAll() {
		Order order[] = new Order[50];
		try {
			myConn = Connect.getConnection();
			Statement myStat = myConn.createStatement();

			ResultSet myRs = myStat.executeQuery("select * from orders");
			int i = 0;
			while (myRs.next()) {
				order[i] = new Order(myRs.getInt("orderID"), myRs.getInt("productID"), myRs.getInt("customerID"),
						myRs.getInt("quantity"), myRs.getInt("price"));
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
		return order;
	}

	public void update(Order order, int id) {
		int quantity = order.getQuantity();
		int product = order.getProductID();
		try {
			myConn = Connect.getConnection();
			Statement myStat = myConn.createStatement();
			if (product == 0) {
				String sql = "update orders " + "set quantity=" + quantity + " where orderID=" + id;
				myStat.executeUpdate(sql);
			} else if (quantity == 0) {

				String sql = "update orders " + "set productID=" + product + " where orderID=" + id;
				myStat.executeUpdate(sql);
			} else if (quantity != 0 && product != 0) {

				String sql = "update products " + "set quantity='" + quantity + "' ,productID=" + product + " "
						+ "where orderID=" + id;
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
	
}
