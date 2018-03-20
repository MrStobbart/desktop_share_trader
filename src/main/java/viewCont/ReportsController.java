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
 *  Class:         ReportsController
 *
 *  Description:   This class generates the queries for the database and displays the reports.
 *   
 */		
public class ReportsController 
{
	private DbConnector ic = new DbConnector();
	private JButton b1 = new JButton("Report 1");;
	private JButton b2 = new JButton("Report 2");
	private JButton b3 = new JButton("Report 3");
	private String[] viewColumns = {" "," "," "," "," "," "," "," "};
	private String[][] viewData = new String[30][10];
	
	private JFrame view = new JFrame();
	private  JTextField jt1 = new JTextField("Please Enter PID",1);
	private JTable vpjt = new JTable(viewData,viewColumns);
	private JScrollPane viewsp = new JScrollPane(vpjt);
	
	
	

	
	/**
	*  Method:         showView
	*
	*  Description:    Starts the process of generating reports by displaying a report menu for the user to select which report they want. 
	*  
	*/		
	public void start()
	{
		ic.connect();
		initGui();
		//ic.closeConnection();
	}
	
	/**
	*  Method:         initGui
	*
	*  Description:    Inititates the main GUI
	*  
	*/	
	public void initGui()
	{
		viewsp.setBounds(20,100,600,400);
		view.getContentPane().add(viewsp);
		view.getContentPane().setLayout(null);
		view.setSize(new Dimension(650,600));
		view.setTitle("Reports");
		view.addWindowListener(new CloseChild(view));
	 
	      b1.addActionListener(new ActionListener()
	      {	         
	         public void actionPerformed(ActionEvent e)
	         {
	        	 resetTable();
	        	 projectedIncome(); 
	        	 view.repaint();
	         }
	      });
	            
	      b2.addActionListener(new ActionListener()
	      {	         
	         public void actionPerformed(ActionEvent e)
	         {
	        	 resetTable();
	        	 projectedIncomeSale();
	        	 view.repaint();
	         }
	      });
	      
	      b3.addActionListener(new ActionListener()
	    	      {	         
	    	         public void actionPerformed(ActionEvent e)
	    	         {
	    	        	 resetTable();
	    	        	 transactions();
	    	        	 view.repaint();
	    	         }
	    	      });
	    	      
	      b1.setBounds(new Rectangle(20, 20, 150, 50));
	      view.getContentPane().add(b1);
	      
	      b2.setBounds(new Rectangle(170, 20, 150, 50));
	      view.getContentPane().add(b2);	

	      b3.setBounds(new Rectangle(320, 20, 150, 50));
	      view.getContentPane().add(b3);	
	      view.setVisible(true);
	}

	/**
	*  Method:         resetTable
	*
	*  Description:    Resets the table data to null valuse 
	*  
	*/	
	public void resetTable()
	{
		for (int x = 0;x<30;x++)
		{
			for(int y = 0;y<10;y++)
			{
				viewData [x][y] = "";
			}
		}
	}

