package controller;

import enums.AuthActions;
import model.AuthModel;
import viewCont.AuthView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthController implements ActionListener {

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

        if(e.getActionCommand() == AuthActions.login.name()){
            authModel.login(username, password);
        }else{
            authModel.signUp(username, password);
        }

    }

    public void addView(AuthView authView){
        this.authView = authView;
    }

    public void addModel(AuthModel authModel){
        this.authModel = authModel;
    }


}