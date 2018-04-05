package viewCont;

import enums.ShareholderActions;
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
public class ShareholdersView implements Observer{

    private JButton buttonBack = new JButton("Back");
	private JButton buttonShowTrades = new JButton("Show trades of selected shareholder");

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
        table.removeColumn(table.getColumnModel().getColumn(0));
        scrollPane = new JScrollPane(table);

        buttonShowTrades.setActionCommand(ShareholderActions.SHAREHOLDER_TRADES.name());
        buttonShowTrades.setBounds(new Rectangle(20, 230, 250, 40));

        buttonBack.setActionCommand(ShareholderActions.BACK.name());
        buttonBack.setBounds(new Rectangle(20, 280, 100, 40));

		scrollPane.setBounds(new Rectangle(20,20,550,200));

        frame.getContentPane().add(buttonBack);
        frame.getContentPane().add(buttonShowTrades);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().setLayout(null);
		frame.setSize(new Dimension(600, 400));
		frame.setTitle("Trades Information");
		frame.addWindowListener(new CloseChild(frame));
		frame.setVisible(true);
	}

	public void addListener(ActionListener actionListener){

	    buttonShowTrades.addActionListener(actionListener);
	    buttonBack.addActionListener(actionListener);
    }


	@Override
	public void update(Observable observable, Object arg) {
		if(arg instanceof String){
		    setTitle((String)arg);
        }else{
            initView((AbstractTableModel) arg);
        }
	}

    private void setTitle(String title) {
	    frame.setTitle(title);
    }

    public void setModel(ShareInformationModel shareInformationModel){
	    this.shareInformationModel = shareInformationModel;
    }

    public String getSelectedShareholderId() {

        int selectedRow = table.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(null, "Please select a broker", "Error", JOptionPane.PLAIN_MESSAGE);
            return null;
        }

        return (String) table.getModel().getValueAt(selectedRow, 0);
    }
}
