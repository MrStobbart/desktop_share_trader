package controller;

import enums.MainActions;
import enums.TradesActions;
import model.TradesModel;
import viewCont.TradesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class TradesController extends Observable implements ActionListener{

    private TradesModel tradesModel;
    private TradesView tradesView;

    public TradesController(){
        tradesModel = new TradesModel();
        tradesView = new TradesView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(TradesActions.BACK.name())){
            setChanged();
            notifyObservers(MainActions.SHOW_SHARE_INFORMATION);
        }

    }

    public void setModel(TradesModel tradesModel){
        this.tradesModel = tradesModel;
    }

    public void setView(TradesView tradesView){
        this.tradesView = tradesView;
    }

    public void showView(String shareCode){
        tradesModel.loadTable(shareCode);
    }
}
