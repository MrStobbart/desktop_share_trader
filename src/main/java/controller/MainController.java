package controller;

import enums.MainActions;
import model.AuthModel;
import viewCont.AuthView;
import viewCont.NavigationView;

import java.util.Observable;
import java.util.Observer;

public class MainController implements Observer {

    private AuthController authController;
    private AuthView authView;
    private AuthModel authModel;

    private NavigationController navigationController;
    private NavigationView navigationView;

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
                System.out.println("Show share information");
                break;

        }
    }

    private void showAuthentication(){

        authController = new AuthController();
        authView = new AuthView();
        authModel = new AuthModel();

        authModel.addObserver(authView);
        authModel.addObserver(authController);

        authController.addView(authView);
        authController.addModel(authModel);
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

    private void hideViews(){

        if(authView != null){
            authView.hideView();
        }

        if(navigationView != null){
            navigationView.hideView();
        }
    }
}
