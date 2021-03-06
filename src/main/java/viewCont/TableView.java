package viewCont;

import enums.TableViewActions;
import model.ShareInformationModel;
import util.StaticMethods;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class TableView implements Observer{

    private JButton buttonBack = new JButton("Back");

	private JFrame frame = new JFrame();
	private JTable table;
	private JScrollPane scrollPane;

	private ShareInformationModel shareInformationModel;


	public void hideView(){
	    frame.setVisible(false);
    }
	

	public void initView(AbstractTableModel tableModel) {
	    table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        buttonBack.setActionCommand(TableViewActions.BACK.name());
        buttonBack.setBounds(new Rectangle(20, 230, 100, 40));

		scrollPane.setBounds(new Rectangle(20,20,860,200));

        frame.getContentPane().add(buttonBack);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().setLayout(null);
		frame.setSize(new Dimension(900, 400));
		frame.setTitle("Trades Information");
		frame.addWindowListener(new CloseChild(frame));
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		StaticMethods.positionAndShow(frame);
	}

	public void addListener(ActionListener actionListener){
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
}
