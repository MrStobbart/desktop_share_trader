package viewCont;
import model.DbConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
 
/**
 *  Class:         DelivController
 *
 *  Description:   This class handles the delivery element of the NavigationView.
 *   
 */		
public class DelivController 
{
	private DbConnector ic = new DbConnector();
	private JButton b1 = new JButton("Check");;
	private JTextField jt1 = new JTextField("Please Enter Customer ID");
	private JFrame view = new JFrame();	
	
	/**
	*  Method:         showView
	*
	*  Description:    Starts the process of setting the finding out if the customer has ok finacial status
	*  If so delivery can be offered.
	*  
	*/			
	public void start()
	{
		connect();
		initGui();
		//closeConnection();
	}
	
	/**
	*  Method:         getDetails
	*
	*  Description:    This is called from the button click. It then gets the CustomerID, Financial status and executes the query. 
	*  
	*/	
	public void getDetails()
	{
		int custID = getCustID();
		String sqlQuery = "SELECT * from Customer WHERE CustomerID = " + custID;
		boolean flag = checkCustomerFinance(sqlQuery);
		if (flag)
			JOptionPane.showMessageDialog(null, "The Customers finance is OK - Free Delivery", "Result", JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, "The Customers finance is not OK- Charged Delivery ", "Result", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	*  Method:         initGui
	*
	*  Description:    Inititates the main  GUI
	*  
	*/	
	public void initGui()
	{
		jt1.setBounds(20,20,200,20);
		b1.setBounds(new Rectangle(20, 40, 200, 40));
		b1.addActionListener(new ActionListener()
			    {	         
			    	public void actionPerformed(ActionEvent e)
			    	{
			    		view.repaint();
			    		getDetails();
			    	}
			    });
		view.getContentPane().add(jt1);
		view.getContentPane().add(b1);
		view.getContentPane().setLayout(null);
		view.setSize(new Dimension(300, 200));
		view.setTitle("Delivery Controller");
		view.addWindowListener(new CloseChild(view));
		view.setVisible(true);
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
	}
	
	/**
	    *  Method:         getCustID
	    *
	    *  Description:    Returns the CustomerID entered via through the GUI
	    *  
	    */		
	public int getCustID()
	{
		return Integer.parseInt(jt1.getText());
	}
	/**
	    *  Method:         checkCustomerFinance()
	    *
	    *  Description:    Queries the database and returns true if Customer has OK fiancial
	    *  					status and False if customer does not. 
	    *  
	    *  @param			sqlQuery  : SQL Query to execute
	    *  @return			boolean  
	    *  @throws			SQLException
	    */		
	public boolean checkCustomerFinance(String sqlQuery)
	{
		boolean flag = false;
		try
		{
			ResultSet local = executeSQL(sqlQuery);
			local.next();
			if (local.getString("FinanceOK").equals("True"))
			{
				flag = true;
			}
			else
			{
				flag = false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
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
	
}
