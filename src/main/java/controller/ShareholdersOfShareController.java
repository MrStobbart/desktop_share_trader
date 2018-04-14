package controller;

import enums.MainActions;
import enums.TableViewActions;
import model.ShareholdersOfShareModel;
import viewCont.TableView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class ShareholdersOfShareController extends Observable implements ActionListener{

    private ShareholdersOfShareModel shareholdersOfShareModel;
    private TableView tableView;

        @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(TableViewActions.BACK.name())){
            setChanged();
            notifyObservers(MainActions.SHOW_SHARE_INFORMATION);
        }

    }

    public void setModel(ShareholdersOfShareModel shareholdersOfShareModel){
        this.shareholdersOfShareModel = shareholdersOfShareModel;
    }

    public void setView(TableView tableView){
        this.tableView = tableView;
    }

    public void showView(String brokerId){
        shareholdersOfShareModel.loadShareholders(brokerId);
    }
}
