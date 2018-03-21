package controller;

import enums.ShareInformationActions;
import model.ShareInformationModel;
import viewCont.ShareInformationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ShareInformationController implements Observer, ActionListener {

    private ShareInformationModel shareInformationModel;
    private ShareInformationView shareInformationView;

    @Override
    public void update(Observable observable, Object arg) {

    }

    public void setView(ShareInformationView shareInformationView){
        this.shareInformationView = shareInformationView;
    }

    public void setModel(ShareInformationModel shareInformationModel){
        this.shareInformationModel = shareInformationModel;
    }

    public void showView(){
        shareInformationModel.loadTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(ShareInformationActions.TRACK_SHARE.name())){

            String tradingCode = shareInformationView.getSelectedRowId();
            int minPrice = shareInformationView.getMinPrice();
            int maxPrice = shareInformationView.getMaxPrice();

            shareInformationModel.setShareAlert(tradingCode, minPrice, maxPrice);
        } else if(e.getActionCommand().equals(ShareInformationActions.BACK.name())){
            System.out.println("Back button clicked");
            // TODO close window and go back
        }
    }
}
