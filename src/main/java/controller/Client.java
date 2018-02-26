package controller;
import javax.swing.*;
import viewCont.*;
import model.*;

import java.awt.*;
import java.awt.event.*;

/**
 *  This class is the controller on the system.  It also initites the main Gui on the system.
 */
;public class Client  extends JFrame 
{
	private PriceController pc;
	private FinController fc;
	private InvController invc;
	private DelivController delc;
	private ReportsController rc;
	private JButton b1 = new JButton("Price Control");;
	private JButton b2 = new JButton("Inventory Control");
	private JButton b3 = new JButton("Delivery Charges");
	private JButton b4 = new JButton("Finacial Approval");;
	private JButton b5 = new JButton("Reports & Analysis");
	private JButton b6 = new JButton("Exit");
	private String theName;
	   
    /**
     *  Method:         Client (constructor)
     *
     *  Description:    Initialise the Controller object.
     *
     */
	public Client(String aName)
	{
		theName = aName;
		pc = new PriceController();
		fc = new FinController();
		delc = new DelivController();
		rc = new ReportsController();
		invc = new InvController();
	}
	
	public String  getTheName()
	{
		return theName;
	}

    /**
     *  Method:         start 
     *  
     *  Description:    Calls initGui() and centres the Gui();
     *
     */
	public void start()
	{
		System.out.println("App started");
		initGui();
		StaticMethods.positionAndShow(this);
	}
	
    /**
     *  Method:         initGui()
     *
     *  Description:    Initialise the Gui
     *
     */
	public void initGui()
	{
		
	      this.getContentPane().setLayout(null);
	      this.setSize(new Dimension(940, 130));
	      this.setTitle("EStore Prototype");
	      this.addWindowListener(new CloseParent());
	          
	      b1.addActionListener(new ActionListener()
	      {	         
	         public void actionPerformed(ActionEvent e)
	         {
	            priceControl(); 	
	         }
	      });
	      
	      b2.addActionListener(new ActionListener()
	      {	         
	         public void actionPerformed(ActionEvent e)
	         {
	        	 invControl();
	         }
	      });
	      
	      b3.addActionListener(new ActionListener()
	      {	         
	         public void actionPerformed(ActionEvent e)
	         {
	        	 delivControl();
	         }
	      });
	      
	      b4.addActionListener(new ActionListener()
	      {	         
	         public void actionPerformed(ActionEvent e)
	         {
	        	 finControl();
	         }
	      });
	      
	      b5.addActionListener(new ActionListener()
	      {	         
	         public void actionPerformed(ActionEvent e)
	         {
	        	 reportsControl();
	         }
	      });
	      
	      b6.addActionListener(new ActionListener()
	      {	         
	         public void actionPerformed(ActionEvent e)
	         {
	        	 System.exit(0);
	         }
	      });
	      
	      b1.setBounds(new Rectangle(20, 20, 150, 50));
	      this.getContentPane().add(b1);
	      
	      b2.setBounds(new Rectangle(170, 20, 150, 50));
	      this.getContentPane().add(b2);
	      
	      b3.setBounds(new Rectangle(320, 20, 150, 50));
	      this.getContentPane().add(b3);
	      
	      b4.setBounds(new Rectangle(470, 20, 150, 50));
	      this.getContentPane().add(b4);	
	      
	      b5.setBounds(new Rectangle(620, 20, 150, 50));
	      this.getContentPane().add(b5);	
	      
	      b6.setBounds(new Rectangle(770, 20, 150, 50));
	      this.getContentPane().add(b6);	
	}

    /**
     *  Method:         priceControl()
     *
     *  Description:    Starts the price Controller
     *
     */
	public void priceControl()
	{
		pc.start();
	}
	
	/**
     *  Method:         invControl()
     *
     *  Description:    Starts the inventory Controller
     *
     */
	public void invControl()
	{
		invc.start();
	}
	
	/**
     *  Method:         finControl()
     *
     *  Description:    Starts the finacial Controller
     *
     */	
	public void finControl()
	{
		fc.start();
	}

	/**
     *  Method:         delivControl()
     *
     *  Description:    Starts the Delivery Controller
     *
     */
	public void delivControl()
	{
		delc.start();
	}
	
	/**
     *  Method:         reportsControl()
     *
     *  Description:    Starts the Reports Controller
     *
     */
	public void reportsControl()
	{
		rc.start();
	}
}
