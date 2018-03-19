package controller;

import model.AuthenticationModel;
import viewCont.AuthenticationView;

public class MainController {

    public MainController(){
        initAuthentication();
    }

    private void initAuthentication(){

        AuthenticationController authenticationController = new AuthenticationController();
        AuthenticationView authenticationView = new AuthenticationView();
        AuthenticationModel authenticationModel = new AuthenticationModel();

        authenticationModel.addObserver(authenticationView);

        authenticationController.addView(authenticationView);
        authenticationController.addModel(authenticationModel);

        authenticationView.addLoginListener(authenticationController);
        authenticationView.addSignUpListener(authenticationController);

    }
}
