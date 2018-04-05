package controller;

import enums.MainActions;
import enums.TableViewActions;
import model.ShareTradesModel;
import viewCont.TableView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class ShareTradesController extends Observable implements ActionListener{

    private ShareTradesModel shareTradesModel;
    private TableView tableView;

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(TableViewActions.BACK.name())){
            setChanged();
            notifyObservers(MainActions.SHOW_SHARE_INFORMATION);
        }

    }

    public void setModel(ShareTradesModel shareTradesModel){
        this.shareTradesModel = shareTradesModel;
    }

    public void setView(TableView tableView){
        this.tableView = tableView;
    }

    public void showView(String shareCode){
        shareTradesModel.loadTrades(shareCode);
    }
}
