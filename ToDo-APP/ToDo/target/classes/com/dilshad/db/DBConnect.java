package com.dilshad.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	
	private static Connection conn;
	
	public static Connection getConncetion()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			if(conn==null)
			{
				String username="root";
				String password="root";
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp", username, password);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return conn;
	}

}
