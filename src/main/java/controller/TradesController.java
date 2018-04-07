package controller;

import enums.MainActions;
import enums.TableViewActions;
import model.TradesModel;
import viewCont.TableView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class TradesController extends Observable implements ActionListener{

    private TradesModel tradesModel;
    private TableView tradesView;

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(TableViewActions.BACK.name())){
            setChanged();
            notifyObservers(MainActions.SHOW_NAVIGATION);
        }

    }

    public void setModel(TradesModel tradesModel){
        this.tradesModel = tradesModel;
    }

    public void setView(TableView tradesView){
        this.tradesView = tradesView;
    }

    public void showView(){
        tradesModel.loadTrades();
    }
}
