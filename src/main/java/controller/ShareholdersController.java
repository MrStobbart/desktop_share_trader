package controller;

import enums.MainActions;
import enums.ShareholderActions;
import model.ShareholdersModel;
import viewCont.ShareholdersView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class ShareholdersController extends Observable implements ActionListener{

    private ShareholdersModel shareholdersModel;
    private ShareholdersView shareholdersView;

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(ShareholderActions.BACK.name())){
            setChanged();
            notifyObservers(MainActions.SHOW_NAVIGATION);
        } else if(e.getActionCommand().equals(ShareholderActions.SHAREHOLDER_TRADES.name())){
            setChanged();
            notifyObservers(MainActions.SHOW_SHAREHOLDER_TRADES);
        }

    }

    public void setModel(ShareholdersModel shareholdersModel){
        this.shareholdersModel = shareholdersModel;
    }

    public void setView(ShareholdersView shareholdersView){
        this.shareholdersView = shareholdersView;
    }

    public void showView(){
        shareholdersModel.loadShareholders();
    }
}
