package viewCont;

import enums.ShareInformationActions;
import model.ShareInformationModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/** 
 *  Class:         ShareInformationView
 *
 *  Description:   This class handles the financial element of the NavigationView.
 *   
 */		
public class TradesView implements Observer{

    private JButton buttonBack = new JButton("Back");

	private JFrame frame = new JFrame();
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

	public void hideView(){
	    frame.setVisible(false);
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

        buttonBack.setActionCommand(ShareInformationActions.BACK.name());
        buttonBack.setBounds(new Rectangle(20, 230, 100, 40));

		scrollPane.setBounds(new Rectangle(20,20,550,200));

        frame.getContentPane().add(buttonBack);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().setLayout(null);
		frame.setSize(new Dimension(600, 400));
		frame.setTitle("Trades Information");
		frame.addWindowListener(new CloseChild(frame));
		frame.setVisible(true);
	}

	public void addListener(ActionListener actionListener){
	    buttonBack.addActionListener(actionListener);
    }

    public String getSelectedRowShareCode(){

	    int selectedRow = table.getSelectedRow();
	    if(selectedRow == -1){
            JOptionPane.showMessageDialog(null, "Please select a share", "Error", JOptionPane.PLAIN_MESSAGE);
	        return null;
        }
	    return (String) table.getValueAt(selectedRow, 0);
    }


	@Override
	public void update(Observable observable, Object arg) {
        initView((AbstractTableModel) arg);
	}

	public void setModel(ShareInformationModel shareInformationModel){
	    this.shareInformationModel = shareInformationModel;
    }
}