package controller;

import enums.MainActions;
import enums.TradesActions;
import model.ShareTradesModel;
import viewCont.TradesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class ShareTradesController extends Observable implements ActionListener{

    private ShareTradesModel shareTradesModel;
    private TradesView tradesView;

    public ShareTradesController(){
        shareTradesModel = new ShareTradesModel();
        tradesView = new TradesView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(TradesActions.BACK.name())){
            setChanged();
            notifyObservers(MainActions.SHOW_SHARE_INFORMATION);
        }

    }

    public void setModel(ShareTradesModel shareTradesModel){
        this.shareTradesModel = shareTradesModel;
    }

    public void setView(TradesView tradesView){
        this.tradesView = tradesView;
    }

    public void showView(String shareCode){
        shareTradesModel.loadTrades(shareCode);
    }
}
