package model;

import java.sql.*;


public class DbConnector implements DatabaseConnection{

    private Connection connection;

    /**
     *  Method:         displayError
     *
     *  Description:    displays all the error messages 
     *  
     *  @param			e : The String representation of the error that was caught
     *
     */
	public void displayError (String e){

		System.out.println(e);
	}

	/**
     *  Method:         connect
     *
     *  Description:    creates the connection to the database.
     *
     */
	public void connect(){
		String url = "jdbc:mysql://localhost:3306/share_trader_local";
		String user = "admin";
		String password = "default";


		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e){
			displayError ("Can't load driver");
			displayError (e.getMessage());
		}

		try{
			this.connection = DriverManager.getConnection(url, user, password);
		}
		catch (SQLException e){
			displayError(e.getMessage());
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
	public ResultSet query(String sqlQuery){

	    ResultSet resultSet = null;
		Statement statement;

		try{
			statement = this.connection.createStatement();
			resultSet = statement.executeQuery(sqlQuery);
		}
		catch (SQLException e){
			displayError(e.getMessage());
		}
		return resultSet;
	}

	public void update(String sqlUpdate){

	    Statement statement;

	    try{
	        statement = this.connection.createStatement();
	        statement.executeUpdate(sqlUpdate);
        } catch (SQLException e){
	        displayError(e.getMessage());
        }

    }

    /**
     *  Method:         closeConnection
     *
     *  Description:    closes the connection to the database.
     *
     */
	public void closeConnection(){
		try{
			this.connection.close();
		}
		catch (SQLException e){
			displayError(e.getMessage());
		}
	}

}
