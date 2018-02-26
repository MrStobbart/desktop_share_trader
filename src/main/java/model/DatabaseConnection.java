package model;

	import java.sql.*;

	public interface DatabaseConnection 
	{
	    /**
	     *  The Connection to the database
	     */
		public Connection myConnection = null;
	    /**
	     *  Method:         closeConnection
	     *
	     *  Description:    closes the connection to the database.
	     *
	     */
		public void closeConnection();
		/**
	     *  Method:         connect
	     *
	     *  Description:    creates the connection to the database.
	     *
	     */
		public void connect();
		/**
	     *  Method:         displayError
	     *
	     *  Description:    displays all the error messages 
	     *  
	     *  @param			e : The String representation of the error that was caught
	     *
	     */
		public void displayError(String e);
		/**
	     *  Method:         query
	     *
	     *  Description:    sends the query to the database
	     *  
	     *  @param			query : The query to be executed at the server
	     *	@return			ResultSet : The ResultSet of the query
	     */		
		public ResultSet query(String query);
		
	}
