package controller;

import enums.MainActions;
import model.*;
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

    private ShareTradesController shareTradesController;
    private ShareTradesModel shareTradesModel;
    private TableView shareTradesView;

    private BrokerTradesController brokerTradesController;
    private BrokerTradesModel brokerTradesModel;
    private TableView brokerTradesView;

    private BrokersController brokersController;
    private BrokersModel brokersModel;
    private BrokersView brokersView;

    private ShareholdersOfShareController shareholdersOfShareController;
    private ShareholdersOfShareModel shareholdersOfShareModel;
    private TableView shareholdersOfShareView;

    public MainController(){

        showAuthentication();

    }

    @Override
    public void update(Observable observable, Object args) {

        switch ((MainActions) args) {
            case SHOW_NAVIGATION:
                hideViews();
                showNavigation();
                break;
            case SHOW_SHARE_INFORMATION:
                hideViews();
                showShareInformation();
                break;
            case SHOW_SHARE_TRADES:
                hideViews();
                showShareTrades();
                break;
            case SHOW_BROKER_TRADES:
                hideViews();
                showBrokerTrades();
                break;
            case SHOW_BROKERS:
                hideViews();
                showBrokers();
                break;
            case SHOW_SHAREHOLDERS:
                hideViews();
                showShareholders();
                break;
            case SHOW_SHAREHOLDERS_OF_SHARE:
                hideViews();
                showShareholdersOfShare();
                break;
            case EXIT_APPLICATION:
                hideViews();
                System.exit(0);

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

    private void showShareTrades(){

        shareTradesController = new ShareTradesController();
        shareTradesModel = new ShareTradesModel();
        shareTradesView = new TableView();

        shareTradesController.setModel(shareTradesModel);
        shareTradesController.setView(shareTradesView);
        shareTradesController.addObserver(this);

        shareTradesModel.addObserver(shareTradesView);
        shareTradesView.addListener(shareTradesController);

        String shareCode = shareInformationView.getSelectedRowShareCode();
        if(shareCode != null){
            shareTradesController.showView(shareCode);
        }else{
            // So that something
            showShareInformation();
        }

    }

    private void showShareholdersOfShare() {
        shareholdersOfShareController = new ShareholdersOfShareController();
        shareholdersOfShareModel= new ShareholdersOfShareModel();
        shareholdersOfShareView= new TableView();

        shareholdersOfShareController.setModel(shareholdersOfShareModel);
        shareholdersOfShareController.setView(shareholdersOfShareView);
        shareholdersOfShareController.addObserver(this);

        shareholdersOfShareModel.addObserver(shareholdersOfShareView);
        shareholdersOfShareView.addListener(shareholdersOfShareController);

        String shareCode = shareInformationView.getSelectedRowShareCode();
        if(shareCode != null){
            shareholdersOfShareController.showView(shareCode);
        }else{
            // So that something
            showShareInformation();
        }

    }

    private void showBrokerTrades() {

        brokerTradesController = new BrokerTradesController();
        brokerTradesModel = new BrokerTradesModel();
        brokerTradesView = new TableView();

        brokerTradesController.setModel(brokerTradesModel);
        brokerTradesController.setView(brokerTradesView);
        brokerTradesController.addObserver(this);

        brokerTradesModel.addObserver(brokerTradesView);
        brokerTradesView.addListener(brokerTradesController);

        String brokerId = brokersView.getSelectedBrokerId();
        if(brokerId != null){
            brokerTradesController.showView(brokerId);
        }else{
            // So that something
            showBrokers();
        }
    }

    private void showShareholders() {
        System.out.println("Show shareholders");
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

        if(shareTradesView != null){
            shareTradesView.hideView();
        }

        if(brokersView != null){
            brokersView.hideView();
        }

        if(brokerTradesView != null){
            brokerTradesView.hideView();
        }

        if(shareholdersOfShareView != null){
            shareholdersOfShareView.hideView();
        }
    }
}
