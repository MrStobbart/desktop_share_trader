package controller;

import enums.MainActions;
import model.AuthModel;
import model.BrokersModel;
import model.ShareInformationModel;
import model.TradesModel;
import viewCont.*;

import java.util.Observable;
import java.util.Observer;

public class MainController implements Observer {

    private AuthController authController;
    private AuthView authView;
    private AuthModel authModel;

    private NavigationController navigationController;
    private NavigationView navigationView;

    private ShareInformationController shareInformationController;
    private ShareInformationModel shareInformationModel;
    private ShareInformationView shareInformationView;

    private TradesController tradesController;
    private TradesView tradesView;
    private TradesModel tradesModel;

    private BrokersController brokersController;
    private BrokersModel brokersModel;
    private BrokersView brokersView;

    public MainController(){

        showAuthentication();

    }

    @Override
    public void update(Observable observable, Object args){

        switch ((MainActions)args){
            case SHOW_NAVIGATION:
                hideViews();
                showNavigation();
                break;
            case SHOW_SHARE_INFORMATION:
                hideViews();
                showShareInformation();
                break;
            case SHOW_TRADES:
                hideViews();
                showTrades();
                break;
            case SHOW_BROKERS:
                hideViews();
                showBrokers();
                break;

        }

        // TODO check for monitored shares
    }

    private void showBrokers() {
        brokersController = new BrokersController();
        brokersModel = new BrokersModel();
        brokersView = new BrokersView();

        brokersController.setModel(brokersModel);
        brokersController.setView(brokersView);
        brokersController.addObserver(this);

        brokersModel.addObserver(brokersView);
        brokersController.showView();
        brokersView.addListener(brokersController);
        // TODO write this
        System.out.println("Show brokers");
    }

    private void showAuthentication(){

        authController = new AuthController();
        authView = new AuthView();
        authModel = new AuthModel();

        authModel.addObserver(authView);
        authModel.addObserver(authController);

        authController.setView(authView);
        authController.setView(authModel);
        authController.addObserver(this);

        authView.addListener(authController);

        authView.showView();
    }

    private void showNavigation(){
        navigationController = new NavigationController();
        navigationView = new NavigationView();

        navigationController.addObserver(this);

        navigationView.addListener(navigationController);
        navigationView.showView();

    }

    private void showShareInformation(){
        shareInformationController = new ShareInformationController();
        shareInformationModel = new ShareInformationModel();
        shareInformationView = new ShareInformationView();

        shareInformationController.setModel(shareInformationModel);
        shareInformationController.setView(shareInformationView);
        shareInformationController.addObserver(this);

        shareInformationModel.addObserver(shareInformationView);

        shareInformationView.addListener(shareInformationController);
        shareInformationController.showView();

    }

    private void showTrades(){

        tradesController = new TradesController();
        tradesModel = new TradesModel();
        tradesView = new TradesView();

        tradesController.setModel(tradesModel);
        tradesController.setView(tradesView);
        tradesController.addObserver(this);

        tradesModel.addObserver(tradesView);
        tradesView.addListener(tradesController);

        String shareCode = shareInformationView.getSelectedRowShareCode();
        if(shareCode != null){
            tradesController.showView(shareCode);
        }else{
            // So that something
            showShareInformation();
        }

    }

    private void hideViews(){

        if(authView != null){
            authView.hideView();
        }

        if(navigationView != null){
            navigationView.hideView();
        }

        if(shareInformationView != null){
            shareInformationView.hideView();
        }

        if(tradesView != null){
            tradesView.hideView();
        }

        if(brokersView != null){
            brokersView.hideView();
        }
    }
}
