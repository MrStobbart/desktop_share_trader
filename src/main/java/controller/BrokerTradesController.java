package controller;

import enums.MainActions;
import enums.TradesActions;
import model.BrokerTradesModel;
import viewCont.TradesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class BrokerTradesController extends Observable implements ActionListener{

    private BrokerTradesModel brokerTradesModel;
    private TradesView tradesView;

    public BrokerTradesController(){
        brokerTradesModel = new BrokerTradesModel();
        tradesView = new TradesView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(TradesActions.BACK.name())){
            setChanged();
            notifyObservers(MainActions.SHOW_BROKERS);
        }

    }

    public void setModel(BrokerTradesModel brokerTradesModel){
        this.brokerTradesModel = brokerTradesModel;
    }

    public void setView(TradesView tradesView){
        this.tradesView = tradesView;
    }

    public void showView(String brokerId){
        brokerTradesModel.loadTrades(brokerId);
    }
}
