package controller;

import enums.AuthActions;
import enums.AuthResults;
import enums.MainActions;
import model.AuthModel;
import viewCont.AuthView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class AuthController extends Observable implements ActionListener, Observer {

    private String username;
    private String password;

    private AuthView authView;
    private AuthModel authModel;

    public AuthController(){
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Controller: The " + e.getActionCommand()
                + " button is clicked at " + new java.util.Date(e.getWhen())
                + " with e.paramString " + e.paramString());

        username = authView.getAuthUsername();
        password = authView.getAuthPassword();

        if(e.getActionCommand() == AuthActions.LOGIN.name()){
            authModel.login(username, password);
        }else{
            authModel.signUp(username, password);
        }

    }

    @Override
    public void update(Observable observable, Object args){

        System.out.println("Authcontroller notified " + args);

        if(args == AuthResults.SUCCESSFUL || args == AuthResults.ACCOUNT_CREATED){

            setChanged();
            notifyObservers(MainActions.SHOW_NAVIGATION);
        }
    }

    public void addView(AuthView authView){
        this.authView = authView;
    }

    public void addModel(AuthModel authModel){
        this.authModel = authModel;
    }


}
