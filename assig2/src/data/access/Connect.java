package data.access;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {


	public static Connection getConnection() {
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment2", "root", "");
			return myConn;

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}
	
}
