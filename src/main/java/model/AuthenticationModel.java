package model;

import java.util.Observable;

public class AuthenticationModel extends Observable{

    public void login(String name, String password){

        notifyObservers(true);
    }
}