	/**
	*  Method:         projectedIncome
	*
	*  Description:    Creates a Query that calculates the amount that the shop will make with current stock. Does not consider sale items 
	*  
	*  @throws 		   sqlException
	*  
	*/			
	public void projectedIncome()
	{
		try
		{
			ResultSet local = ic.query(" SELECT Name,BaseCost,costAdjustment,Quantity,SUM((BaseCost+CostAdjustment)*Quantity) as ProjectedIncome FROM StoreProduct sp,Products p, StoreQuantity sq WHERE sq.PID = p.PID AND p.PID = sp.PID  GROUP BY name,BaseCost,costAdjustment,Quantity");
		
			viewData[1][0] = "Name";
			viewData[1][1] = "Base";
			viewData[1][2] = "Diff";
			viewData[1][3] = "Quant";
			viewData[1][4] = "Ex Inc";
			double total = 0;
			double localTotal;
			
	  		int x = 3;
			int y = 0;
			
			while(local.next())
			{
				viewData[x][y] = "" + local.getString("Name");
				y++;
				viewData[x][y] = "€" + local.getDouble("BaseCost");
				y++;
				viewData[x][y] = "€" + local.getDouble("CostAdjustment");
				y++;
				viewData[x][y] = "" + local.getInt("Quantity");
				y++;
				localTotal = local.getDouble("ProjectedIncome");
				viewData[x][y] = "€" + (int)(localTotal*100)/ 100.0;
				total += localTotal;
				x++;
				y = 0;
			}


			viewData [x+3][0] = "Total";
			viewData [x+3][1] = "€" + total;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	*  Method:         projectedIncomeSale
	*
	*  Description:    Creates a Query that calculates the amount that the shop will make with current stock including sale items 
	*  
	*  @throws 		   sqlException
	*  
	*/			
	public void projectedIncomeSale()
	{
		try
		{
			String sqlQuery = "SELECT Products.Name, Sum([StoreQuantity].[Quantity]*(([Products].[BaseCost]+[StoreProduct].[CostAdjustment]))) AS WithoutSale, Sum([StoreQuantity].[Quantity]*(([Products].[BaseCost]+[StoreProduct].[CostAdjustment]))-([StoreQuantity].[Quantity]*(([Products].[BaseCost]+[StoreProduct].[CostAdjustment]))*([sale]/100)))AS WithSale, StoreQuantity.Quantity, Products.BaseCost, StoreProduct.CostAdjustment,sale";
					sqlQuery +=  " FROM (StoreProduct INNER JOIN (Products INNER JOIN StoreQuantity ON Products.PID = StoreQuantity.PID) ON StoreProduct.PID = StoreQuantity.PID) LEFT JOIN Sale ON Products.PID = Sale.PID GROUP BY Products.Name, StoreQuantity.Quantity, Products.BaseCost, StoreProduct.CostAdjustment,sale";

			ResultSet local = ic.query(sqlQuery);
		
			viewData[1][0] = "Name";
			viewData[1][1] = "Base";
			viewData[1][2] = "Diff";
			viewData[1][3] = "Quant";
			viewData[1][4] = "Sale";
			viewData[1][5] = "Income";
			
			double total = 0;
			int x = 3;
			int y = 0;
			while (local.next())
			{
				viewData[x][y] = "" + local.getString("Name");
				y++;
				viewData[x][y] = "€" + local.getDouble("BaseCost");
				y++;
				viewData[x][y] = "€" + local.getDouble("CostAdjustment");
				y++;
				viewData[x][y] = "" + local.getInt("Quantity");
				y++;
				viewData[x][y] = "" + local.getDouble("sale") + "%";
				y++;
				double withsale = local.getDouble("WithSale");
				double withoutsale = local.getDouble("WithoutSale");
				if (withsale>0)
				{	
					viewData[x][y] = "€" + (int)(withsale*100)/ 100.0;
					total += withsale;
				}	
				else
				{	
					viewData[x][y] = "€" + (int)(withoutsale*100)/ 100.0;
					total += withoutsale;
				}	
				x++;
				y = 0;
				
			}
	
			viewData [x+3][0] = "Total";
			viewData [x+3][1] = "€" + total;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	*  Method:         transactions
	*
	*  Description:    Creates a report with all transactions on the system. 
	*  
	*  @throws 		   sqlException
	*  
	*/			
	public void transactions()
	{
				
		try
		{
			String sqlQuery =  "SELECT Customer.Name as CName, Customer.FinanceOK as FOK, TransactionLine.TID, Sum([CostAdjustment]+[baseCost]+[Cost]) AS totalCost, Delivery.ZoneID, Delivery.Cost, StoreProduct.PID, Products.Name, Products.BaseCost, StoreProduct.CostAdjustment";
			sqlQuery += " FROM Delivery INNER JOIN (((((Customer INNER JOIN [Transaction] ON Customer.CustomerID = Transaction.CustomerID) INNER JOIN TransactionLine ON Transaction.TID = TransactionLine.TID) INNER JOIN StoreProduct ON TransactionLine.PID = StoreProduct.PID) INNER JOIN Products ON TransactionLine.PID = Products.PID) INNER JOIN zoneProd ON TransactionLine.PID = zoneProd.PID) ON Delivery.ZoneID = zoneProd.ZoneID";
			sqlQuery += " WHERE (((Customer.FinanceOK)='False')) GROUP BY Customer.Name, Customer.FinanceOK, TransactionLine.TID, Delivery.ZoneID, Delivery.Cost, StoreProduct.PID, Products.Name, Products.BaseCost, StoreProduct.CostAdjustment HAVING (((Customer.FinanceOK)='False'))";
			sqlQuery += " UNION 		SELECT Customer.Name, Customer.FinanceOK, TransactionLine.TID, Sum([CostAdjustment]+[baseCost]) AS Expr1, Delivery.ZoneID, Delivery.Cost, StoreProduct.PID, Products.Name, Products.BaseCost, StoreProduct.CostAdjustment";
			sqlQuery += " FROM Delivery INNER JOIN (((((Customer INNER JOIN [Transaction] ON Customer.CustomerID = Transaction.CustomerID) INNER JOIN TransactionLine ON Transaction.TID = TransactionLine.TID) INNER JOIN StoreProduct ON TransactionLine.PID = StoreProduct.PID) INNER JOIN Products ON TransactionLine.PID = Products.PID) INNER JOIN zoneProd ON TransactionLine.PID = zoneProd.PID) ON Delivery.ZoneID = zoneProd.ZoneID";
			sqlQuery += " WHERE (((Customer.FinanceOK)='True')) GROUP BY Customer.Name, Customer.FinanceOK, TransactionLine.TID, Delivery.ZoneID, Delivery.Cost, StoreProduct.PID, Products.Name, Products.BaseCost, StoreProduct.CostAdjustment HAVING (((Customer.FinanceOK)='True'))ORDER BY TransactionLine.TID; ";

			ResultSet tr = ic.query(sqlQuery);
		
			viewData[1][0] = "C Name";
			viewData[1][1] = "TID";
			viewData[1][2] = "P.Name";
			viewData[1][3] = "FinanceOK";
			viewData[1][4] = "Delivery Cost";
			viewData[1][5] = "BaseCost";
			viewData[1][6] = "Cost Adj";
			viewData[1][7] = "Total Cost";
			
			int tidc = -1;
			int tid =0;
			double ttotal = 0;
			double tot = 0;
			double ctotal = 0;
			double delivery = 0;
			int x = 3;
			int y = 0;
			while (tr.next())
			{
				tid = tr.getInt("TID");
				if ((tid!=tidc) && (tidc!=-1))
				{	
					viewData [x][5] = "Transaction";
					viewData [x][6] = "Total";
					viewData [x][7] = "€" + ttotal;
					x++;
					ttotal = 0;
				}	
				viewData [x][y] = "" + tr.getString("CName");
				y++;
				viewData [x][y] = "" + tid;
				y++;
				viewData [x][y] = "" + tr.getString("Name");
				y++;
				String fok = tr.getString("FOK");
				viewData [x][y] = "" + fok; 
				y++;
				if (fok.equals("True"))
					delivery = 0;
				else
					delivery = tr.getDouble("Cost");
				viewData [x][y] = "€" + delivery;
				y++;

				double basecost = tr.getDouble("basecost");
				viewData [x][y] = "€" + basecost;
				y++;
				double costAdjust = tr.getDouble("CostAdjustment");
				viewData [x][y] = "€" + costAdjust;
				y++;
				viewData [x][y] = "€" + (basecost + costAdjust + delivery);
				y++;
				ttotal += (basecost + costAdjust + delivery);
				ctotal += (basecost + costAdjust + delivery);
				tidc = tid;
				x++;
				y=0;
			}	
			
			viewData [x][5] = "Transaction";
			viewData [x][6] = "Total";
			viewData [x][7] = "€" + ttotal;
			
			viewData [x+2][5] = "Complete";
			viewData [x+2][6] = "Total";
			
			viewData [x+2][7] = "€" + ctotal;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
