package viewCont;
import java.sql.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import model.DbConnector;
/**
 *  Class:         PriceController
 *
 *  Description:   This class handles all elements of the pricing for the store.
 *   
 */		
public class PriceController extends JFrame 
{
	private DbConnector ic = new DbConnector();
	private JButton b1 = new JButton("Products");;
	private JButton b2 = new JButton("Sale's");
	private JButton b3 = new JButton("Amend Product");
	private JButton b4 = new JButton("Add Sale item");
	private String[] viewProductsColumns = {"PID","Name","Weight","Unit","base Cost","Store Cost"};
	private String[][] viewProductsData = new String[10][10];
	
	private JFrame viewP = new JFrame();
	private  JTextField jt1 = new JTextField("Please Enter price adjustment",1);
	private JTextField jt2 = new JTextField("Please enter Product ID",1);
	private JTable vpjt = new JTable(viewProductsData,viewProductsColumns);
	private JScrollPane viewsp = new JScrollPane(vpjt);
	private String[] viewAllSalesColumns = {"Name","ShopCost","sale %","Sale Cost"};
	private String[][] viewAllSalesData = new String[7][8];
	private JTextField jt3 = new JTextField("Sale %",1);
	private JTextField jt4 = new JTextField("Product ID",1);
	private JFrame viewAs = new JFrame();
	private JTable asjt = new JTable(viewAllSalesData,viewAllSalesColumns);
	private JScrollPane viewAllsp = new JScrollPane(asjt);
    
	/**
	*  Method:         resetButtons
	*
	*  Description:    Resets all the buttons back to the value at creation.
	*  
	*/	
	public void resetButtons()
	{
		jt1.setText("Please Enter price adjustment");
		jt2.setText("Please enter Product ID");
		jt3.setText("Sale %");
		jt4.setText("Product ID");
	}
	/**
	*  Method:         PriceController Constructore
	*
	*  Description:    Inititates all the Gui but does not make them visible.
	*  
	*/	
    public PriceController()
    {
    	initMainGui();
    	initViewPGui();
    	initAllSGui();
    	//initAmendGui();
    }

	/**
	*  Method:         start
	*
	*  Description:    Starts the price Controller. 
	*  
	*/		
	public void start()
	{
		ic.connect();
		this.setVisible(true);
	}	

