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

    private AuthView authView;
    private AuthModel authModel;

    @Override
    public void actionPerformed(ActionEvent e) {

        String username = authView.getAuthUsername();
        String password = authView.getAuthPassword();

        if(e.getActionCommand().equals(AuthActions.LOGIN.name())){
            authModel.login(username, password);
        }else{
            authModel.signUp(username, password);
        }

    }

    @Override
    public void update(Observable observable, Object args){

        if(args == AuthResults.SUCCESSFUL || args == AuthResults.ACCOUNT_CREATED){

            setChanged();
            notifyObservers(MainActions.SHOW_NAVIGATION);
        }
    }

    public void setView(AuthView authView){
        this.authView = authView;
    }

    public void setView(AuthModel authModel){
        this.authModel = authModel;
    }


}
