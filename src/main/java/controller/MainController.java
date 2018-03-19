package controller;

import model.AuthModel;
import viewCont.AuthView;

public class MainController {

    public MainController(){
        initAuthentication();
    }

    private void initAuthentication(){

        AuthController authController = new AuthController();
        AuthView authView = new AuthView();
        AuthModel authModel = new AuthModel();

        authModel.addObserver(authView);

        authController.addView(authView);
        authController.addModel(authModel);

        authView.addListener(authController);

    }
}
