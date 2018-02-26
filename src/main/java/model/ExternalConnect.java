package model;
/* dBApplication class to connect to an Access database and read from it*/

import java.sql.*;

public class ExternalConnect implements DatabaseConnection  
{
	private Connection myConnection;

	/**
     *  Method:         displayError
     *
     *  Description:    displays all the error messages 
     *  
     *  @param			e : The String representation of the error that was caught
     *
     */
	public void displayError (String e) 
	{
		System.out.println(e);
	}
	/**
     *  Method:         connect
     *
     *  Description:    creates the connection to the database.
     *
     */
	public void connect() 
	{
		String url = "jdbc:odbc:external";
		String user = "java";
		String password = "java";

		try 
		{
			Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch (ClassNotFoundException e) 
		{
			displayError ("Can't load driver");
			displayError (e.getMessage());
		}

		try 
		{
			this.myConnection = DriverManager.getConnection(url, user, password);
		}
		catch (SQLException ex) 
		{
			displayError(ex.getMessage());
			displayError("Can't connect");
		}
	}
	/**
     *  Method:         query
     *
     *  Description:    sends the query to the database
     *  
     *  @param			sqlQuery : The query to be executed at the server
     *	@return			ResultSet : The ResultSet of the query
     */		
	public ResultSet query(String sqlQuery) 
	{
		ResultSet tempResults = null;
		Statement aStatement;
		try 
		{
			aStatement = this.myConnection.createStatement();
			tempResults = aStatement.executeQuery(sqlQuery);
		}
		catch (SQLException ex) 
		{
			displayError(ex.getMessage());
		}
		return tempResults;
	}


    /**
     *  Method:         closeConnection
     *
     *  Description:    closes the connection to the database.
     *
     */
	public void closeConnection() 
	{
		try 
		{
			this.myConnection.close();
		}
		catch (SQLException ex)
		{
			displayError(ex.getMessage());
		}
	}
}