package fr.ensitech.golfloc.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public final class UserDataSource {

	private UserDataSource() {
	}
	
	public final static Connection getConnection() throws Exception {
		String user = "u206027959_golfloc";
		String password = "4WizAAL0ABzF3WI2K1Xw";
		String url = "jdbc:mysql://193.203.168.57/u206027959_hibernate";
		String driver = "com.mysql.cj.jdbc.Driver";
		Class.forName(driver);
		
		return DriverManager.getConnection(url, user, password);
	}
}
