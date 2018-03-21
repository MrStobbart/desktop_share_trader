package viewCont;
import enums.NavigationActions;
import util.StaticMethods;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  This class is the controller on the system.  It also initites the main Gui on the system.
 */
;

public class NavigationView extends JFrame
{
	private PriceController pc;
	private ShareInformationView fc;
	private InvController invc;
	private DelivController delc;
	private ReportsController rc;
	private JButton buttonShareInformation = new JButton("Share information");;
	private JButton b2 = new JButton("Inventory Control");
	private JButton b3 = new JButton("Delivery Charges");
	private JButton b4 = new JButton("Finacial Approval");;
	private JButton b5 = new JButton("Reports & Analysis");
	private JButton b6 = new JButton("Exit");

    /**
     *  Method:         NavigationView (constructor)
     *
     *  Description:    Initialise the Controller object.
     *
     */
	public NavigationView(){
		pc = new PriceController();
		fc = new ShareInformationView();
		delc = new DelivController();
		rc = new ReportsController();
		invc = new InvController();
	}
	

    /**
     *  Method:         initView
     *  
     *  Description:    Calls initView() and centres the Gui();
     *
     */
	public void showView() {
		System.out.println("App started");
		initGui();
		StaticMethods.positionAndShow(this);
	}

	public void hideView(){
		this.setVisible(false);
	}
	
    /**
     *  Method:         initView()
     *
     *  Description:    Initialise the Gui
     *
     */
	public void initGui()
	{
		
	      this.getContentPane().setLayout(null);
	      this.setSize(new Dimension(940, 130));
	      this.setTitle("Desktop share trader prototype");
	      this.addWindowListener(new CloseParent());
	          

	      
	      buttonShareInformation.setBounds(new Rectangle(20, 20, 150, 50));
	      this.getContentPane().add(buttonShareInformation);
	      
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

	public void addListener(ActionListener actionListener){

	    buttonShareInformation.setActionCommand(NavigationActions.SHARE_INFORMATION.name());
        buttonShareInformation.addActionListener(actionListener);

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
