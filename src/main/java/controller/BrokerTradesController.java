package controller;

import enums.MainActions;
import enums.TableViewActions;
import model.BrokerTradesModel;
import viewCont.TableView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class BrokerTradesController extends Observable implements ActionListener{

    private BrokerTradesModel brokerTradesModel;
    private TableView tableView;

    public BrokerTradesController(){
        brokerTradesModel = new BrokerTradesModel();
        tableView = new TableView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(TableViewActions.BACK.name())){
            setChanged();
            notifyObservers(MainActions.SHOW_BROKERS);
        }

    }

    public void setModel(BrokerTradesModel brokerTradesModel){
        this.brokerTradesModel = brokerTradesModel;
    }

    public void setView(TableView tableView){
        this.tableView = tableView;
    }

    public void showView(String brokerId){
        brokerTradesModel.loadTrades(brokerId);
    }
}
