package controller;

import model.ShareInformationModel;
import viewCont.ShareInformationView;

import java.util.Observable;
import java.util.Observer;

public class ShareInformationController implements Observer {

    private ShareInformationModel shareInformationModel;
    private ShareInformationView shareInformationView;

    @Override
    public void update(Observable observable, Object arg) {

    }

    public void addView(ShareInformationView shareInformationView){
        this.shareInformationView = shareInformationView;
    }

    public void addModel(ShareInformationModel shareInformationModel){
        this.shareInformationModel = shareInformationModel;
    }

    public void showView(){
        shareInformationModel.loadTable();
    }
}
