package viewCont;
import enums.NavigationActions;
import util.StaticMethods;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *  This class is the controller on the system.  It also initites the main Gui on the system.
 */
;

public class NavigationView extends JFrame {
	private ShareInformationView fc;
	private JButton buttonShareInformation = new JButton("Share information");
	private JButton buttonTrades = new JButton("Trades");
	private JButton buttonBrokers = new JButton("Brokers");
	private JButton buttonShareholders = new JButton("Shareholders");
	private JButton buttonExit = new JButton("Exit");

    /**
     *  Method:         NavigationView (constructor)
     *
     *  Description:    Initialise the Controller object.
     *
     */
	public NavigationView(){
		fc = new ShareInformationView();
	}
	

    /**
     *  Method:         initView
     *  
     *  Description:    Calls initView() and centres the Gui();
     *
     */
	public void showView() {
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
	      this.setSize(new Dimension(790, 130));
	      this.setTitle("Share trader navigation");
	      this.addWindowListener(new CloseParent());
	      this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	          

	      
	      buttonShareInformation.setBounds(new Rectangle(20, 20, 150, 50));
	      this.getContentPane().add(buttonShareInformation);
	      
	      buttonTrades.setBounds(new Rectangle(170, 20, 150, 50));
	      this.getContentPane().add(buttonTrades);
	      
	      buttonBrokers.setBounds(new Rectangle(320, 20, 150, 50));
	      this.getContentPane().add(buttonBrokers);
	      
	      buttonShareholders.setBounds(new Rectangle(470, 20, 150, 50));
	      this.getContentPane().add(buttonShareholders);
	      
	      buttonExit.setBounds(new Rectangle(620, 20, 150, 50));
	      this.getContentPane().add(buttonExit);

	      // TODO remove this
//	      b6.setBounds(new Rectangle(770, 20, 150, 50));
	}

	public void addListener(ActionListener actionListener){

	    buttonShareInformation.setActionCommand(NavigationActions.SHARE_INFORMATION.name());
        buttonShareInformation.addActionListener(actionListener);

        buttonTrades.setActionCommand(NavigationActions.TRADES.name());
        buttonTrades.addActionListener(actionListener);

        buttonBrokers.setActionCommand(NavigationActions.BROKERS.name());
        buttonBrokers.addActionListener(actionListener);

        buttonShareholders.setActionCommand(NavigationActions.SHAREHOLDERS.name());
        buttonShareholders.addActionListener(actionListener);

        buttonExit.setActionCommand(NavigationActions.EXIT.name());
        buttonExit.addActionListener(actionListener);

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

}
