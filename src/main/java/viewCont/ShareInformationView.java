package viewCont;

import model.ShareInformationModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/** 
 *  Class:         ShareInformationView
 *
 *  Description:   This class handles the financial element of the NavigationView.
 *   
 */		
public class ShareInformationView implements Observer
{
	private JFrame view = new JFrame();
	private JTable table;
	private JScrollPane scrollPane;

	private ShareInformationModel shareInformationModel;

	/**
	*  Method:         initView
	*
	*  Description:    Starts the process of setting the fiancial status of a customer
	*  
	*/		
	public void start() {
	}
	


	/**
	*  Method:         initView
	*
	*  Description:    Inititates the main GUI
	*  
	*/
	public void initView(AbstractTableModel tableModel) {
	    table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

		scrollPane.setBounds(new Rectangle(20,20,550,200));
		view.getContentPane().add(scrollPane);
		view.getContentPane().setLayout(null);
		view.setSize(new Dimension(600, 400));
		view.setTitle("Finacial Approval");
		view.addWindowListener(new CloseChild(view));
		view.setVisible(true);
	}

	@Override
	public void update(Observable observable, Object arg) {
	    System.out.println("We got this");
        System.out.println(arg);
        initView((AbstractTableModel) arg);
	}

	public void setModel(ShareInformationModel shareInformationModel){
	    this.shareInformationModel = shareInformationModel;
    }
}
