package viewCont;

import enums.BrokersActions;
import model.ShareInformationModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
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
public class BrokersView implements Observer {

    private JButton buttonGetTradeRecord = new JButton("Get trade record of selected broker");
    private JButton buttonBack = new JButton("Back");
    private JButton buttonRecommended = new JButton("Get recommended broker based on share field");
    private JLabel labelBrokerField = new JLabel("Please insert share field for broker recommendation");
    private JTextArea textAreaBrokerField = new JTextArea();

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

        labelBrokerField.setBounds(20, 230, 350, 20);
        textAreaBrokerField.setBounds(370,230,100,20);

        buttonGetTradeRecord.setActionCommand(BrokersActions.TRADE_RECORD.name());
        buttonGetTradeRecord.setBounds(new Rectangle(140, 320, 250, 40));

        buttonBack.setActionCommand(BrokersActions.BACK.name());
        buttonBack.setBounds(new Rectangle(20, 320, 100, 40));

        buttonRecommended.setActionCommand(BrokersActions.RECOMMENDATIONS.name());
        buttonRecommended.setBounds(new Rectangle( 70, 260, 350, 40));

		scrollPane.setBounds(new Rectangle(20,20,550,200));

        frame.getContentPane().add(labelBrokerField);
        frame.getContentPane().add(textAreaBrokerField);
        frame.getContentPane().add(buttonBack);
        frame.getContentPane().add(buttonGetTradeRecord);
        frame.getContentPane().add(buttonRecommended);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().setLayout(null);
		frame.setSize(new Dimension(600, 400));
		frame.setTitle("Brokers information");
		frame.addWindowListener(new CloseChild(frame));
		frame.setVisible(true);
	}

	public void addListener(ActionListener actionListener){
	    buttonGetTradeRecord.addActionListener(actionListener);
	    buttonBack.addActionListener(actionListener);
        buttonRecommended.addActionListener(actionListener);
    }

    public String getSelectedBrokerId(){

	    int selectedRow = table.getSelectedRow();
	    if(selectedRow == -1){
            JOptionPane.showMessageDialog(null, "Please select a broker", "Error", JOptionPane.PLAIN_MESSAGE);
	        return null;
        }

	    return (String) table.getModel().getValueAt(selectedRow, 0);
    }

    /**
     * Returns the value of the textAreaBrokerField and sets it to an empty string afterwards
     * @return  String in the textAreaBrokerField
     */
    public String getRecommendationField(){
	    String inputText = textAreaBrokerField.getText();
	    textAreaBrokerField.setText("");
	    return inputText;
    }

    public void sortTable(String filterCriteria){

	    // Set regex string to match all when no filter was given
	    if(filterCriteria == null || filterCriteria.isEmpty()){
	        filterCriteria = ".*";
        }
        TableRowSorter<TableModel> sorter = new TableRowSorter<>((table.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter("(?i)^"+filterCriteria+"$",2));

        table.setRowSorter(sorter);
    }


	@Override
	public void update(Observable observable, Object arg) {
        initView((AbstractTableModel) arg);
	}

	public void setModel(ShareInformationModel shareInformationModel){
	    this.shareInformationModel = shareInformationModel;
    }
}
