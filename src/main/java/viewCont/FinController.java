package viewCont;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


import model.DbConnector;

/** 
 *  Class:         FinController
 *
 *  Description:   This class handles the financial element of the Client.
 *   
 */		
public class FinController 
{
	private DbConnector ic = new DbConnector();
	private JButton b1 = new JButton("Update");;
	private JTextField jt1 = new JTextField("Please Enter Customer ID");
	private JTextField jt2 = new JTextField("Please enter True or False");
	private JFrame view = new JFrame();
	private String[] viewColumns = {"CustomerID","Name","FinacialStatus"};
	private String[][] viewData = new String[12][12];	
	private JTable jt = new JTable(viewData,viewColumns);
	private JScrollPane jsp = new JScrollPane(jt);

	/**
	*  Method:         start
	*
	*  Description:    Starts the process of setting the fiancial status of a customer
	*  
	*/		
	public void start()
	{
		connect();
		initGui();
		displayCustomers();
		//closeConnection();
	}
	
	public void displayCustomers()
	{
		try
		{
			ResultSet local = ic.query("Select * from Customer Order by CustomerID");
	  		int x = 1;
			int y = 0;
			
			while(local.next())
			{
				viewData[x][y] = "" + local.getInt("CustomerID");
				y++;
				viewData[x][y] = "" + local.getString("Name");
				y++;
				viewData[x][y] = "" + local.getString("FinanceOK");
				y++;
				x++;
				y = 0;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
	    *  Method:         getFinance
	    *
	    *  Description:    Returns the Finace String entered via through the GUI
	    *  
	    */		
	public String getFinance()
	{
		return jt2.getText();
	}
	
	
	/**
	    *  Method:         setFinance
	    *
	    *  Description:    Sets the finance of the customer by updating the database with the data entered through the GUI.
	    *  
	    */
	public void setFinance()
	{
		int custID = getCustID();
		
		String finance = getFinance();
		String sqlQuery = "UPDATE Customer set FinanceOK = '"+ finance +"' where CustomerID = " + custID;
		executeSQL(sqlQuery);
		resetButtons();
	}
	/**
	*  Method:         resetButtons
	*
	*  Description:    Resets all the buttons back to the value at creation.
	*  
	*/	
	public void resetButtons()
	{
		jt1.setText("Please Enter Customer ID");
		jt2.setText("Please enter True or False");
	}
	
	/**
	*  Method:         initGui
	*
	*  Description:    Inititates the main GUI
	*  
	*/	
	public void initGui()
	{
		jsp.setBounds(new Rectangle(20,20,290,200));
		jt1.setBounds(20,230,290,20);
		jt2.setBounds(20,250,290,20);
		b1.setBounds(new Rectangle(20, 270, 290, 40));
		b1.addActionListener(new ActionListener()
			    {	         
			    	public void actionPerformed(ActionEvent e)
			    	{
			    		System.out.println("!!!!!!");
			    		setFinance();
			    		displayCustomers();
			    		view.repaint();
			    	}
			    });
		view.getContentPane().add(jt1);
		view.getContentPane().add(jt2);
		view.getContentPane().add(b1);
		view.getContentPane().add(jsp);
		view.getContentPane().setLayout(null);
		view.setSize(new Dimension(600, 400));
		view.setTitle("Finacial Approval");
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
