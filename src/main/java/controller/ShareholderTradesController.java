package controller;

import enums.MainActions;
import enums.TableViewActions;
import model.ShareholderTradesModel;
import viewCont.TableView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class ShareholderTradesController extends Observable implements ActionListener{

    private ShareholderTradesModel shareholderTradesModel;
    private TableView tableView;

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(TableViewActions.BACK.name())){
            setChanged();
            notifyObservers(MainActions.SHOW_SHAREHOLDERS);
        }

    }

    public void setModel(ShareholderTradesModel shareholderTradesModel){
        this.shareholderTradesModel = shareholderTradesModel;
    }

    public void setView(TableView tableView){
        this.tableView = tableView;
    }

    public void showView(String shareholderId){
        shareholderTradesModel.loadTrades(shareholderId);
    }
}