	/**
	*  Method:         initAllSGui
	*
	*  Description:    Inititates the Sales GUI
	*  
	*/	
	public void initAllSGui()
	{
			viewAllsp.setBounds(new Rectangle(20, 20, 650, 170));
			jt3.setBounds(20,150,330,20);
			jt4.setBounds(20,170,330,20);
			b4.setBounds(new Rectangle(350, 150, 320, 40));
			b4.addActionListener(new ActionListener()
				    {	         
				    	public void actionPerformed(ActionEvent e)
				    	{
				    		viewAs.setVisible(true);
				    		addProductToSaleGroup();
				    		viewAs.repaint();
				    	}
				    });
			viewAs.getContentPane().add(jt3);
			viewAs.getContentPane().add(jt4);
			viewAs.getContentPane().add(b4);
			viewAs.getContentPane().add(viewAllsp);
			viewAs.getContentPane().setLayout(null);
			viewAs.setSize(new Dimension(700, 300));
			viewAs.setTitle("All Sale Items");
			viewAs.addWindowListener(new CloseChild(viewAs));
	}      
	/**
	*  Method:         initViewPGui
	*
	*  Description:    Inititates the Products GUI
	*  
	*/	
	public void initViewPGui()
	{
			jt1.setBounds(20,150,330,20);
			jt2.setBounds(20,170,330,20);
			viewsp.setBounds(new Rectangle(20, 20, 650, 170));
		    b3.addActionListener(new ActionListener()
		    {	         
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		viewP.setVisible(true);
		    		amendIndividualPrice();
		    		viewP.repaint();
		    	}
		    });
		    b3.setBounds(new Rectangle(350, 150, 320, 40));
		    viewP.getContentPane().add(b3);
		    viewP.getContentPane().add(jt1);
		    viewP.getContentPane().add(jt2);
			viewP.getContentPane().add(viewsp);
			viewP.getContentPane().setLayout(null);
			viewP.setSize(new Dimension(700, 300));
			viewP.setTitle("All Products");
			viewP.addWindowListener(new CloseChild(viewP));
	}   
	
	/**
	*  Method:         initMainGui
	*
	*  Description:    Inititates the main Price Controller GUI
	*  
	*/		
	public void initMainGui()
	{
	      this.getContentPane().setLayout(null);
	      this.setSize(new Dimension(350,150));
	      this.setTitle("Price Control");
	      this.addWindowListener(new CloseChild(this));
	 
	      b1.addActionListener(new ActionListener()
	      {	         
	         public void actionPerformed(ActionEvent e)
	         {
	        	 viewP.setVisible(true);
	        	 viewAllProducts(); 
	        	 viewP.repaint();
	         }
	      });
	            
	      b2.addActionListener(new ActionListener()
	      {	         
	         public void actionPerformed(ActionEvent e)
	         {
	        	 viewAs.setVisible(true);
	        	 viewAllSaleGroups();
	        	 viewAs.repaint();
	         }
	      });
	      
	      b1.setBounds(new Rectangle(20, 20, 150, 50));
	      this.getContentPane().add(b1);
	      
	      b2.setBounds(new Rectangle(170, 20, 150, 50));
	      this.getContentPane().add(b2);	
	}
	
	/**
	*  Method:         viewAllProducts
	*
	*  Description:    Display all the products in a JTable on the GUI
	*  @throws			SQLException
	*/	
	public void viewAllProducts()
	{
		try
		{
			ResultSet local = ic.query("Select sp.PID ,Name,Weight,basecost as bc2 , UnitPerPack,BaseCost,costAdjustment from StoreProduct sp,Products p where sp.PID = p.PID Order by sp.PID");
			System.out.println("Products");
	  		int x = 1;
			int y = 0;
			
			while(local.next())
			{
				viewProductsData[x][y] = "" + local.getInt("PID");
				y++;
				viewProductsData[x][y] = "" + local.getString("Name");
				y++;
				viewProductsData[x][y] = "" + local.getInt("Weight");
				y++;
				viewProductsData[x][y] = "" + local.getInt("UnitPerPack");
				y++;
				viewProductsData[x][y] = "" + local.getInt("bc2");
				y++;
				double cost = local.getDouble("BaseCost") + local.getDouble("CostAdjustment");
				viewProductsData[x][y] = "€" + cost;
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
	*  Method:         viewAllSaleGroups
	*
	*  Description:    Display all the sale items in a JTable on the GUI
	*  @throws			SQLException
	*/		
	public void viewAllSaleGroups()
	{
		ResultSet local = ic.query("Select Name,s.PID,SUM(baseCost +costAdjustment) as shopCost,sale,SUM(sale/100*(baseCost+costAdjustment)) as  saleCost from Sale s,StoreProduct sp,Products p where sp.PID = s.PID AND s.PID = p.PID GROUP BY Name,s.PID,sale");
		System.out.println("Sale");
		try
		{
	  		int x = 1;
			int y = 0;
			
			while(local.next())
			{
				viewAllSalesData[x][y] = "" + local.getString("Name");
				y++;
				viewAllSalesData[x][y] = "€" + local.getDouble("ShopCost");
				y++;
				viewAllSalesData[x][y] = local.getInt("sale") + "%";
				y++;
				viewAllSalesData[x][y] = "" + "€" + local.getDouble("saleCost");
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
	*  Method:         addProductToSaleGroup
	*
	*  Description:    Adds a sale item to the sale table on the local table.
	*
	*/		
	public void addProductToSaleGroup()
	{
		String possibleError = StaticMethods.checkIntInput(jt3.getText());
		boolean percentFlag = StaticMethods.checkError(possibleError);
		possibleError += StaticMethods.checkIntInput(jt4.getText());
		boolean pidFlag = StaticMethods.checkError(possibleError);
		if ((percentFlag) && (pidFlag))
		{	
			int percent = Integer.parseInt(jt3.getText());
	        int pidToAdd = Integer.parseInt(jt4.getText()); 
	        ResultSet local = ic.query("INSERT INTO SALE (Pid,Sale) VALUES ('" + pidToAdd + "','" + percent + "')");
	        viewAllSaleGroups();
			vpjt.repaint();
			resetButtons();
		}
		else
		{
			JOptionPane.showMessageDialog(null, possibleError, "Result", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	*  Method:         amendIndividualPrice
	*
	*  Description:    Amends a single Product item in the StoreProducts table.
	*
	*/			
	public void amendIndividualPrice()
	{
		String possibleError = StaticMethods.checkIntInput(jt1.getText());
		boolean productFlag = StaticMethods.checkError(possibleError);
		possibleError += StaticMethods.checkIntInput(jt2.getText());
		boolean pidFlag = StaticMethods.checkError(possibleError);
		if ((productFlag) && (pidFlag))
		{	
			double costAdjustment = Double.parseDouble(jt1.getText());
	        int pidToAlter = Integer.parseInt(jt2.getText()); 
			ResultSet local = ic.query("UPDATE StoreProduct set costAdjustment = '"+ costAdjustment + "' WHERE PID = " + pidToAlter);
			viewAllProducts();
			vpjt.repaint();
			resetButtons();
		}
		else
		{
			JOptionPane.showMessageDialog(null, possibleError, "Result", JOptionPane.INFORMATION_MESSAGE);
		}
	}	
}
