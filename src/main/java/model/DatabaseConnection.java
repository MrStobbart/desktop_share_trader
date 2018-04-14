package model;

	import java.sql.*;

	public interface DatabaseConnection{

	    /**
	     *  The Connection to the database
	     */
	    Connection connection = null;

	    /**
	     *  Method:         closeConnection
	     *  Description:    closes the connection to the database.
	     */
		void closeConnection();

		/**
	     *  Method:         connect
	     *  Description:    creates the connection to the database.
	     */
		void connect();

		/**
		 * Method:			update
		 * Description:		Executes an update statement on the previously created connection.
		 */
		void update(String sqlUpdate);

		/**
	     *  Method:         displayError
	     *  Description:    displays all the error messages
	     *  @param			e : The String representation of the error that was caught
	     *
	     */
		void displayError(String e);

		/**
	     *  Method:         query
	     *  Description:    sends the query to the database
	     *  @param			query : The query to be executed at the server
	     *	@return			ResultSet : The ResultSet of the query
	     */		
		ResultSet query(String query);
		
	}
