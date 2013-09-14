package com.creepercountry.amber.storage.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDatabase implements AutoCloseable 
{
	private String HostName;
	private String UserName;
	private String UserPassword;
	private String DatabaseName;
	
	private Connection dbConnection = null;
	
	MySqlDatabase(String hostName, String userName, String userPassword, String databaseName) throws SQLException
	{
		this.HostName = hostName;
		this.UserName = userName;
		this.UserPassword = userPassword;
		this.DatabaseName = databaseName;
	
		// go ahead and open a connection to the database
		connect();
		
		return;
	}

	public void connect() throws SQLException
	{
		// we need to open a connection with the database
		try
		{
			this.dbConnection = DriverManager.getConnection
			(
				String.format
				(
					"jdbc:mysql://%s/%s?user=%s&password=%s",
					this.HostName,
					this.DatabaseName,
					this.UserName,
					this.UserPassword
				)
			);
		}
		
		catch (SQLException e)
		{
			e.printStackTrace();
			
			throw e;
		}
		
		return;
	}
	
	@Override
	public void close() throws Exception
	{
		// TODO Auto-generated method stub
		
	}
}
