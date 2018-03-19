package viewCont;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import model.ExternalConnect;
import model.DbConnector;

/**
 *  Class:         InvController
 *
 *  Description:   This class handles the Inventory element of the Client.
 *   
 */		
public class InvController 
{

	private ExternalConnect ex = new ExternalConnect();
	private DbConnector ic = new DbConnector();
	/**
	*  Method:         start
	*
	*  Description:    Starts the process of updating the local and external databases with the required quantities 
	*  
	*/			
	public void start()
	{
		connect();
		updateStock();
		sendMessageToPda();
		closeConnection();
	}

	/**
	*  Method:         updateStock
	*
	*  Description:    This method calculats the stock needed and available from the local and external databases and orders accordingly 
	*  
	*/		
	public void updateStock()
	{
		String sqlQuery = "SELECT PID,(desiredQuantity-Quantity) as toOrder,Quantity FROM StoreQuantity ORDER BY PID;";
		String sqlQuery2 = "SELECT PID,Quantity as avail FROM companyQuantity ORDER BY PID;";
		ResultSet loc = ic.query(sqlQuery);
		ResultSet ext = ex.query(sqlQuery2);
		try
		{
			while ((loc.next()) &&(ext.next()))
			{
				int lQuant = loc.getInt("Quantity");
				int pid = loc.getInt("PID");
				int needed = loc.getInt("toOrder");
				int avail = ext.getInt("avail");
				int ordered = 0;
				int companyQuant = 0;
				if (avail - needed < 0)
				{
					ordered = avail;
				}
				else
				{
					ordered = needed;
				}
				companyQuant = avail - ordered;
				sqlQuery = " UPDATE StoreQuantity SET Quantity = " + (ordered+lQuant) + " where Pid = " + pid;
				System.out.println(sqlQuery);
				sqlQuery2 = " UPDATE companyQuantity SET Quantity = " + (avail - ordered) + " where Pid = " + pid;
				System.out.println(sqlQuery2);
				ResultSet locc = executeSQL(sqlQuery);
				ResultSet extt = executeExtSQL(sqlQuery2);
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	    *  Method:         connect
	    *
	    *  Description:    Calls the IC method to connect to the database
	    *  
	    */		
	public void connect()
	{
		ic.connect();
		ex.connect();
	}

	/**
	    *  Method:         closeConnection
	    *
	    *  Description:    Calls the IC method to close the connection
	    *  
	    */		
	public void closeConnection()
	{
		ic.closeConnection();
		ex.closeConnection();
	}
	/**
	    *  Method:         executeExtSQl
	    *
	    *  Description:    Calls the External  method to connect to the database
	    *  @param		   sqlQuery : String sql query to be executed
	    *  @return			ResultSet
	    */		
	public ResultSet  executeExtSQL(String sqlQuery)
	{
		return ex.query(sqlQuery);
	}
		
	/**
	    *  Method:         executeSQl
	    *
	    *  Description:    Calls the IC method to connect to the database
	    *  @param		   sqlQuery : String sql query to be executed
	    *  @return			ResultSet
	    */		
	
	public ResultSet  executeSQL(String sqlQuery)
	{
		return ic.query(sqlQuery);
	}
	
	/**
	*  Method:         sendMessageToPda
	*
	*  Description:    This method sends a message to the managers PDA
	*  	*/
	public void sendMessageToPda()
	{
		JOptionPane.showMessageDialog(null, "The stock levels have been sent to the PDA", "PDA Message", JOptionPane.INFORMATION_MESSAGE);	
	}
}
