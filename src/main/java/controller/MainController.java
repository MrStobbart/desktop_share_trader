package controller;

import enums.MainActions;
import model.AuthModel;
import model.ShareInformationModel;
import model.TradesModel;
import viewCont.AuthView;
import viewCont.NavigationView;
import viewCont.ShareInformationView;
import viewCont.TradesView;

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

        }
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
    }
}
