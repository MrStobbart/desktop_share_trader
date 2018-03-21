package viewCont;

import enums.ShareInformationActions;
import model.ShareInformationModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Observable;
import java.util.Observer;

/** 
 *  Class:         ShareInformationView
 *
 *  Description:   This class handles the financial element of the NavigationView.
 *   
 */		
public class ShareInformationView implements Observer {

    private JButton buttonWatchShare = new JButton("Track selected share");
    private JButton buttonBack = new JButton("Back");
    private JLabel labelMinPrice = new JLabel("Min share price for alert");
    private JLabel labelMaxPrice = new JLabel("Max share price for alert");
    private SpinnerModel minPriceModel = new SpinnerNumberModel(100,  0, 10000, 1);
    private JSpinner spinnerMinPrice = new JSpinner(minPriceModel);
    private SpinnerModel maxPriceModel = new SpinnerNumberModel(250,  0, 10000, 1);
    private JSpinner spinnerMaxPrice = new JSpinner(maxPriceModel);

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

        labelMinPrice.setBounds(20, 230, 200, 20);
        spinnerMinPrice.setBounds(220,230,100,20);

        labelMaxPrice.setBounds(20, 250, 200, 20);
        spinnerMaxPrice.setBounds(220,250,100,20);


        buttonWatchShare.setActionCommand(ShareInformationActions.TRACK_SHARE.name());
        buttonWatchShare.setBounds(new Rectangle(140, 270, 180, 40));

        buttonBack.setActionCommand(ShareInformationActions.BACK.name());
        buttonBack.setBounds(new Rectangle(20, 270, 100, 40));

		scrollPane.setBounds(new Rectangle(20,20,550,200));

        view.getContentPane().add(labelMinPrice);
        view.getContentPane().add(spinnerMinPrice);
        view.getContentPane().add(labelMaxPrice);
        view.getContentPane().add(spinnerMaxPrice);
        view.getContentPane().add(buttonBack);
        view.getContentPane().add(buttonWatchShare);
		view.getContentPane().add(scrollPane);
		view.getContentPane().setLayout(null);
		view.setSize(new Dimension(600, 400));
		view.setTitle("Share Information");
		view.addWindowListener(new CloseChild(view));
		view.setVisible(true);
	}

	public void addListener(ActionListener actionListener){
	    buttonWatchShare.addActionListener(actionListener);
	    buttonBack.addActionListener(actionListener);
    }

    public String getSelectedRowId(){

	    int selectedRow = table.getSelectedRow();
	    if(selectedRow == -1){
            JOptionPane.showMessageDialog(null, "Please select a share", "Error", JOptionPane.PLAIN_MESSAGE);
	        return null;
        }
	    return (String) table.getValueAt(selectedRow, 0);
    }

    public int getMinPrice(){
	    try{
	        spinnerMinPrice.commitEdit();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        return (Integer)spinnerMinPrice.getValue();
    }

    public int getMaxPrice(){
        try{
            spinnerMaxPrice.commitEdit();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        return (Integer)spinnerMaxPrice.getValue();
    }

	@Override
	public void update(Observable observable, Object arg) {
        initView((AbstractTableModel) arg);
	}

	public void setModel(ShareInformationModel shareInformationModel){
	    this.shareInformationModel = shareInformationModel;
    }
}
