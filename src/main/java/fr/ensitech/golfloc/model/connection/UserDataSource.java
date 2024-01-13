package fr.ensitech.golfloc.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public final class UserDataSource {

	private UserDataSource() {
	}
	
	public final static Connection getConnection() throws Exception {
		String user = "root";
		String password = "MsTmMlidH99M4aS2IYrQ";
		String url = "jdbc:mysql://localhost:3306/users_db";
		String driver = "com.mysql.cj.jdbc.Driver";
		Class.forName(driver);
		
		return DriverManager.getConnection(url, user, password);
	}
}
