package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private Connection con;
	
	public DBConnection(String url,String username,String pwd) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");		
		this.con=DriverManager.getConnection(url,username,pwd);
	}
	public Connection getConnection()
	{
		return this.con;
	}
	public void CloseConnection() throws SQLException
	{
		if(this.con!=null)
			 this.con.close();
	}

}
