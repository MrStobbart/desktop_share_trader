package controller;

import enums.MainActions;
import model.AuthModel;
import viewCont.AuthView;

import java.util.Observable;
import java.util.Observer;

public class MainController implements Observer {


    public MainController(){

        initAuthentication();

    }

    @Override
    public void update(Observable observable, Object args){

        switch ((MainActions)args){
            case LOGGED_IN:
                System.out.println("Show navbar in main controller");
                break;

        }
    }

    private void initAuthentication(){

        AuthController authController = new AuthController();
        AuthView authView = new AuthView();
        AuthModel authModel = new AuthModel();

        authModel.addObserver(authView);
        authModel.addObserver(authController);

        authController.addView(authView);
        authController.addModel(authModel);
        authController.addObserver(this);

        authView.addListener(authController);

        authView.show();
    }

}
