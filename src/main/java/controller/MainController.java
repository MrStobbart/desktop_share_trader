package controller;

import enums.MainActions;
import model.*;
import viewCont.*;

import java.util.Observable;
import java.util.Observer;

public class MainController implements Observer {

    private static int userId = 0;

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

    private TradesController tradesController;
    private TradesModel tradesModel;
    private TableView tradesView;

    private BrokersController brokersController;
    private BrokersModel brokersModel;
    private BrokersView brokersView;

    private ShareholdersOfShareController shareholdersOfShareController;
    private ShareholdersOfShareModel shareholdersOfShareModel;
    private TableView shareholdersOfShareView;

    private ShareholdersController shareholdersController;
    private ShareholdersModel shareholdersModel;
    private ShareholdersView shareholdersView;

    private ShareholderTradesController shareholderTradesController;
    private ShareholderTradesModel shareholderTradesModel;
    private TableView shareholderTradesView;

    private AlertModel alertModel = new AlertModel();


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
            case SHOW_SHAREHOLDER_TRADES:
                hideViews();
                showShareholdersTrades();
                break;
            case SHOW_TRADES:
                hideViews();
                showTrades();
                break;
            case EXIT_APPLICATION:
                hideViews();
                System.exit(0);

        }

        checkForShareMonitoringAlerts();

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
            showShareInformation();
        }

    }

    private void showTrades(){

        tradesController = new TradesController();
        tradesModel = new TradesModel();
        tradesView = new TableView();

        tradesController.setModel(tradesModel);
        tradesController.setView(tradesView);
        tradesController.addObserver(this);

        tradesModel.addObserver(tradesView);
        tradesView.addListener(tradesController);

        tradesController.showView();

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
            showBrokers();
        }
    }

    private void showShareholdersTrades() {

        shareholderTradesController = new ShareholderTradesController();
        shareholderTradesModel = new ShareholderTradesModel();
        shareholderTradesView = new TableView();

        shareholderTradesController.setModel(shareholderTradesModel);
        shareholderTradesController.setView(shareholdersOfShareView);
        shareholderTradesController.addObserver(this);

        shareholderTradesModel.addObserver(shareholderTradesView);
        shareholderTradesView.addListener(shareholderTradesController);

        String shareholderId = shareholdersView.getSelectedShareholderId();
        if(shareholderId != null){
            shareholderTradesController.showView(shareholderId);
        }else{
            showShareholders();
        }
    }

    private void showShareholders() {

        shareholdersController = new ShareholdersController();
        shareholdersModel = new ShareholdersModel();
        shareholdersView = new ShareholdersView();

        shareholdersController.setModel(shareholdersModel);
        shareholdersController.setView(shareholdersView);
        shareholdersController.addObserver(this);

        shareholdersModel.addObserver(shareholdersView);
        shareholdersView.addListener(shareholdersController);

        shareholdersController.showView();
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

        if(shareholdersView != null){
            shareholdersView.hideView();
        }

        if(shareholderTradesView != null){
            shareholderTradesView.hideView();
        }

        if(tradesView != null){
            tradesView.hideView();
        }
    }

    private void checkForShareMonitoringAlerts() {

        if(userId != 0){
            alertModel.checkShareAlert(userId);
        }
    }

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        MainController.userId = userId;
    }

}
