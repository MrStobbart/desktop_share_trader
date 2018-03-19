package controller;

import model.AuthenticationModel;
import viewCont.AuthenticationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthenticationController implements ActionListener {

    private String username;
    private String password;

    private AuthenticationView authenticationView;
    private AuthenticationModel authenticationModel;

    public AuthenticationController(){
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Controller: The " + e.getActionCommand()
                + " button is clicked at " + new java.util.Date(e.getWhen())
                + " with e.paramString " + e.paramString());


    }


    public void addView(AuthenticationView authenticationView){
        this.authenticationView = authenticationView;
    }

    public void addModel(AuthenticationModel authenticationModel){
        this.authenticationModel = authenticationModel;
    }


}
